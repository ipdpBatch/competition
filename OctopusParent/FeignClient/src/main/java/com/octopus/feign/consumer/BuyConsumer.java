package com.octopus.feign.consumer;

import com.netflix.discovery.converters.Auto;
import com.octopus.common.bo.BuyBo;
import com.octopus.common.dao.domain.OrderFinancialDto;
import com.octopus.common.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * 文件创建时写入注释内容
 *
 * @author zp3778
 * @version 1.0.0
 * @date Created in 18:56 2019/9/29
 */
public class BuyConsumer {
    @Autowired
    private  OrderDispatcher orderDispatcher;
    @Autowired
    private  ProductDispatcher productDispatcher;

    public boolean buy(BuyBo buyBo){
        //1.建单
//        buyBo.setOrderStep("ESTB");
//        if(orderDispatcher.createOrder(buyBo)){
//            buyBo.setOrderStep("CHKC");
//            /*订单控制信息切换步骤*/
//        }
        //2.客户预检查

        //3.产品预检查
        productDispatcher.checkProduct(buyBo);

        //4.额度控销

        //5.支付

        //6.加仓
        //7.完成订单
     return  true;





    }
}
