package com.octopus.feign.consumer;

import com.octopus.common.dao.domain.ProductQuotaInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ConsumerApplication.class)
public class QuotaDispatcherTest {

    @Autowired
    QuotaDispatcher quotaDispatcher;
    @Test
    public void checkQuota() {
        String productId = "000539";
        BigDecimal volume = new BigDecimal("0.01");
        ProductQuotaInfo productQuotaInfo = quotaDispatcher.checkQuota(productId, volume);
        System.out.println(productQuotaInfo.toString());
    }
}
