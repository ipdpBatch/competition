package com.octopus.eureka.control;

import java.math.BigInteger;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.octopus.common.dao.domain.ControlCentureDto;
import com.octopus.common.dao.mapper.ControlCentureMapper;


@RestController
public class CentureController {
	private final static Logger logger = LoggerFactory.getLogger(CentureController.class);

    @Value("${server.port}")
    String port;
	
    @Autowired
    ControlCentureMapper controlCentureMapper;
    
    @GetMapping("/controlCentrue/{orderSeq}")
    public ControlCentureDto getControlCenture(@PathVariable("orderSeq") BigInteger orderSeq) {
        logger.info("请求参数orderSeq："+ orderSeq);
        return controlCentureMapper.selectById(orderSeq);
    }
    
    @GetMapping("/controlCentrue/all")
    public List<ControlCentureDto> getControlCentureList() {
        return controlCentureMapper.selectAll();
    }
    
    @RequestMapping("/controlCentrue/update")
    public int updateControlCentrue(@RequestBody ControlCentureDto controlCentureDto) {
        return controlCentureMapper.update(controlCentureDto);
    }

    @RequestMapping("/controlCentrue/add")
    public int addControlCentrue(@RequestBody ControlCentureDto controlCentureDto) {
        int result = controlCentureMapper.insert(controlCentureDto);
        logger.info("插入后主键seq为："+controlCentureDto.getOrderSeq());
        return result;
    }

    @RequestMapping("/controlCentrue/delete/{orderSeq}")
    public int deleteControlCentrue(@PathVariable("orderSeq") BigInteger orderSeq) {
        logger.info("请求参数orderSeq："+ orderSeq);
        return controlCentureMapper.delete(orderSeq);
    }
    
    

    
    
}
