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
import com.octopus.common.dao.domain.ControlCenterDto;
import com.octopus.common.dao.mapper.ControlCenterMapper;


@RestController
public class CenterController {
	private final static Logger logger = LoggerFactory.getLogger(CenterController.class);

    @Value("${server.port}")
    String port;
	
    @Autowired
    ControlCenterMapper controlCenterMapper;
    
    @GetMapping("/controlCentrue/{orderSeq}")
    public ControlCenterDto getControlCenture(@PathVariable("orderSeq") BigInteger orderSeq) {
        logger.info("请求参数orderSeq："+ orderSeq);
        return controlCenterMapper.selectById(orderSeq);
    }
    
    @GetMapping("/controlCentrue/all")
    public List<ControlCenterDto> getControlCentureList() {
        return controlCenterMapper.selectAll();
    }
    
    @RequestMapping("/controlCentrue/update")
    public int updateControlCentrue(@RequestBody ControlCenterDto controlCenterDto) {
        return controlCenterMapper.update(controlCenterDto);
    }

    @RequestMapping("/controlCentrue/add")
    public int addControlCentrue(@RequestBody ControlCenterDto controlCenterDto) {
        int result = controlCenterMapper.insert(controlCenterDto);
        logger.info("插入后主键seq为："+ controlCenterDto.getOrderSeq());
        return result;
    }

    @RequestMapping("/controlCentrue/delete/{orderSeq}")
    public int deleteControlCentrue(@PathVariable("orderSeq") BigInteger orderSeq) {
        logger.info("请求参数orderSeq："+ orderSeq);
        return controlCenterMapper.delete(orderSeq);
    }
    
    

    
    
}
