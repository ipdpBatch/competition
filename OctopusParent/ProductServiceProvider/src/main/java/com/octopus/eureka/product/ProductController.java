package com.octopus.eureka.product;

import com.octopus.common.dao.domain.ProductBaseInfoDto;
import com.octopus.common.dao.mapper.ProductBaseInfoMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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
    private ProductBaseInfoMapper productBaseInfoMapper;
    @Value("${server.port}")
    String port;
    @RequestMapping("/product")
    public String home() {
        return "Hello world ,port:" + port +". This is " + this.getClass().getName();
    }

    @GetMapping("/product/{id}")
    public ProductBaseInfoDto findById(@PathVariable String id) {
        ProductBaseInfoDto productBaseInfoDto = productBaseInfoMapper.selectById(id);
        if (productBaseInfoDto != null) {
            return productBaseInfoDto;
        } else {
            return null;
        }
    }

    @GetMapping("/product/all")
    public List<ProductBaseInfoDto> findAll() {
        return productBaseInfoMapper.selectAll();
    }

    @RequestMapping("/product/delete/{productId}")
    public Long deleteProduct(@PathVariable("productId") String productId){
        return productBaseInfoMapper.delete(productId);
    }
//update 未通
    @RequestMapping("/product/update")
    public int updateProduct(@RequestBody ProductBaseInfoDto productBaseInfoDto){
        return productBaseInfoMapper.update(productBaseInfoDto);
    }

    @RequestMapping("/product/insert")
    public int insertProduct(@RequestBody ProductBaseInfoDto productBaseInfoDto) {
        return productBaseInfoMapper.insert(productBaseInfoDto);
    }
}
