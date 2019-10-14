package com.octopus.eureka.control;

import com.octopus.common.bo.BuyBo;
import com.octopus.eureka.service.OrderBuyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.GET;

/**
 * @Author dongjiale
 * @Date 2019/10/11 8:50 PM
 * @Version 1.0
 */
@RestController
public class OrderBuyController {

    private final static Logger logger = LoggerFactory.getLogger(OrderBuyController.class);

    @Autowired
    private OrderBuyService orderBuyService;

    @RequestMapping(value = "/transaction/buy",method = RequestMethod.GET)
    public String buy(@RequestParam(value = "customerId") String customerId, @RequestParam(value = "productId")  String productId, Model model){
        BuyBo buyBo = new BuyBo();
        if(" ".equals(customerId)|| " ".equals(productId) | customerId == null | productId == null){
            logger.error("请求参数不能为空!custormerId:" + customerId + ",productId" + productId);
        }
        buyBo.setCustomerId(customerId);
        buyBo.setProductId(productId);
        model.addAttribute("buyBo",buyBo);
        return "orderBuy";
    }

    @RequestMapping(value = "/transaction/buy", method = RequestMethod.POST)
    public String buy(@RequestBody BuyBo buyBo, Model model){
        logger.info("前端传来的buyBo为：" + buyBo.toString());
        if (buyBo.getCustomerId() == null | "".equals(buyBo.getCustomerId())){
            logger.error("custormerId请求参数为空!");
        }
        if (buyBo.getProductId() == null | "".equals(buyBo.getProductId())){
            logger.error("productId请求参数为空!");
        }
        if (buyBo.getTransactionAmount() == null | "".equals(buyBo.getTransactionAmount())){
            logger.error("transactionAmount请求参数为空!");
        }
        if (buyBo.getBusinessCode() == null | "".equals(buyBo.getBusinessCode())){
            logger.error("businessCode请求参数为空!");
        }
        orderBuyService.orderBuy(buyBo);
        return "orderlist";
    }

}
