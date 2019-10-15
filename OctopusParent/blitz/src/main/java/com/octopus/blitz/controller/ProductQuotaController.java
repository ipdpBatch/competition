package com.octopus.blitz.controller;

import com.octopus.blitz.BlitzException;
import com.octopus.blitz.dto.ControlProduct;
import com.octopus.blitz.dto.ControlProductPrimaryKey;
import com.octopus.blitz.dto.ProductBaseInfo;
import com.octopus.blitz.dto.ProductQuotaInfo;
import com.octopus.blitz.repository.ControlProductRepository;
import com.octopus.blitz.repository.ProductBaseInfoRepository;
import com.octopus.blitz.repository.ProductQuotaInfoRepository;
import com.octopus.common.bo.BuyBo;
import com.octopus.common.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;


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
    @Autowired
    private ControlProductRepository controlProductRepository;

    @Transactional(propagation = Propagation.NESTED, rollbackFor = Exception.class)
    public void insertIntoControl(ControlProduct controlProduct) throws BlitzException {
            ControlProductPrimaryKey controlProductPrimaryKey = new ControlProductPrimaryKey();
            controlProductPrimaryKey.setOrderSeq(controlProduct.getOrderSeq());
            controlProductPrimaryKey.setOrderStep(controlProduct.getOrderStep());
            Optional<ControlProduct> controlProductInDatabase = controlProductRepository.findById(controlProductPrimaryKey);
            if (controlProductInDatabase.isPresent()) {
                throw new BlitzException();
            } else {
                controlProductRepository.save(controlProduct);
            }
            logger.info(controlProductInDatabase.toString());
    }

    @RequestMapping(value = "/process")
    @Transactional
    public ProductBaseInfo checkQuota(@RequestBody BuyBo buyBo) throws BlitzException {
        ControlProduct controlProduct = new ControlProduct();
        controlProduct.setOrderSeq(buyBo.getOrderSeq());
        controlProduct.setOrderStep(buyBo.getOrderStep());
        controlProduct.setRequestTime(DateUtil.formatTime(new Date()));
        controlProduct.setUpdateTime(DateUtil.formatTime(new Date()));
        controlProduct.setStepStatus("INIT");
        insertIntoControl(controlProduct);
        return checkQuotaWithTransactionAndUpdateFirstWithCacheWithProductBaseInfo(buyBo.getProductId(), buyBo.getTransactionAmount());
    }


    @RequestMapping(value = "/v1/{produtId}/{volume}")
    @Transactional
    public ProductQuotaInfo checkQuotaWithoutTransaction(@PathVariable String productId, @PathVariable BigDecimal volume) {
        // STEP 1: 读取产品基本信息
        ProductBaseInfo productBaseInfo = productBaseInfoRepository.getOne(productId);
        logger.info(productBaseInfo.toString());


        ProductQuotaInfo productQuotaInfo = productQuotaInfoRepository.getOne(productId);
        BigDecimal surplusVolume = productQuotaInfo.getSurplusVolume();
        productQuotaInfo.setSurplusVolume(surplusVolume.subtract(volume));
        try {
            Thread.sleep(SLEEP_MILLIS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        productQuotaInfoRepository.save(productQuotaInfo);
        logger.info(productQuotaInfo.toString());
        return productQuotaInfo;
    }

    @RequestMapping(value = "/v2/{productId}/{volume}")
    @Transactional
    public ProductQuotaInfo checkQuotaWithTransactionAndGetForUpdate(@PathVariable String productId, @PathVariable BigDecimal volume) {
        // STEP 1: 读取产品基本信息
        ProductBaseInfo productBaseInfo = productBaseInfoRepository.getOne(productId);
        logger.info(productBaseInfo.toString());

        ProductQuotaInfo productQuotaInfo = productQuotaInfoRepository.getOneForUpdate(productId);
        productQuotaInfo.setProductId(productId);
        productQuotaInfo.setSurplusVolume(productQuotaInfo.getSurplusVolume().subtract(volume));

        try {
            Thread.sleep(SLEEP_MILLIS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        productQuotaInfoRepository.save(productQuotaInfo);
        productQuotaInfo = productQuotaInfoRepository.getOne(productId);
        logger.info(productQuotaInfo.toString());
        return productQuotaInfo;
    }

    @RequestMapping(value = "/v3/{productId}/{volume}")
    @Transactional
    public ProductQuotaInfo checkQuotaWithTransactionAndUpdateFirst(@PathVariable String productId, @PathVariable BigDecimal volume) {
        // STEP 1: 读取产品基本信息
        ProductBaseInfo productBaseInfo = productBaseInfoRepository.getOne(productId);
        logger.info(productBaseInfo.toString());

        productQuotaInfoRepository.substractSurplusVolume(productId, volume);
        try {
            Thread.sleep(SLEEP_MILLIS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ProductQuotaInfo productQuotaInfo = productQuotaInfoRepository.getOne(productId);
        logger.info(productQuotaInfo.toString());
        return productQuotaInfo;
    }

    @RequestMapping(value = "/v4/{productId}/{volume}")
    @Transactional
    public ProductQuotaInfo checkQuotaWithTransactionAndUpdateFirstWithCache(@PathVariable String productId, @PathVariable BigDecimal volume) {
        // STEP 1: 读取产品基本信息
        ProductBaseInfo productBaseInfo = productBaseInfoRepository.getByProductId(productId);
        logger.info(productBaseInfo.toString());

        productQuotaInfoRepository.substractSurplusVolume(productId, volume);
        try {
            Thread.sleep(SLEEP_MILLIS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ProductQuotaInfo productQuotaInfo = productQuotaInfoRepository.getOne(productId);
        logger.info(productQuotaInfo.toString());
        return productQuotaInfo;
    }


    @RequestMapping(value = "/v6/{productId}/{volume}")
    @Transactional
    public ProductBaseInfo checkQuotaWithTransactionAndUpdateFirstWithCacheWithProductBaseInfo(@PathVariable String productId, @PathVariable BigDecimal volume) {

        productBaseInfoRepository.substractSurplusVolume(productId, volume);
        try {
            Thread.sleep(SLEEP_MILLIS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ProductBaseInfo productBaseInfo = productBaseInfoRepository.getOne(productId);
        logger.info(productBaseInfo.toString());
        return productBaseInfo;
    }


    @RequestMapping(value = "/v5/{productId}/{volume}")
    @Transactional
    public ProductQuotaInfo checkQuotaWithTransactionAndUpdateFirstWithCacheAndUsingZookeeper(@PathVariable String productId, @PathVariable BigDecimal volume) {
//        boolean isSoldOut = SoldOutWatcher.isSoldOut(productId);
//        if (isSoldOut) {
//            return productQuotaInfoRepository.getOne(productId);
//        }
        // STEP 1: 读取产品基本信息
        ProductBaseInfo productBaseInfo = productBaseInfoRepository.getByProductId(productId);
        logger.info(productBaseInfo.toString());

        productQuotaInfoRepository.substractSurplusVolume(productId, volume);
        try {
            Thread.sleep(SLEEP_MILLIS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ProductQuotaInfo productQuotaInfo = productQuotaInfoRepository.getOne(productId);
        logger.info(productQuotaInfo.toString());
        return productQuotaInfo;
    }
}
