package com.octopus.eureka.product;

import com.octopus.common.bo.BuyBo;
import com.octopus.common.bo.BuyResponseBo;
import com.octopus.common.dao.domain.ControlProductDto;
import com.octopus.common.dao.domain.ProductBaseInfoDto;
import com.octopus.common.dao.mapper.ControlProductMapper;
import com.octopus.common.dao.mapper.ProductBaseInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

import static com.octopus.common.utils.DateUtil.formatTime;

/**
 *
 * @author lyn
 * @version 1.0.0
 * @date 2019-10-9
 */
@Component("productService")
public class ProductService {
    private final static Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Resource
    private ProductBaseInfoMapper productBaseInfoMapper;

    @Resource
    private ControlProductMapper controlProductMapper;

    /*产品检查*/
//    @Transactional(rollbackFor = Exception.class)
    public BuyResponseBo checkProduct(BuyBo buyBo){
        BuyResponseBo buyResponseBo = new BuyResponseBo();
        buyResponseBo.setOrderSeq(buyBo.getOrderSeq());
        buyResponseBo.setBusinessCode(buyBo.getBusinessCode());
        buyResponseBo.setCustomerId(buyBo.getCustomerId());
        buyResponseBo.setProductId(buyBo.getProductId());
        buyResponseBo.setTransactionAmount(buyBo.getTransactionAmount());
        buyResponseBo.setDestiny(buyBo.getDestiny());
        buyResponseBo.setOrderStep(buyBo.getOrderStep());
        buyResponseBo.setOrderReturnCode(false);
        buyResponseBo.setErrorDetail("产品检查开始~");
            //产品控制表记录信息
            ControlProductDto controlProductDto = new ControlProductDto();
            controlProductDto.setOrderSeq(buyBo.getOrderSeq());
            controlProductDto.setOrderStep(buyBo.getOrderStep());
            String requestTime = formatTime(new Date());
            controlProductDto.setRequestTime(requestTime);
            controlProductDto.setUpdateTime(formatTime(new Date()));
            controlProductDto.setStepStatus("INIT");
            int i = controlProductMapper.insert(controlProductDto);
            if (i != 1) {
                buyResponseBo.setOrderReturnCode(false);
                buyResponseBo.setErrorDetail("插入产品控制表失败");
            } else {
                ProductBaseInfoDto productBaseInfoDto = productBaseInfoMapper.selectById(buyBo.getProductId());
                logger.info("产品信息为：" + productBaseInfoDto.toString());
                if (productBaseInfoDto == null) {
                    buyResponseBo.setOrderReturnCode(false);
                    buyResponseBo.setErrorDetail("无此产品");
                    logger.info(buyResponseBo.isOrderReturnCode() + buyResponseBo.getErrorDetail());
                    controlProductDto.setUpdateTime(formatTime(new Date()));
                    controlProductDto.setStepStatus("PCFL");

                    int j = controlProductMapper.update(controlProductDto);

                } else {
                    BigDecimal remainAmount = productBaseInfoDto.getProductRemainAmount();
                    if (remainAmount.compareTo(buyBo.getTransactionAmount()) < 0) {//额度不足
                        buyResponseBo.setOrderReturnCode(false);
                        buyResponseBo.setErrorDetail("产品额度不足");
                        logger.info(buyResponseBo.isOrderReturnCode() + buyResponseBo.getErrorDetail());
                        controlProductDto.setUpdateTime(formatTime(new Date()));
                        controlProductDto.setStepStatus("PCFL");
                        int j = controlProductMapper.update(controlProductDto);
                    } else {
                        buyResponseBo.setOrderReturnCode(true);
                        buyResponseBo.setErrorDetail("检查通过");
                        logger.info(buyResponseBo.isOrderReturnCode() + buyResponseBo.getErrorDetail());
                        controlProductDto.setUpdateTime(formatTime(new Date()));
                        controlProductDto.setStepStatus("PCSC");
                        int j = controlProductMapper.update(controlProductDto);
                    }
                }
            }
            return buyResponseBo;
    }
}
