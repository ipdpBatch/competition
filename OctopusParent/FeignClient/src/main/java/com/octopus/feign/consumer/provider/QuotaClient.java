package com.octopus.feign.consumer.provider;

import com.octopus.common.bo.BuyBo;
import com.octopus.common.bo.BuyResponseBo;
import com.octopus.common.dao.domain.ControlOrderDto;
import com.octopus.common.dao.domain.OrderFinancialDto;
import com.octopus.common.dao.domain.ProductQuotaInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * @Author tiansheng
 * @Date 2019/9/27 7:31 PM
 * @Version 1.0
 */
@FeignClient("eureka-provider-blitz")
public interface QuotaClient {

    @RequestMapping(value = "/quota/process", method = RequestMethod.POST)
    ProductQuotaInfo checkQuota(@RequestBody BuyBo buyBo);

}
