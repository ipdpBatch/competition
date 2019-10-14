package com.octopus.feign.consumer;

import com.octopus.common.bo.BuyBo;
import com.octopus.common.bo.BuyResponseBo;
import com.octopus.common.dao.domain.ControlOrderDto;
import com.octopus.common.dao.domain.OrderFinancialDto;
import com.octopus.common.dao.domain.ProductQuotaInfo;
import com.octopus.feign.consumer.provider.OrderClient;
import com.octopus.feign.consumer.provider.QuotaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;


@RestController
public class QuotaDispatcher {

    private final static Logger logger = LoggerFactory.getLogger(QuotaDispatcher.class);

    @Autowired
    private QuotaClient quotaClient;

    ProductQuotaInfo checkQuota(BuyBo buyBo) {
        return quotaClient.checkQuota(buyBo);
    }
}