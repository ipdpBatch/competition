package com.octopus.eureka.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;


@RestController
@RequestMapping("/octopus")
public class OctopusEarController {
    private final static Logger logger = LoggerFactory.getLogger(OctopusEarController.class);

    /*@GetMapping(value = "/customerId/{customerId}/productId/{productId}/transactionAmount/{transactionAmount}")
    public String buyRequest(@PathVariable("customerId") String id, @PathVariable("productId") String pid, @PathVariable("transactionAmount") BigDecimal txnamt) {
        logger.info("接收交易请求如下:【客户号】"+id+"【产品代码】"+pid+"【交易金额】"+txnamt);
        return null;
    }*/

    @GetMapping("/get")
    public Date get() {
        return new Date();
    }

    /**
     * 带有 URI 模板
     */
/*
    @GetMapping(path = "/{day}")
    public String getForDay(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date day, Model model) {
        System.out.println("--> " + new SimpleDateFormat("yyyy-MM-dd").format(day));
        return "-->" + new SimpleDateFormat("yyyy-MM-dd").format(day);
    }
*/
    @GetMapping("/post")
    public Map<String, String> test(@RequestParam Map<String, String> fieldValueList) {
        return fieldValueList;
    }

    @GetMapping("/posta")
    public String post(@RequestParam String username, @RequestParam String password) {
        return "Username:" + username + "'\n'password:" + password;
    }
}