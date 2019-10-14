package com.octopus.eureka.user;

import com.octopus.common.dao.domain.CustomerSignInfoDto;
import com.octopus.common.utils.DateUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("customerSignService")
public class CustomerSignService
{
    @Resource
    UserController userController;

    public boolean doProcess(String customerId) {

        CustomerSignInfoDto customerSignInfoDto = new CustomerSignInfoDto();
        customerSignInfoDto.setCustomerId(customerId);
        customerSignInfoDto.setSignStatus("SUCC");
        customerSignInfoDto.setTransactionDate(DateUtil.getNowToday());
        customerSignInfoDto.setTransactionTime(DateUtil.getNowTime());
        int resultInsert = userController.insertSignInfo(customerSignInfoDto);
        if (resultInsert == 1) {
            return true;
        } else {
            int resultUpdate = userController.updateSignInfo(customerSignInfoDto);
            return resultUpdate == 1;
        }
    }
}
