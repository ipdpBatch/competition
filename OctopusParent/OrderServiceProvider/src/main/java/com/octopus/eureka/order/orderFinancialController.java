package com.octopus.eureka.order;

import com.octopus.eureka.order.Dao.ControlOrderDto;
import com.octopus.eureka.order.Dao.OrderFinancialDto;
import com.octopus.eureka.order.Dao.OrderFinancialMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author dongjiale
 * @Date 2019/9/26 11:59 PM
 * @Version 1.0
 */
@RestController
public class orderFinancialController {

    private final static Logger logger = LoggerFactory.getLogger(orderFinancialController.class);
    @Autowired
    OrderFinancialMapper orderFinancialMapper;

    @RequestMapping("/order/{orderSeq}")
    public OrderFinancialDto getOrder(@PathVariable("orderSeq") String orderSeq) {
        logger.info("请求参数orderSeq："+ orderSeq);
        return orderFinancialMapper.selectById(orderSeq);
    }

    @RequestMapping("/order/all")
    public List<OrderFinancialDto> getOrderList() {
        return orderFinancialMapper.selectAll();
    }

    @RequestMapping("/order/update")
    public int updateOrder(@RequestBody OrderFinancialDto orderFinancialDto) {
        return orderFinancialMapper.update(orderFinancialDto);
    }

    @RequestMapping("/order/add")
    public int addOrder(@RequestBody  OrderFinancialDto orderFinancialDto) {
        return orderFinancialMapper.insert(orderFinancialDto);
    }

    @RequestMapping("/order/delete/{orderSeq}")
    public int deleteOrder(@PathVariable("orderSeq") String orderSeq) {
        logger.info("请求参数orderSeq："+ orderSeq);
        return orderFinancialMapper.delete(orderSeq);
    }


}
