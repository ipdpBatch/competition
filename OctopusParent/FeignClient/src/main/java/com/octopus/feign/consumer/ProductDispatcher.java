package com.octopus.feign.consumer;

import com.octopus.common.dao.domain.ControlProductDto;
import com.octopus.common.dao.domain.ProductBaseInfoDto;
import com.octopus.feign.consumer.provider.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;


@RestController
public class ProductDispatcher {
//    @Autowired
//    private HomeClient homeClient;
    @Autowired
    private ProductClient productClient;

//    public String findAllUsers(){
//        return homeClient.findAllUser();
//    }
//    public String findById(String id){
//        return homeClient.findById(id);
//    }

//    @Autowired
//    private HomeClient homeClient;
//
//    public String findAllUsers(){
//        return homeClient.findAllUser();
//    }
//    public String findById(String id){
//        return homeClient.findById(id);
//    }


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

}