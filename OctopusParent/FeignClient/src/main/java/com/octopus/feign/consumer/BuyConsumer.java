package com.octopus.feign.consumer;

import com.octopus.common.bo.BuyBo;
import com.octopus.common.bo.BuyResponseBo;
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

    public boolean buy(BuyBo buyBo) {
        BuyResponseBo buyResponseBo = new BuyResponseBo();
        //1.建单
        buyBo.setOrderStep(MicroService.getOrderStep(MicroService.ORDER_ESTBLISH));
        buyResponseBo = orderDispatcher.createOrder(buyBo);
        if (buyResponseBo.isOrderReturnCode()) {
            buyBo.setOrderStep(MicroService.getOrderStep(MicroService.CUSTOMER_CHECK));
        }

        //2.客户预检查
        buyResponseBo= null;
        //buyResponseBo = userDispatcher.precheck(buyBo); todo
        if (buyResponseBo.isOrderReturnCode()) {
            buyBo.setOrderStep(MicroService.getOrderStep(MicroService.PRODUCT_CHECK));
        }
        //3.产品预检查
        buyResponseBo= null;
        buyResponseBo =productDispatcher.checkProduct(buyBo);
        if (buyResponseBo.isOrderReturnCode()) {
            buyBo.setOrderStep(MicroService.getOrderStep(MicroService.VOLUME_FROZON));
        }
        //4.额度控销
        buyResponseBo= null;
        buyResponseBo =productDispatcher.checkProduct(buyBo);//todo
        if (buyResponseBo.isOrderReturnCode()) {
            buyBo.setOrderStep(MicroService.getOrderStep(MicroService.PAY_BILL));
        }
        //5.支付  todo

        //6.加仓
        buyResponseBo= null;
        buyResponseBo =userDispatcher.addPosition(buyBo);
        if (buyResponseBo.isOrderReturnCode()) {
            buyBo.setOrderStep(MicroService.getOrderStep(MicroService.PAY_BILL));
        }

        //7.完成订单




        return true;


    }
}
