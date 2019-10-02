package com.octopus.eureka.user;

import com.mysql.cj.util.StringUtils;
import com.octopus.common.bo.BuyBo;
import com.octopus.common.dao.domain.CustomerCifInfoDto;
import com.octopus.common.dao.domain.CustomerSignInfoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

//客户预检查
@Component("preCheckService")
public class PreCheckService {
    private final static Logger logger = LoggerFactory.getLogger(PreCheckService.class);

    @Resource
    UserController userController;
    public int  doProcess(BuyBo buyBo){
        String customerId = buyBo.getCustomerId();
        CustomerCifInfoDto customerCifInfoDto = userController.findById(customerId);
        int cifFlag = 0;
        if(customerCifInfoDto == null){
            cifFlag = 0;
        }else{
            cifFlag = 1;
        }
        CustomerSignInfoDto customerSignInfoDto  = userController.getSignInfoById(customerId);
        int signFlag = 0;
        if(customerSignInfoDto == null){
            signFlag = 0;
        }else{
            signFlag = 1;
        }
        return signFlag*cifFlag;
    }
}
