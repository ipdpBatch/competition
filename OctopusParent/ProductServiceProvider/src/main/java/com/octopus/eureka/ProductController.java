package com.octopus.eureka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文件创建时写入注释内容
 *
 * @author zp3778
 * @version 1.0.0
 * @date Created in 17:08 2019/9/23
 */
@RestController
public class ProductController {
    @Value("${server.port}")
    String port;
    @RequestMapping("/product")
    public String home() {
        return "Hello world ,port:" + port +". This is " + this.getClass().getName();
    }
}
