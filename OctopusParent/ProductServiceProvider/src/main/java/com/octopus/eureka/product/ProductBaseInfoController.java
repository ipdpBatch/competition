package com.octopus.eureka.product;

import com.octopus.common.bo.BuyBo;
import com.octopus.common.bo.BuyResponseBo;
import com.octopus.common.dao.domain.ProductBaseInfoDto;
import com.octopus.common.dao.mapper.ProductBaseInfoMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author lyn
 * @version 1.0.0
 * @date 2019-10-2
 */
@RestController
public class ProductBaseInfoController {
    @Resource
    private ProductBaseInfoMapper productBaseInfoMapper;

    @Resource
    private ProductService productService;
    @Value("${server.port}")
    String port;
    @RequestMapping("/product")
    public String home() {
        return "Hello world ,port:" + port +". This is " + this.getClass().getName();
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){  return "index";}

    @GetMapping("/product/{id}")
    public ProductBaseInfoDto findById(@PathVariable String id) {
        ProductBaseInfoDto productBaseInfoDto = productBaseInfoMapper.selectById(id);
        if (productBaseInfoDto != null) {
            return productBaseInfoDto;
        } else {
            return null;
        }
    }

    @RequestMapping("/product/all")
    public List<ProductBaseInfoDto> findAll(@RequestBody ProductBaseInfoDto productBaseInfoDto) {
        return productBaseInfoMapper.selectAll(productBaseInfoDto);
    }

    @RequestMapping("/product/delete/{productId}")
    public Long deleteProduct(@PathVariable("productId") String productId){
        return productBaseInfoMapper.delete(productId);
    }

    @RequestMapping("/product/update")
    public int updateProduct(@RequestBody ProductBaseInfoDto productBaseInfoDto){
        return productBaseInfoMapper.update(productBaseInfoDto);
    }

    @RequestMapping("/product/insert")
    public int insertProduct(@RequestBody ProductBaseInfoDto productBaseInfoDto) {
        return productBaseInfoMapper.insert(productBaseInfoDto);
    }
//    String productId;
//    String productRiskLevel;
//    String productType;
//    BigDecimal productRaiseAmount;
//    BigDecimal productRemainAmount;
//    String productName;
//    String registrarCode;
//    String registrarName;
    @RequestMapping("/product/insertNew")
    public int insertProduct2(@RequestParam(value = "productId", required = false) String productId,
                              @RequestParam(value = "productRiskLevel", required = false) String productRiskLevel,
                              @RequestParam(value = "productType", required = false) String productType,
                              @RequestParam(value = "productRaiseAmount", required = false) BigDecimal productRaiseAmount,
                              @RequestParam(value = "productRemainAmount", required = false) BigDecimal productRemainAmount,
                              @RequestParam(value = "productName", required = false) String productName,
                              @RequestParam(value = "registrarCode", required = false) String registrarCode,
                              @RequestParam(value = "registrarName", required = false) String registrarName) {
        ProductBaseInfoDto productBaseInfoDto=new ProductBaseInfoDto();
        productBaseInfoDto.setProductId(productId);
        productBaseInfoDto.setProductRiskLevel(productRiskLevel);
        productBaseInfoDto.setProductType(productType);
        productBaseInfoDto.setProductRaiseAmount(productRaiseAmount);
        productBaseInfoDto.setProductRemainAmount(productRemainAmount);
        productBaseInfoDto.setProductName(productName);
        productBaseInfoDto.setRegistrarCode(registrarCode);
        productBaseInfoDto.setRegistrarName(registrarName);
        return productBaseInfoMapper.insert(productBaseInfoDto);
    }

    @RequestMapping("/product/updateNew")
    public int updateProduct2(@RequestParam(value = "productId", required = false) String productId,
                              @RequestParam(value = "productRiskLevel", required = false) String productRiskLevel,
                              @RequestParam(value = "productType", required = false) String productType,
                              @RequestParam(value = "productRaiseAmount", required = false) BigDecimal productRaiseAmount,
                              @RequestParam(value = "productRemainAmount", required = false) BigDecimal productRemainAmount,
                              @RequestParam(value = "productName", required = false) String productName,
                              @RequestParam(value = "registrarCode", required = false) String registrarCode,
                              @RequestParam(value = "registrarName", required = false) String registrarName){
        ProductBaseInfoDto productBaseInfoDto=new ProductBaseInfoDto();
        productBaseInfoDto.setProductId(productId);
        productBaseInfoDto.setProductRiskLevel(productRiskLevel);
        productBaseInfoDto.setProductType(productType);
        productBaseInfoDto.setProductRaiseAmount(productRaiseAmount);
        productBaseInfoDto.setProductRemainAmount(productRemainAmount);
        productBaseInfoDto.setProductName(productName);
        productBaseInfoDto.setRegistrarCode(registrarCode);
        productBaseInfoDto.setRegistrarName(registrarName);
        return productBaseInfoMapper.update(productBaseInfoDto);
    }

    @RequestMapping("/product/deleteNew")
    public Long deleteProduct2(@RequestParam(value = "productId", required = false) String productId){
        return productBaseInfoMapper.delete(productId);
    }

    @RequestMapping("/product/checkProduct")
    public BuyResponseBo checkProduct(@RequestBody BuyBo buyBo){
        return productService.checkProduct(buyBo);
    }
}
