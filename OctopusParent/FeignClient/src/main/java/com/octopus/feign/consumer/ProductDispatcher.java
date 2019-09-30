package com.octopus.feign.consumer;

import com.octopus.common.dao.domain.ControlOrderDto;
import com.octopus.common.dao.domain.ProductBaseInfoDto;
import com.octopus.feign.consumer.provider.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

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


//    //订单控制表
//    public List<ControlOrderDto> getControlOrderList(){
//        return  productClient;
//    }
//
//    public ControlOrderDto getControlOrder(String orderSeq) {
//        return productClient.getControlOrder(orderSeq);
//    }
//
//    public int deleteControlOrder(String orderSeq){
//        return  productClient.deleteControlOrder(orderSeq);
//    }
//    public int updateControlOrder(ControlOrderDto controlOrderDto){
//        return productClient.updateControlOrder(controlOrderDto);
//    }
//    public int addControlOrder(ControlOrderDto controlOrderDto){
//        return productClient.addControlOrder(controlOrderDto);
//    }

    //订单表
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
//    update不通
    public int insertProduct(ProductBaseInfoDto productBaseInfoDto){
        return productClient.insertProduct(productBaseInfoDto);
    }

}