package com.octopus.eureka.user;

import com.octopus.eureka.user.dao.CustomerDto;
import com.octopus.eureka.user.dao.UserMapper;
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
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @Value("${server.port}")
    String port;

    @RequestMapping("/user")
    public String home() {
        return "Hello world ,port:" + port + ". This is " + this.getClass().getName();
    }

    @GetMapping("/user/{id}")
    public CustomerDto findById(@PathVariable String id) {
        CustomerDto customerDto = userMapper.selectById(id);
        if (customerDto != null) {
            return customerDto;
        } else {
            return null;
        }
    }

    @GetMapping("/user/all")
    public List<CustomerDto> findAll() {
        return userMapper.selectAll();
    }

}
