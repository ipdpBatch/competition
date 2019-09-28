package com.octopus.eureka.product;

import com.octopus.eureka.product.dao.ProductBseInfoDto;
import com.octopus.eureka.product.dao.ProductBseInfoMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class ProductController {
    @Resource
    private ProductBseInfoMapper productBseInfoMapper;
    @Value("${server.port}")
    String port;
    @RequestMapping("/product")
    public String home() {
        return "Hello world ,port:" + port +". This is " + this.getClass().getName();
    }

    @GetMapping("/user/{id}")
    public ProductBseInfoDto findById(@PathVariable String id) {
        ProductBseInfoDto productBseInfoDto = productBseInfoMapper.selectById(id);
        if (productBseInfoDto != null) {
            return productBseInfoDto;
        } else {
            return null;
        }
    }

    @GetMapping("/user/all")
    public List<ProductBseInfoDto> findAll() {
        return productBseInfoMapper.selectAll();
    }
}
