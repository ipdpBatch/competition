package com.octopus.eureka.order.controller;

import com.octopus.common.dao.domain.CustomerCifInfoDto;
import com.octopus.common.dao.domain.OrderTestDto;
import com.octopus.common.dao.mapper.CustomerSignInfoMapper;
import com.octopus.common.dao.mapper.OrderTestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 文件创建时写入注释内容
 *
 * @author zp3778
 * @version 1.0.0
 * @date Created in 17:08 2019/9/23
 */
@Controller
public class OrderTestController {

    private final static Logger logger = LoggerFactory.getLogger(OrderTestController.class);

    @Value("${server.port}")
    String port;

    @Autowired
    OrderTestMapper orderTestMapper;

    @RequestMapping("/ordertest")
    public String home() {
        return "Hello world ,port:" + port + ". This is " + this.getClass().getName();
    }

    @GetMapping("/ordertest/{orderId}")
    @ResponseBody
    public OrderTestDto getControlOrder(@PathVariable("orderId") String orderId) {
        logger.info("orderId：" + orderId);
        return orderTestMapper.selectById(orderId);
    }

    @GetMapping("/ordertest/all")
    @ResponseBody
    public List<OrderTestDto> getControlOrderList() {
        return orderTestMapper.selectAll();
    }

    @GetMapping("/ordertest/input")
    public String greetingForm(Model model) {
        model.addAttribute("ordertestdto", new OrderTestDto());
        return "ordertestinput";
    }

    @PostMapping("/ordertest/input")
    public String greetingSubmit(@ModelAttribute OrderTestDto orderTestDto) {
        int res = 0;
        try {
            res = orderTestMapper.insertOrderTest(orderTestDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (res == 1) {
            return "ordertestoutput";
        } else {
            return "ordertesterror";
        }
    }

    @RequestMapping(value = "/user/set", method = RequestMethod.GET)
    public String set(Model model) {
        Set<String> allNames = new HashSet<String>() ;
        List<Integer> allIds = new ArrayList<Integer>() ;
        for (int x = 0 ; x < 5 ; x ++) {
            allNames.add("boot-" + x) ;
            allIds.add(x) ;
        }
        model.addAttribute("names", allNames) ;
        model.addAttribute("ids", allIds) ;
        model.addAttribute("mydate", new java.util.Date()) ;
        return "user_set" ;
    }

    @RequestMapping(value = "/inner", method = RequestMethod.GET)
    public String inner(HttpServletRequest request, Model model) {
        request.setAttribute("requestMessage", "springboot-request");
        request.getSession().setAttribute("sessionMessage", "springboot-session");
        request.getServletContext().setAttribute("applicationMessage",
                "springboot-application");
        model.addAttribute("url", "www.baidu.cn");
        request.setAttribute("url2",
                "<span style='color:red'>www.baidu.cn</span>");
        return "show_inner";
    }

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<OrderTestDto> allMembers = new ArrayList<OrderTestDto>();
        /*OrderTestDto dto = new OrderTestDto("dssf","121434");
        OrderTestDto dto1 = new OrderTestDto("dssfdsfsdd","sfdsgs");
        allMembers.add(dto);
        allMembers.add(dto1);*/
        allMembers = orderTestMapper.selectAll();
        model.addAttribute("allUsers", allMembers);
        return "order_list";
    }
}
