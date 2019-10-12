package com.octopus.eureka.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author dongjiale
 * @Date 2019/10/11 8:50 PM
 * @Version 1.0
 */
@Controller
@RequestMapping(value = "/transaction")
public class OrderBuyController {

    private final static Logger logger = LoggerFactory.getLogger(OrderBuyController.class);

    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public String buy(Model model){
        return "orderBuy";
    }

}
