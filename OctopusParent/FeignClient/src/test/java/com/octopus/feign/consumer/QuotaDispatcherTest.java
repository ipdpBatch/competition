package com.octopus.feign.consumer;

import com.octopus.common.bo.BuyBo;
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
        BuyBo buyBo = new BuyBo();
        buyBo.setProductId(productId);
        buyBo.setTransactionAmount(volume);
        ProductQuotaInfo productQuotaInfo = quotaDispatcher.checkQuota(buyBo);
        System.out.println(productQuotaInfo.toString());
        assert true;
    }
}
