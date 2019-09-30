package com.octopus.feign.consumer.provider;

import com.octopus.common.dao.domain.ProductBaseInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @Author dongjiale
 * @Date 2019/9/27 7:35 PM
 * @Version 1.0
 */
@FeignClient("eureka-provider-product")
public interface ProductClient {

    //控制表

    //产品表
    @RequestMapping(value ="/product/all", method = RequestMethod.GET)
    List<ProductBaseInfoDto> selectAll();

    @RequestMapping(value ="/product/{id}", method = RequestMethod.GET)
    ProductBaseInfoDto findById(@PathVariable("id") String id);

    @RequestMapping(value ="/product/delete/{productId}", method = RequestMethod.DELETE)
    int deleteProduct(@PathVariable("productId") String productId);

    @RequestMapping(value = "/product/update/",method = RequestMethod.POST)
    int updateProduct(@RequestBody ProductBaseInfoDto productBaseInfoDto);

    @RequestMapping(value = "/product/insert",method = RequestMethod.POST)
    int insertProduct(@RequestBody ProductBaseInfoDto productBaseInfoDto);

}
