package com.octopus.eureka.user;

import com.octopus.common.bo.BuyBo;
import com.octopus.common.bo.BuyResponseBo;
import com.octopus.common.dao.domain.CustomerCifInfoDto;
import com.octopus.common.dao.domain.CustomerSignInfoDto;
import com.octopus.common.dao.mapper.CustomerCifInfoMapper;
import com.octopus.common.dao.mapper.CustomerSignInfoMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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

    @Resource
    PreCheckService preCheckService;

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

    @GetMapping("/user/sign/{customerId}")
    public CustomerSignInfoDto getSignInfoById(@PathVariable String customerId){
        CustomerSignInfoDto  customerSignInfoDto = customerSignInfoMapper.selectByCusid(customerId);
        return customerSignInfoDto;
    }

    @PostMapping("/user/sign/insert")
    public int insertSignInfo(@RequestBody CustomerSignInfoDto customerSignInfoDto){
        try{
            int result = customerSignInfoMapper.insert(customerSignInfoDto);
            return result;
        }catch (Exception e){
            return 0;
        }

    }

    @PostMapping("/user/sign/updateByid")
    public int updateSignInfo(@RequestBody CustomerSignInfoDto customerSignInfoDto){
        return customerSignInfoMapper.update(customerSignInfoDto);
    }

    @PostMapping("/user/precheck")
    public BuyResponseBo preCheck(@RequestBody BuyBo buybo){
        int result = preCheckService.doProcess(buybo);
        BuyResponseBo buyResponseBo = new BuyResponseBo();
        BeanUtils.copyProperties(buybo, buyResponseBo);
        switch (result){
            case 0:
                buyResponseBo.setOrderReturnCode(false);
                buyResponseBo.setErrorDetail("用户不存在");
                break;
            case 10:
                buyResponseBo.setOrderReturnCode(false);
                buyResponseBo.setErrorDetail("用户未签约");
                break;
            case 11:
                buyResponseBo.setOrderReturnCode(true);
                buyResponseBo.setErrorDetail("客户预检查成功");
                break;
            default:
                buyResponseBo.setOrderReturnCode(false);
                buyResponseBo.setErrorDetail("返回值不合法");
        }
        return buyResponseBo;
    }

}
