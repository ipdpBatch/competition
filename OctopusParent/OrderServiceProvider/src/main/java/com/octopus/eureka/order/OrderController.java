package com.octopus.eureka.order;

import com.octopus.eureka.order.Dao.ContolOrderMapper;
import com.octopus.eureka.order.Dao.ControlOrderDto;
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

    @Value("${server.port}")
    String port;

    @Autowired
    ContolOrderMapper contolOrderMapper;

    @RequestMapping("/home")
    public String home() {
        return "Hello world ,port:" + port +". This is " + this.getClass().getName();
    }
    @RequestMapping("/controlOrder/{orderSeq}")
    public ControlOrderDto getControlOrder(@PathVariable String orderSeq) {
        return contolOrderMapper.selectById(orderSeq);
    }

    @RequestMapping("/controlOrder/all")
    public List<ControlOrderDto> getControlOrderList() {
        return contolOrderMapper.selectAll();
    }

    @RequestMapping("/controlOrder/update/{controlOrderDto}")
    public int updateControlOrder(@PathVariable ControlOrderDto controlOrderDto) {
        return contolOrderMapper.update(controlOrderDto);
    }

    @RequestMapping("/controlOrder/add/{controlOrderDto}")
    public int addControlOrder(@PathVariable ControlOrderDto controlOrderDto) {
        return contolOrderMapper.insert(controlOrderDto);
    }

    @RequestMapping("/controlOrder/delete/{orderSeq}")
    public int deleteControlOrder(@PathVariable String orderSeq) {
        return contolOrderMapper.delete(orderSeq);
    }
}
