package com.octopus.feign.consumer;

import com.octopus.common.bo.BuyBo;
import com.octopus.common.bo.BuyResponseBo;
import com.octopus.common.dao.domain.ControlCenterDto;
import com.octopus.common.dao.mapper.ControlCenterMapper;
import com.octopus.common.enums.MicroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 文件创建时写入注释内容
 *
 * @author zp3778
 * @version 1.0.0
 * @date Created in 18:56 2019/9/29
 */
@Component
public class BuyConsumer {
    @Autowired
    private OrderDispatcher orderDispatcher;
    @Autowired
    private ProductDispatcher productDispatcher;
    @Autowired
    private UserDispatcher userDispatcher;
    @Autowired
    private ControlCenterMapper controlCenterMapper;

    public boolean buy(BuyBo buyBo) {
        BuyResponseBo buyResponseBo = new BuyResponseBo();
        //获取订单编号，获取控制表的dto
        ControlCenterDto controlCenterDto = new ControlCenterDto();
        controlCenterDto = controlCenterMapper.selectById(buyBo.getOrderSeq());

        controlCenterDto.setOrderStep(MicroService.getOrderStep(MicroService.ORDER_ESTBLISH));
        controlCenterMapper.update(controlCenterDto);

        //1.建单
        buyBo.setOrderStep(MicroService.getOrderStep(MicroService.ORDER_ESTBLISH));
        buyResponseBo = orderDispatcher.createOrder(buyBo);
        if (buyResponseBo.isOrderReturnCode()) {
            buyBo.setOrderStep(MicroService.getOrderStep(MicroService.CUSTOMER_CHECK));
        }


        controlCenterDto.setOrderStep(MicroService.getOrderStep(MicroService.CUSTOMER_CHECK));
        controlCenterMapper.update(controlCenterDto);
        //2.客户预检查
        buyResponseBo= null;
        buyResponseBo = userDispatcher.precheck(buyBo);
        if (buyResponseBo.isOrderReturnCode()) {
            buyBo.setOrderStep(MicroService.getOrderStep(MicroService.PRODUCT_CHECK));
        }

        controlCenterDto.setOrderStep(MicroService.getOrderStep(MicroService.PRODUCT_CHECK));
        controlCenterMapper.update(controlCenterDto);
        //3.产品预检查
        buyResponseBo= null;
        buyResponseBo =productDispatcher.checkProduct(buyBo);
        if (buyResponseBo.isOrderReturnCode()) {
            buyBo.setOrderStep(MicroService.getOrderStep(MicroService.VOLUME_FROZON));
        }

        controlCenterDto.setOrderStep(MicroService.getOrderStep(MicroService.VOLUME_FROZON));
        controlCenterMapper.update(controlCenterDto);
        //4.额度控销
        buyResponseBo= null;
        buyResponseBo =productDispatcher.checkProduct(buyBo);//todo
        if (buyResponseBo.isOrderReturnCode()) {
            buyBo.setOrderStep(MicroService.getOrderStep(MicroService.PAY_BILL));
        }



        controlCenterDto.setOrderStep(MicroService.getOrderStep(MicroService.PAY_BILL));
        controlCenterMapper.update(controlCenterDto);
        //5.支付  todo





        controlCenterDto.setOrderStep(MicroService.getOrderStep(MicroService.POSITION_INCREASE));
        controlCenterMapper.update(controlCenterDto);
        //6.加仓
        buyResponseBo= null;
        buyResponseBo =userDispatcher.addPosition(buyBo);
        if (buyResponseBo.isOrderReturnCode()) {
            buyBo.setOrderStep(MicroService.getOrderStep(MicroService.FINISH));
        }


        controlCenterDto.setOrderStep(MicroService.getOrderStep(MicroService.FINISH));
        controlCenterMapper.update(controlCenterDto);
        //7.完成订单
        buyResponseBo = orderDispatcher.createOrder(buyBo);
        if (buyResponseBo.isOrderReturnCode()) {
            //申购交易完成
        }
        return true;


    }
}
