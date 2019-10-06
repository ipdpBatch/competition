package com.octopus.eureka.user;

import com.octopus.common.dao.domain.ControlUserDto;
import com.octopus.common.dao.mapper.ControlUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;


/**
 * 文件创建时写入注释内容
 *
 * @author gsj4877
 * @version 1.0.0
 * @date Created in 2019/10/6 10:17
 */
@RestController
public class ControlUserController {
    private final static Logger logger = LoggerFactory.getLogger(ControlUserController.class);

    @Value("${server.port}")
    String port;

    @Resource
    private ControlUserMapper controlUserMapper;

    @RequestMapping("/home")
    public String home() {
        return "Hello world ,port:" + port +". This is " + this.getClass().getName();
    }

    @GetMapping("/controlUser/{orderSeq}")
    public ControlUserDto getControlUser(@PathVariable("orderSeq") BigInteger orderSeq) {
        logger.info("请求参数orderSeq："+ orderSeq);
        return controlUserMapper.selectById(orderSeq);
    }

    @GetMapping("/controlUser/all")
    public List<ControlUserDto> getControlUserList() {
        return controlUserMapper.selectAll();
    }

    @RequestMapping("/controlUser/update")
    public int updateControlUser(@RequestBody ControlUserDto controlUserDto) {
        return controlUserMapper.update(controlUserDto);
    }

    @RequestMapping("/controlUser/add")
    public int addControlUser(@RequestBody ControlUserDto controlUserDto) {
        int result = controlUserMapper.insert(controlUserDto);
        logger.info("User层，插入后主键seq为："+controlUserDto.getOrderSeq());
        return result;
    }

    @RequestMapping("/controlUser/delete/{orderSeq}")
    public int deleteControlUser(@PathVariable("orderSeq") BigInteger orderSeq) {
        logger.info("请求参数orderSeq："+ orderSeq);
        return controlUserMapper.delete(orderSeq);
    }
}
