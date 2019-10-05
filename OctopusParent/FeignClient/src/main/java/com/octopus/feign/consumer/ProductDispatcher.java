package com.octopus.feign.consumer;

import com.octopus.common.bo.BuyBo;
import com.octopus.common.dao.domain.ControlProductDto;
import com.octopus.common.dao.domain.ProductBaseInfoDto;
import com.octopus.feign.consumer.provider.ProductClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.octopus.common.utils.DateUtil.formatTime;


@RestController
public class ProductDispatcher {
    private final static Logger logger = LoggerFactory.getLogger(ProductDispatcher.class);
//    @Autowired
//    private HomeClient homeClient;
    @Autowired
    private ProductClient productClient;

    //产品控制表
    public List<ControlProductDto> getControlProductList(){
        return  productClient.getControlProductList();
    }

    public ControlProductDto getControlProduct(BigInteger orderSeq) {
        return productClient.getControlProduct(orderSeq);
    }

    public int deleteControlProduct(BigInteger orderSeq){
        return  productClient.deleteControlProduct(orderSeq);
    }
    public int updateControlProduct(ControlProductDto controlProductDto){
        return productClient.updateControlProduct(controlProductDto);
    }
    public int addControlProduct(ControlProductDto controlProductDto){
        return productClient.addControlProduct(controlProductDto);
    }

    //产品信息表
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

    public int insertProduct(ProductBaseInfoDto productBaseInfoDto){
        return productClient.insertProduct(productBaseInfoDto);
    }

    /*产品检查*/
    public Map checkProduct(BuyBo buyBo) {
        Map result=new HashMap();
        //产品控制表记录信息
        ControlProductDto controlProductDto = new ControlProductDto();
        controlProductDto.setOrderSeq(buyBo.getOrderSeq());
        controlProductDto.setOrderStep(buyBo.getOrderStep());
        String requestTime=formatTime(new Date());
        controlProductDto.setRequestTime(requestTime);
        controlProductDto.setUpdateTime(formatTime(new Date()));
        controlProductDto.setStepStatus("INIT");
        int i=productClient.addControlProduct(controlProductDto);
        if(i!=1){
            result.put("checkResult","false");
            result.put("reason","插入产品控制表失败");
        }else{
            ProductBaseInfoDto productBaseInfoDto = productClient.findById(buyBo.getProductId());
            logger.info("产品信息为："+productBaseInfoDto.toString());
            if(productBaseInfoDto==null){
                result.put("checkResult","false");
                result.put("reason","无此产品");

                controlProductDto.setUpdateTime(formatTime(new Date()));
                controlProductDto.setStepStatus("PCFL");
                int j=productClient.updateControlProduct(controlProductDto);

            }else{
                BigDecimal raiseAmount=productBaseInfoDto.getProductRaiseAmount();
                if(raiseAmount.compareTo(buyBo.getTransactionAmount())<0){//额度不足
                    result.put("checkResult","false");
                    result.put("reason","产品额度不足");

                    controlProductDto.setUpdateTime(formatTime(new Date()));
                    controlProductDto.setStepStatus("PCFL");
                    int j=productClient.updateControlProduct(controlProductDto);
                } else{
                    result.put("checkResult","true");
                    result.put("reason","检查通过");

                    controlProductDto.setUpdateTime(formatTime(new Date()));
                    controlProductDto.setStepStatus("PCSC");
                    int j=productClient.updateControlProduct(controlProductDto);
                }
            }
        }
        return result;
    }
}