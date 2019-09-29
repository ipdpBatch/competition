package com.octopus.eureka.order;

import com.octopus.common.dao.domain.OrderTestDto;
import com.octopus.common.dao.mapper.OrderTestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 文件创建时写入注释内容
 *
 * @author zp3778
 * @version 1.0.0
 * @date Created in 17:08 2019/9/23
 */
@RestController
public class OrderTestController {

    private final static Logger logger = LoggerFactory.getLogger(OrderTestController.class);

    @Value("${server.port}")
    String port;

    @Autowired
    OrderTestMapper orderTestMapper;

    @RequestMapping("/ordertest")
    public String home() {
        return "Hello world ,port:" + port +". This is " + this.getClass().getName();
    }

    @GetMapping("/ordertest/{orderId}")
    public OrderTestDto getControlOrder(@PathVariable("orderId") String orderId) {
        logger.info("orderId："+ orderId);
        return orderTestMapper.selectById(orderId);
    }

    @GetMapping("/ordertest/all")
    public List<OrderTestDto> getControlOrderList() {
        return orderTestMapper.selectAll();
    }
}
