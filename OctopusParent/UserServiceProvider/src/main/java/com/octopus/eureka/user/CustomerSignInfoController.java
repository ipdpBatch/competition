package com.octopus.eureka.user;

import com.octopus.common.dao.domain.CustomerSignInfoDto;
import com.octopus.common.dao.mapper.CustomerSignInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author lyn
 * @Date 2019-9-27 21:22:37
 * @Version 1.0
 */
@RestController
public class CustomerSignInfoController {
    @Autowired
    CustomerSignInfoMapper customerSignInfoMapper;

    @RequestMapping("/user/customerSignInfo/{customerId}/{productId}")
    public CustomerSignInfoDto getCustomerSignInfo(@PathVariable String customerId,@PathVariable String productId) {
        return customerSignInfoMapper.selectByPrimaryKey(customerId,productId);
    }

    @RequestMapping("/user/customerSignInfo/all")
    public List<CustomerSignInfoDto> getCustomerSignInfoListAll() {
        return customerSignInfoMapper.selectAll();
    }

    @RequestMapping("/user/customerSignInfo/update")
    public int updateCustomerSignInfo(@RequestBody CustomerSignInfoDto customerSignInfoDto) {
        return customerSignInfoMapper.update(customerSignInfoDto);
    }

    @RequestMapping("/user/customerSignInfo/insert")
    public int insertCustomerSignInfo(@RequestBody  CustomerSignInfoDto customerSignInfoDto) {
        return customerSignInfoMapper.insert(customerSignInfoDto);
    }

    @RequestMapping("/user/customerSignInfo/delete/{customerId}/{productId}")
    public Long deleteCustomerSignInfo(@PathVariable String customerId, @PathVariable String productId) {
        return customerSignInfoMapper.delete(customerId,productId);
    }

}
