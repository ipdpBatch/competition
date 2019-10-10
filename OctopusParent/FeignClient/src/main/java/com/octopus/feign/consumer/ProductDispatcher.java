package com.octopus.feign.consumer;

import com.octopus.common.bo.BuyBo;
import com.octopus.common.bo.BuyResponseBo;
import com.octopus.common.dao.domain.ControlProductDto;
import com.octopus.common.dao.domain.ProductBaseInfoDto;
import com.octopus.eureka.product.ProductService;
import com.octopus.feign.consumer.provider.ProductClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.octopus.common.utils.DateUtil.formatTime;


@RestController
public class ProductDispatcher {
    private final static Logger logger = LoggerFactory.getLogger(ProductDispatcher.class);
//    @Autowired
//    private HomeClient homeClient;

    @Autowired
    private ProductClient productClient;


    //产品控制表
    public List<ControlProductDto> getControlProductList(){
        return  productClient.getControlProductList();
    }

    public ControlProductDto getControlProduct(BigInteger orderSeq) {
        return productClient.getControlProduct(orderSeq);
    }

    public int deleteControlProduct(BigInteger orderSeq){
        return  productClient.deleteControlProduct(orderSeq);
    }
    public int updateControlProduct(ControlProductDto controlProductDto){
        return productClient.updateControlProduct(controlProductDto);
    }
    public int addControlProduct(ControlProductDto controlProductDto){
        return productClient.addControlProduct(controlProductDto);
    }

    //产品信息表
    public List<ProductBaseInfoDto> selectAll(){
        return productClient.selectAll();
    }
    public ProductBaseInfoDto getProductById(String productId){
        return productClient.findById(productId);
    }
    public int deleteProduct(String productId){
        return productClient.deleteProduct(productId);
    }
    public int updateProduct(ProductBaseInfoDto productBaseInfoDto){
        return productClient.updateProduct(productBaseInfoDto);
    }

    public int insertProduct(ProductBaseInfoDto productBaseInfoDto){
        return productClient.insertProduct(productBaseInfoDto);
    }

    //产品检查
    public BuyResponseBo checkProduct(BuyBo buyBo) {
        return productClient.checkProduct(buyBo);
    }
}