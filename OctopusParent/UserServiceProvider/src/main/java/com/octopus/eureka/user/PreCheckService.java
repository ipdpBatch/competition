package com.octopus.eureka.user;

import com.mysql.cj.util.StringUtils;
import com.octopus.common.bo.BuyBo;
import com.octopus.common.bo.BuyResponseBo;
import com.octopus.common.dao.domain.ControlUserDto;
import com.octopus.common.dao.domain.CustomerCifInfoDto;
import com.octopus.common.dao.domain.CustomerSignInfoDto;
import com.octopus.common.dao.mapper.ControlUserMapper;
import com.octopus.common.dao.mapper.CustomerCifInfoMapper;
import com.octopus.common.dao.mapper.CustomerSignInfoMapper;
import com.octopus.common.dao.mapper.PositionBalanceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

import static com.octopus.common.utils.DateUtil.formatTime;

//客户预检查
@Component("preCheckService")
public class PreCheckService {
    private final static Logger logger = LoggerFactory.getLogger(PreCheckService.class);

    @Autowired
    private UserController customerCifInfoMapper;
    @Autowired
    private UserController customerSignInfoMapper;
    @Autowired
    private ControlUserMapper controlUserMapper;



    public int  doProcess(BuyBo buyBo){
        logger.info("开始进行用户信息检查!!"+buyBo.toString());
        //插入控制表
        int resFlag = 0;
        ControlUserDto controlUserDto = new ControlUserDto();
        controlUserDto.setOrderSeq(buyBo.getOrderSeq());
        controlUserDto.setOrderStep(buyBo.getOrderStep());
        controlUserDto.setRequestTime(formatTime(new Date()));
        controlUserDto.setUpdateTime(formatTime(new Date()));
        controlUserDto.setStepStatus("INIT");
        int i = controlUserMapper.insert(controlUserDto);
        if (i != 1) {
            resFlag=22;
            logger.info("插入用户控制表失败!!");
            return resFlag;
        }
        String customerId = buyBo.getCustomerId();
        CustomerCifInfoDto customerCifInfoDto = customerCifInfoMapper.findById(customerId);
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
        controlUserDto.setUpdateTime(formatTime(new Date()));
        controlUserDto.setStepStatus("PCSC");
        controlUserDto.setOrderStep(buyBo.getOrderStep());
        int j=controlUserMapper.update(controlUserDto);
        logger.info("用户检查步骤完成!!");
        return resFlag;
    }
}
