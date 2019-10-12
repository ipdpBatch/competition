package com.octopus.feign.consumer;

import com.octopus.common.utils.DateUtil;
import com.octopus.common.bo.BuyBo;
import com.octopus.common.bo.BuyResponseBo;
import com.octopus.common.dao.domain.ControlCenterDto;
import com.octopus.common.dao.mapper.ControlCenterMapper;
import com.octopus.common.enums.MicroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 文件创建时写入注释内容
 *
 * @author zp3778
 * @version 1.0.0
 * @date Created in 18:56 2019/9/29
 */
@Component
public class BuyConsumer {
    private final static Logger logger = LoggerFactory.getLogger(BuyConsumer.class);
    @Autowired
    private OrderDispatcher orderDispatcher;
    @Autowired
    private ProductDispatcher productDispatcher;
    @Autowired
    private UserDispatcher userDispatcher;
    @Autowired
    private ControlCenterMapper controlCenterMapper;
    @Autowired
    private PayDispatcher payDispatcher;

    public boolean buy(BuyBo buyBo) {
        logger.info("输入买入请求参数:"+buyBo.toString());
        BuyResponseBo buyResponseBo = new BuyResponseBo();
        //获取订单编号，获取控制表的dto
        ControlCenterDto controlCenterDto = new ControlCenterDto();
        controlCenterDto = controlCenterMapper.selectById(buyBo.getOrderSeq());
        controlCenterDto.setOrderStep(MicroService.getOrderStep(MicroService.ORDER_ESTBLISH));
        //总控制表更新订单步骤为ESTB
        controlCenterDto.setUpdateTime(DateUtil.formatTime(new Date()));
        controlCenterMapper.update(controlCenterDto);

        //1.调用建单微服务
        buyBo.setOrderStep(MicroService.getOrderStep(MicroService.ORDER_ESTBLISH));
        buyResponseBo = orderDispatcher.createOrder(buyBo);
        if (buyResponseBo.isOrderReturnCode()) {
            //如果建单成功，切换buyBo的订单步骤为CHKC
            logger.info("-------1.建单成功，切换buyBo的订单步骤为CHKC");
            buyBo.setOrderStep(MicroService.getOrderStep(MicroService.CUSTOMER_CHECK));
            controlCenterDto.setOrderStep(MicroService.getOrderStep(MicroService.CUSTOMER_CHECK));
            controlCenterDto.setUpdateTime(DateUtil.formatTime(new Date()));
            controlCenterMapper.update(controlCenterDto);
        }else{
            logger.info("订单建立失败!"+buyResponseBo.getErrorDetail());
            controlCenterDto.setOrderStatus("PCFL");
            controlCenterMapper.update(controlCenterDto);
            return false;
        }

        //2.客户预检查
        buyResponseBo= null;
        buyResponseBo = userDispatcher.precheck(buyBo);
        if (buyResponseBo.isOrderReturnCode()) {
            //如果客户预检查通过，切换订单步骤为CHKP
            logger.info("-------2.客户预检查通过，切换订单步骤为CHKP");
            buyBo.setOrderStep(MicroService.getOrderStep(MicroService.PRODUCT_CHECK));
            controlCenterDto.setOrderStep(MicroService.getOrderStep(MicroService.PRODUCT_CHECK));
            controlCenterDto.setUpdateTime(DateUtil.formatTime(new Date()));
            controlCenterMapper.update(controlCenterDto);
        }else{
            logger.info("客户预检查失败!"+buyResponseBo.getErrorDetail());
            controlCenterDto.setOrderStatus("PCFL");
            controlCenterMapper.update(controlCenterDto);
            return false;
        }

        //3.产品预检查
        buyResponseBo= null;
        buyResponseBo =productDispatcher.checkProduct(buyBo);
        if (buyResponseBo.isOrderReturnCode()) {
            //如果产品预检查通过，切换订单步骤为VOLF
            logger.info("-------3.产品预检查通过，切换订单步骤为VOLF");
            buyBo.setOrderStep(MicroService.getOrderStep(MicroService.VOLUME_FROZON));
            controlCenterDto.setOrderStep(MicroService.getOrderStep(MicroService.VOLUME_FROZON));
            controlCenterDto.setUpdateTime(DateUtil.formatTime(new Date()));
            controlCenterMapper.update(controlCenterDto);
        }else{
            logger.info("产品预检查失败!"+buyResponseBo.getErrorDetail());
            controlCenterDto.setOrderStatus("PCFL");
            controlCenterMapper.update(controlCenterDto);
            return false;
        }

        //4.额度控销
        buyResponseBo= null;
        buyResponseBo =productDispatcher.checkQuota(buyBo);//todo
        logger.info("额度控销执行结果:"+buyResponseBo.isOrderReturnCode());
        if (buyResponseBo.isOrderReturnCode()) {
            //如果额度控销成功，切换订单步骤为PAYA
            logger.info("-------4.额度控销成功，切换订单步骤为PAYA");
            buyBo.setOrderStep(MicroService.getOrderStep(MicroService.PAY_BILL));
            controlCenterDto.setOrderStep(MicroService.getOrderStep(MicroService.PAY_BILL));
            controlCenterDto.setUpdateTime(DateUtil.formatTime(new Date()));
            controlCenterMapper.update(controlCenterDto);
        }else{
            logger.info("份额圈存失败!"+buyResponseBo.getErrorDetail());
            controlCenterDto.setOrderStatus("PCFL");
            controlCenterMapper.update(controlCenterDto);
            return false;
        }

        //5.支付  todo
        buyResponseBo= null;
        buyResponseBo =payDispatcher.payProcess(buyBo);//todo
        if (buyResponseBo.isOrderReturnCode()) {
            logger.info("-------5.申购扣款成功，切换订单步骤为PAYA");
            buyBo.setOrderStep(MicroService.getOrderStep(MicroService.POSITION_INCREASE));
            controlCenterDto.setOrderStep(MicroService.getOrderStep(MicroService.POSITION_INCREASE));
            controlCenterDto.setUpdateTime(DateUtil.formatTime(new Date()));
            controlCenterMapper.update(controlCenterDto);
        }else{
            logger.info("申购扣款失败!"+buyResponseBo.getErrorDetail());
            controlCenterDto.setOrderStatus("PCFL");
            controlCenterMapper.update(controlCenterDto);
            return false;
        }

        //6.加仓
        buyResponseBo= null;
        buyResponseBo =userDispatcher.addPosition(buyBo);
        if (buyResponseBo.isOrderReturnCode()) {
            //如果加仓成功，切换订单步骤为FNSH
            logger.info("-------6.加仓成功，切换订单步骤为FNSH");
            buyBo.setOrderStep(MicroService.getOrderStep(MicroService.FINISH));
            controlCenterDto.setOrderStep(MicroService.getOrderStep(MicroService.FINISH));
            controlCenterMapper.update(controlCenterDto);
        }else{
            logger.info("持仓更新失败!"+buyResponseBo.getErrorDetail());
            controlCenterDto.setOrderStatus("PCFL");
            controlCenterMapper.update(controlCenterDto);
            return false;
        }

        //7.完成订单
        buyResponseBo= null;
        buyResponseBo = orderDispatcher.finishOrder(buyBo);
        if (buyResponseBo.isOrderReturnCode()) {
            //申购交易完成，终态标志置为Y
            logger.info("-------7.申购交易完成，终态标志置为Y ~~~~~~~~~~~~~~~~~~~");
            controlCenterDto.setFlag("Y");
            controlCenterMapper.update(controlCenterDto);
            logger.info("申购订单已完成!!!");
        }else{
            logger.info("订单结束更新失败!"+buyResponseBo.getErrorDetail());
            controlCenterDto.setOrderStatus("PCFL");
            controlCenterMapper.update(controlCenterDto);
            return false;
        }
        return true;
    }
}
