package com.octopus.feign.consumer.provider;

import java.math.BigInteger;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.octopus.common.bo.BuyBo;
import com.octopus.common.bo.BuyResponseBo;
import com.octopus.common.dao.domain.ControlProductDto;
import com.octopus.common.dao.domain.ProductBaseInfoDto;

/**
 * @Author lyn
 * @Date 2019-10-2
 * @Version 1.0
 */
@FeignClient("eureka-provider-product")
public interface ProductClient {

    //控制表
    @RequestMapping(value = "/controlProduct/all", method = RequestMethod.GET)
    List<ControlProductDto> getControlProductList();

    @RequestMapping(value ="/controlProduct/{orderSeq}", method = RequestMethod.GET)
    ControlProductDto getControlProduct(@PathVariable("orderSeq") BigInteger orderSeq);

    @RequestMapping(value ="/controlProduct/delete/{orderSeq}",method = RequestMethod.DELETE)
    int deleteControlProduct(@PathVariable("orderSeq") BigInteger orderSeq);

    @RequestMapping(value ="/controlProduct/update",method = RequestMethod.POST)
    int updateControlProduct(@RequestBody ControlProductDto controlProductDto);

    @RequestMapping(value ="/controlProduct/add",method = RequestMethod.POST)
    int addControlProduct(@RequestBody ControlProductDto controlProductDto);

    //产品表
    @RequestMapping(value ="/product/all", method = RequestMethod.GET)
    List<ProductBaseInfoDto> selectAll();

    @RequestMapping(value ="/product/{id}", method = RequestMethod.GET)
    ProductBaseInfoDto findById(@PathVariable("id") String id);

    @RequestMapping(value ="/product/delete/{productId}", method = RequestMethod.DELETE)
    int deleteProduct(@PathVariable("productId") String productId);

    @RequestMapping(value = "/product/update",method = RequestMethod.POST)
    int updateProduct(@RequestBody ProductBaseInfoDto productBaseInfoDto);

    @RequestMapping(value = "/product/insert",method = RequestMethod.POST)
    int insertProduct(@RequestBody ProductBaseInfoDto productBaseInfoDto);

    @RequestMapping(value = "/product/checkProduct",method = RequestMethod.GET)
    BuyResponseBo checkProduct(@RequestBody BuyBo buyBo);

}
