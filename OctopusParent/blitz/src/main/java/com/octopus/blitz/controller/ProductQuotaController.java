package com.octopus.blitz.controller;

import com.octopus.blitz.dto.ProductBaseInfo;
import com.octopus.blitz.dto.ProductQuotaInfo;
import com.octopus.blitz.repository.ProductBaseInfoRepository;
import com.octopus.blitz.repository.ProductQuotaInfoRepository;
import com.octopus.common.bo.BuyBo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;


@Slf4j
@RestController
@RequestMapping(value = "quota")
public class ProductQuotaController {
    private static final Logger logger = LoggerFactory.getLogger(ProductQuotaController.class);
    private static final int SLEEP_MILLIS = 10;


    @Autowired
    private ProductQuotaInfoRepository productQuotaInfoRepository;
    @Autowired
    private ProductBaseInfoRepository productBaseInfoRepository;

    @RequestMapping(value = "/process")
    @Transactional
    public ProductQuotaInfo checkQuota(@RequestBody BuyBo buyBo) throws InterruptedException {
        return checkQuotaWithTransactionAndUpdateFirstWithCache(buyBo.getProductId(), buyBo.getTransactionAmount());
    }


    @RequestMapping(value = "/v1/{productId}/{volume}")
    @Transactional
    public ProductQuotaInfo checkQuotaWithoutTransaction(@PathVariable String productId, @PathVariable BigDecimal volume) throws InterruptedException {
        // STEP 1: 读取产品基本信息
        ProductBaseInfo productBaseInfo = productBaseInfoRepository.getOne(productId);
        logger.info(productBaseInfo.toString());


        ProductQuotaInfo productQuotaInfo = productQuotaInfoRepository.getOne(productId);
        BigDecimal surplusVolume = productQuotaInfo.getSurplusVolume();
        productQuotaInfo.setSurplusVolume(surplusVolume.subtract(volume));
        Thread.sleep(SLEEP_MILLIS);
        productQuotaInfoRepository.save(productQuotaInfo);
        logger.info(productQuotaInfo.toString());
        return productQuotaInfo;
    }

    @RequestMapping(value = "/v2/{productId}/{volume}")
    @Transactional
    public ProductQuotaInfo checkQuotaWithTransactionAndGetForUpdate(@PathVariable String productId, @PathVariable BigDecimal volume) throws InterruptedException {
        // STEP 1: 读取产品基本信息
        ProductBaseInfo productBaseInfo = productBaseInfoRepository.getOne(productId);
        logger.info(productBaseInfo.toString());

        ProductQuotaInfo productQuotaInfo = productQuotaInfoRepository.getOneForUpdate(productId);
        productQuotaInfo.setProductId(productId);
        productQuotaInfo.setSurplusVolume(productQuotaInfo.getSurplusVolume().subtract(volume));

        Thread.sleep(SLEEP_MILLIS);
        productQuotaInfoRepository.save(productQuotaInfo);
        productQuotaInfo = productQuotaInfoRepository.getOne(productId);
        logger.info(productQuotaInfo.toString());
        return productQuotaInfo;
    }

    @RequestMapping(value = "/v3/{productId}/{volume}")
    @Transactional
    public ProductQuotaInfo checkQuotaWithTransactionAndUpdateFirst(@PathVariable String productId, @PathVariable BigDecimal volume) throws InterruptedException {
        // STEP 1: 读取产品基本信息
        ProductBaseInfo productBaseInfo = productBaseInfoRepository.getOne(productId);
        logger.info(productBaseInfo.toString());

        productQuotaInfoRepository.substractSurplusVolume(productId, volume);
        Thread.sleep(SLEEP_MILLIS);
        ProductQuotaInfo productQuotaInfo = productQuotaInfoRepository.getOne(productId);
        logger.info(productQuotaInfo.toString());
        return productQuotaInfo;
    }

    @RequestMapping(value = "/v4/{productId}/{volume}")
    @Transactional
    public ProductQuotaInfo checkQuotaWithTransactionAndUpdateFirstWithCache(@PathVariable String productId, @PathVariable BigDecimal volume) throws InterruptedException {
        // STEP 1: 读取产品基本信息
        ProductBaseInfo productBaseInfo = productBaseInfoRepository.getByProductId(productId);
        logger.info(productBaseInfo.toString());

        productQuotaInfoRepository.substractSurplusVolume(productId, volume);
        Thread.sleep(SLEEP_MILLIS);
        ProductQuotaInfo productQuotaInfo = productQuotaInfoRepository.getOne(productId);
        logger.info(productQuotaInfo.toString());
        return productQuotaInfo;
    }

    @RequestMapping(value = "/v5/{productId}/{volume}")
    @Transactional
    public ProductQuotaInfo checkQuotaWithTransactionAndUpdateFirstWithCacheAndUsingZookeeper(@PathVariable String productId, @PathVariable BigDecimal volume) throws InterruptedException {
//        boolean isSoldOut = SoldOutWatcher.isSoldOut(productId);
//        if (isSoldOut) {
//            return productQuotaInfoRepository.getOne(productId);
//        }
        // STEP 1: 读取产品基本信息
        ProductBaseInfo productBaseInfo = productBaseInfoRepository.getByProductId(productId);
        logger.info(productBaseInfo.toString());

        productQuotaInfoRepository.substractSurplusVolume(productId, volume);
        Thread.sleep(SLEEP_MILLIS);
        ProductQuotaInfo productQuotaInfo = productQuotaInfoRepository.getOne(productId);
        logger.info(productQuotaInfo.toString());
        return productQuotaInfo;
    }
}
