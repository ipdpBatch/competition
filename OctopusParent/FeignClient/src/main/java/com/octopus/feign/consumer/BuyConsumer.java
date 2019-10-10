package com.octopus.feign.consumer;

import com.octopus.common.bo.BuyBo;
import com.octopus.common.bo.BuyResponseBo;
import com.octopus.common.dao.domain.ControlCentureDto;
import com.octopus.common.dao.mapper.ControlCentureMapper;
import com.octopus.common.enums.MicroService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 文件创建时写入注释内容
 *
 * @author zp3778
 * @version 1.0.0
 * @date Created in 18:56 2019/9/29
 */
public class BuyConsumer {
    @Autowired
    private OrderDispatcher orderDispatcher;
    @Autowired
    private ProductDispatcher productDispatcher;
    @Autowired
    private UserDispatcher userDispatcher;
    @Autowired
    private ControlCentureMapper controlCentureMapper;

    public boolean buy(BuyBo buyBo) {
        BuyResponseBo buyResponseBo = new BuyResponseBo();
        //获取订单编号，获取控制表的dto
        ControlCentureDto controlCentureDto = new ControlCentureDto();
        controlCentureDto = controlCentureMapper.selectById(buyBo.getOrderSeq());

        controlCentureDto.setOrderStep(MicroService.getOrderStep(MicroService.ORDER_ESTBLISH));
        controlCentureMapper.update(controlCentureDto);

        //1.建单
        buyBo.setOrderStep(MicroService.getOrderStep(MicroService.ORDER_ESTBLISH));
        buyResponseBo = orderDispatcher.createOrder(buyBo);
        if (buyResponseBo.isOrderReturnCode()) {
            buyBo.setOrderStep(MicroService.getOrderStep(MicroService.CUSTOMER_CHECK));
        }


        controlCentureDto.setOrderStep(MicroService.getOrderStep(MicroService.CUSTOMER_CHECK));
        controlCentureMapper.update(controlCentureDto);
        //2.客户预检查
        buyResponseBo= null;
        buyResponseBo = userDispatcher.precheck(buyBo);
        if (buyResponseBo.isOrderReturnCode()) {
            buyBo.setOrderStep(MicroService.getOrderStep(MicroService.PRODUCT_CHECK));
        }

        controlCentureDto.setOrderStep(MicroService.getOrderStep(MicroService.PRODUCT_CHECK));
        controlCentureMapper.update(controlCentureDto);
        //3.产品预检查
        buyResponseBo= null;
        buyResponseBo =productDispatcher.checkProduct(buyBo);
        if (buyResponseBo.isOrderReturnCode()) {
            buyBo.setOrderStep(MicroService.getOrderStep(MicroService.VOLUME_FROZON));
        }

        controlCentureDto.setOrderStep(MicroService.getOrderStep(MicroService.VOLUME_FROZON));
        controlCentureMapper.update(controlCentureDto);
        //4.额度控销
        buyResponseBo= null;
        buyResponseBo =productDispatcher.checkProduct(buyBo);//todo
        if (buyResponseBo.isOrderReturnCode()) {
            buyBo.setOrderStep(MicroService.getOrderStep(MicroService.PAY_BILL));
        }



        controlCentureDto.setOrderStep(MicroService.getOrderStep(MicroService.PAY_BILL));
        controlCentureMapper.update(controlCentureDto);
        //5.支付  todo





        controlCentureDto.setOrderStep(MicroService.getOrderStep(MicroService.POSITION_INCREASE));
        controlCentureMapper.update(controlCentureDto);
        //6.加仓
        buyResponseBo= null;
        buyResponseBo =userDispatcher.addPosition(buyBo);
        if (buyResponseBo.isOrderReturnCode()) {
            buyBo.setOrderStep(MicroService.getOrderStep(MicroService.FINISH));
        }


        controlCentureDto.setOrderStep(MicroService.getOrderStep(MicroService.FINISH));
        controlCentureMapper.update(controlCentureDto);
        //7.完成订单
        buyResponseBo = orderDispatcher.createOrder(buyBo);
        if (buyResponseBo.isOrderReturnCode()) {
            //申购交易完成
        }
        return true;


    }
}
