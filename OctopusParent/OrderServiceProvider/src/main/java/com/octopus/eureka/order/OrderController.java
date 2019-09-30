package com.octopus.eureka.order;

import com.octopus.common.dao.mapper.ControlOrderMapper;
import com.octopus.common.dao.domain.ControlOrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文件创建时写入注释内容
 *
 * @author zp3778
 * @version 1.0.0
 * @date Created in 17:08 2019/9/23
 */
@RestController
public class OrderController {

    private final static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Value("${server.port}")
    String port;

    @Autowired
    ControlOrderMapper controlOrderMapper;

    @RequestMapping("/home")
    public String home() {
        return "Hello world ,port:" + port +". This is " + this.getClass().getName();
    }

    @GetMapping("/controlOrder/{orderSeq}")
    public ControlOrderDto getControlOrder(@PathVariable("orderSeq") String orderSeq) {
        logger.info("请求参数orderSeq："+ orderSeq);
        return controlOrderMapper.selectById(orderSeq);
    }

    @GetMapping("/controlOrder/all")
    public List<ControlOrderDto> getControlOrderList() {
        return controlOrderMapper.selectAll();
    }

    @RequestMapping("/controlOrder/update")
    public int updateControlOrder(@RequestBody ControlOrderDto controlOrderDto) {
        return controlOrderMapper.update(controlOrderDto);
    }

    @RequestMapping("/controlOrder/add")
    public int addControlOrder(@RequestBody ControlOrderDto controlOrderDto) {
        int result = controlOrderMapper.insert(controlOrderDto);
        logger.info("order层，插入后主键seq为："+controlOrderDto.getOrderSeq());
        return result;
    }

    @RequestMapping("/controlOrder/delete/{orderSeq}")
    public int deleteControlOrder(@PathVariable("orderSeq")  double orderSeq) {
        logger.info("请求参数orderSeq："+ orderSeq);
        return controlOrderMapper.delete(orderSeq);
    }
}
