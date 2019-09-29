package com.octopus.eureka;

import com.octopus.common.dao.domain.ProductBaseInfoDto;
import com.octopus.common.dao.mapper.ProductBaseInfoMapper;
import com.octopus.eureka.product.ProductServiceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ProductServiceApplication.class)
public class ProductServiceApplicationTests {

    @Resource
    private ProductBaseInfoMapper productBseInfoMapper;

    @Test
    public void test(){
        float productRaiseAmount = new Float(10000.00);
        float productRemainAmount = new  Float(100000.00);
        ProductBaseInfoDto product = new ProductBaseInfoDto("000539", "01", "250", productRaiseAmount,productRemainAmount,"KZ", "Y3", "Y3");
        int i = productBseInfoMapper.insert(product);
        System.out.println(i+"rows have been inserted!!");

    }

}
