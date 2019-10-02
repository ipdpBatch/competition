package com.octopus.eureka.user;

import com.octopus.common.bo.BuyBo;
import com.octopus.common.dao.domain.CustomerCifInfoDto;
import com.octopus.common.dao.domain.CustomerSignInfoDto;
import com.octopus.common.dao.mapper.CustomerCifInfoMapper;
import com.octopus.common.dao.mapper.CustomerSignInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
    private CustomerCifInfoMapper customerCifInfoMapper;

    @Autowired
    private CustomerSignInfoMapper customerSignInfoMapper;

/*    @Resource
    PreCheckService preCheckService;*/

    @Value("${server.port}")
    String port;

    @RequestMapping("/user")
    public String home() {
        return "Hello world ,port:" + port + ". This is " + this.getClass().getName();
    }

    @GetMapping("/user/{id}")
    public CustomerCifInfoDto findById(@PathVariable String id) {
        CustomerCifInfoDto customerCifInfoDto = customerCifInfoMapper.selectById(id);
        if (customerCifInfoDto != null) {
            return customerCifInfoDto;
        } else {
            return null;
        }
    }

    @GetMapping("/user/all")
    public List<CustomerCifInfoDto> findAll() {
        return customerCifInfoMapper.selectAll();
    }

    @GetMapping("/user/sign/{id}")
    public CustomerSignInfoDto getSignInfoById(@PathVariable String customerId){
        CustomerSignInfoDto  customerSignInfoDto = customerSignInfoMapper.selectByCusid(customerId);
        return customerSignInfoDto;
    }

   /* @GetMapping("/user/precheck/{buybo}")
    public int preCheck(@PathVariable BuyBo buybo){
        return preCheckService.doProcess(buybo);
    }*/

}
