package com.octopus.eureka.user;

import com.mysql.cj.util.StringUtils;
import com.octopus.common.bo.BuyBo;
import com.octopus.common.dao.domain.CustomerCifInfoDto;
import com.octopus.common.dao.domain.CustomerSignInfoDto;
import com.octopus.common.dao.mapper.CustomerCifInfoMapper;
import com.octopus.common.dao.mapper.CustomerSignInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

//客户预检查
@Component("preCheckService")
public class PreCheckService {
    private final static Logger logger = LoggerFactory.getLogger(PreCheckService.class);

    @Autowired
    private UserController customerCifInfoMapper;
    @Autowired
    private UserController customerSignInfoMapper;


    public int  doProcess(BuyBo buyBo){
        String customerId = buyBo.getCustomerId();
        CustomerCifInfoDto customerCifInfoDto = customerCifInfoMapper.findById(customerId);
        int resFlag = 0;
        if(customerCifInfoDto == null){
            resFlag = 0;

        }else{
            CustomerSignInfoDto customerSignInfoDto  = customerSignInfoMapper.getSignInfoById(customerId);
            if(customerSignInfoDto == null){
                resFlag = 10;
            }else{
                resFlag = 11;
            }
        }
        return resFlag;
    }
}
