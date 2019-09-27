package com.octopus.eureka;

import com.octopus.eureka.product.ProductServiceApplication;
import com.octopus.eureka.product.dao.ProductBseInfoDto;
import com.octopus.eureka.product.dao.ProductBseInfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ProductServiceApplication.class)
public class ProductServiceApplicationTests {

    @Resource
    private ProductBseInfoMapper productBseInfoMapper;

    @Test
    public void test(){
        float productRaiseAmount = new Float(10000.00);
        float productRemainAmount = new  Float(100000.00);
        ProductBseInfoDto product = new ProductBseInfoDto("11111111", "01", "250", productRaiseAmount,productRemainAmount,"KZ", "Y3", "Y3");
        int i = productBseInfoMapper.insert(product);
        System.out.println(i+"rows have been inserted!!");

    }

}
