package com.octopus.eureka.product;

import com.octopus.common.dao.domain.ControlProductDto;
import com.octopus.common.dao.mapper.ControlProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author lyn
 * @version 1.0.0
 * @date 
 */
@RestController
public class ProductController {

    private final static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Value("${server.port}")
    String port;

    @Resource
    private ControlProductMapper controlProductMapper;

    @RequestMapping("/home")
    public String home() {
        return "Hello world ,port:" + port +". This is " + this.getClass().getName();
    }

    @GetMapping("/controlProduct/{orderSeq}")
    public ControlProductDto getControlProduct(@PathVariable("orderSeq") BigInteger orderSeq) {
        logger.info("请求参数orderSeq："+ orderSeq);
        return controlProductMapper.selectById(orderSeq);
    }

    @GetMapping("/controlProduct/all")
    public List<ControlProductDto> getControlProductList() {
        return controlProductMapper.selectAll();
    }

    @RequestMapping("/controlProduct/update")
    public int updateControlProduct(@RequestBody ControlProductDto controlProductDto) {
        return controlProductMapper.update(controlProductDto);
    }

    @RequestMapping("/controlProduct/add")
    public int addControlProduct(@RequestBody ControlProductDto controlProductDto) {
        int result = controlProductMapper.insert(controlProductDto);
        logger.info("order层，插入后主键seq为："+controlProductDto.getOrderSeq());
        return result;
    }

    @RequestMapping("/controlProduct/delete/{orderSeq}")
    public int deleteControlProduct(@PathVariable("orderSeq") BigInteger orderSeq) {
        logger.info("请求参数orderSeq："+ orderSeq);
        return controlProductMapper.delete(orderSeq);
    }
}
