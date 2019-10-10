package com.octopus.feign.consumer;

import com.octopus.common.bo.BuyBo;
import com.octopus.common.bo.BuyResponseBo;
import com.octopus.common.dao.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.octopus.feign.consumer.rabbitMq.HelloSender;
import com.octopus.feign.consumer.rabbitMq.Receiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.octopus.common.utils.DateUtil.formatTime;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ConsumerApplication.class)
public class ConsumerApplicationTests {
    private final static Logger logger = LoggerFactory.getLogger(ConsumerApplicationTests.class);
    @Autowired
    private ProductDispatcher productDispatcher;

    @Autowired
    private OrderDispatcher orderDispatcher;


    @Autowired
    private HelloSender helloSender;

    @Autowired
    private Receiver receiver;

    @Autowired
    private UserDispatcher userDispatcher;

//    @Autowired
//    private RabbitTemplate rabbitTemplate;

    @Test
    public void sendOrder() {
        helloSender.send();
    }

    @Test
    public void testProduct(){

//        List<ProductBaseInfoDto> productList = productDispatcher.selectAll();
//        logger.info("列表："+productList.toString());
//        ProductBaseInfoDto productdto = productDispatcher.getProductById("000539");
//        logger.info("主键："+productdto.toString());
//
//        ProductBaseInfoDto productBaseInfoDto=new ProductBaseInfoDto();
//        productBaseInfoDto.setProductId("100999");
//        productBaseInfoDto.setProductName("1999");
//        productBaseInfoDto.setProductRiskLevel("01");
//        productBaseInfoDto.setProductType("01");
//        productBaseInfoDto.setProductRemainAmount(200000);
//        productBaseInfoDto.setProductRaiseAmount(300000);
//        productBaseInfoDto.setRegistarCode("98");
//        productBaseInfoDto.setRegistarName("ABC");
//        logger.info("新建产品记录："+productBaseInfoDto.toString());
//        System.out.println("产品"+productBaseInfoDto.toString());
//        int i=productDispatcher.insertProduct(productBaseInfoDto);
//        logger.info("插入后的productBaseInfoDto："+i);

//        int j=productDispatcher.deleteProduct("000123");
//        logger.info("删除结果："+j);


        ProductBaseInfoDto productBaseInfoDto=new ProductBaseInfoDto();
        productBaseInfoDto.setProductId("000999");
        productBaseInfoDto.setProductName("666");
        productBaseInfoDto.setProductRiskLevel("01");
        productBaseInfoDto.setProductType("01");
//        productBaseInfoDto.setProductRemainAmount(200000);
//        productBaseInfoDto.setProductRaiseAmount(300000);
//        productBaseInfoDto.setRegistarCode("98");
//        productBaseInfoDto.setRegistarName("ABC");
        int k=productDispatcher.updateProduct(productBaseInfoDto);
        logger.info("修改结果："+k);

//        ProductBaseInfoDto productdto = productDispatcher.addProduct("000539");
//        logger.debug("查询结果：" + productdto.toString());
//        receiver.receivertest1( );

    }

    @Test
    public void testControlProduct(){

//        List<ControlProductDto> controlProductDtoList = productDispatcher.getControlProductList();
//        logger.info("列表："+controlProductDtoList.toString());
//        ControlProductDto controlProductDto = productDispatcher.getControlProduct(BigInteger.valueOf(00000000000000000001));
//        logger.info("主键："+controlProductDto.toString());
//
        ControlProductDto controlProductDto =new ControlProductDto();
//        controlProductDto.setOrderSeq(BigInteger.valueOf(1));
        controlProductDto.setStepStatus("PCSC");
        controlProductDto.setOrderStep("INIT");
        controlProductDto.setRequestTime(formatTime(new Date()));
        controlProductDto.setUpdateTime(formatTime(new Date()));
        logger.info("新建产品控制表记录："+controlProductDto.toString());
        System.out.println("产品控制表"+controlProductDto.toString());
        int i=productDispatcher.addControlProduct(controlProductDto);
        logger.info("插入后的controlProductDto："+i);

//        int j=productDispatcher.deleteControlProduct(BigInteger.valueOf(1));
//        logger.info("删除结果："+j);


//        ControlProductDto controlProductDto =new ControlProductDto();
//        controlProductDto.setOrderSeq(BigInteger.valueOf(1));
//        controlProductDto.setStepStatus("PCSC");
//        controlProductDto.setOrderStep("INIT");
//        controlProductDto.setRequestTime(formatTime(new Date()));
//        controlProductDto.setUpdateTime(formatTime(new Date()));
//        int k=productDispatcher.updateControlProduct(controlProductDto);
//        logger.info("修改结果："+k);


    }
    @Test
    public void testCheckProduct(){
        BuyBo buyBo = new BuyBo();
        buyBo.setOrderSeq(BigInteger.valueOf(15));
        buyBo.setBusinessCode("022");
        buyBo.setCustomerId("abc");
        buyBo.setTransactionAmount(new BigDecimal(1000000000));
        buyBo.setProductId("11111111");
        buyBo.setOrderStep("CHKP");
        BuyResponseBo product = productDispatcher.checkProduct(buyBo);
        logger.info("检查结果为："+product.toString());

    }

    @Test
    public void testControlOrder() {
       List<ControlOrderDto> controlOrderList = orderDispatcher.getControlOrderList();
        logger.info("查询结果："+controlOrderList.toString());
        ControlOrderDto controlOrder = orderDispatcher.getControlOrder("1111");
        logger.info("查询结果：" + controlOrder.toString());
//       ControlOrderDto controlOrderDto = new ControlOrderDto();
//        controlOrderDto.setOrderSeq("1");
////        controlOrderDto.setOrderStep("VOLF");
////        controlOrderDto.setStepStatus("PCSC");
//       controlOrderDto.setRequestTime("20190733");
//       controlOrderDto.setUpdateTime("20190866");
//       logger.info("新建controlOrderDto记录："+controlOrderDto.toString());
//       int result = orderDispatcher.addControlOrder(controlOrderDto);
//        logger.info("插入后的controlOrderDto："+result);
//        logger.info("插入条数：" + result);
//
//        ControlOrderDto controlOrderDto2 = new ControlOrderDto("3",new Date(),new Date(),"VOLF","PCFL");
//        int result2 = octopusHandController.updateControlOrder(controlOrderDto2);
//        logger.info("更新条数：" + result2);
////        int result3 = octopusHandController.deleteControlOrder("1");
//        logger.info("删除条数：" + result3);
    }

    @Test
    public void receiverOrder() {

        receiver.receivertest1( );

    }

    @Test
    public void testorder() {
        //查询所有
        List<OrderFinancialDto> orderList = orderDispatcher.getOrderList();
        logger.info("查询结果："+orderList.toString());
        //根据id查询
//        OrderFinancialDto order = orderDispatcher.getOrder(12344455);
        logger.info("查询结果：" + orderList.toString());
        //新增
        OrderFinancialDto orderDto = new OrderFinancialDto();
//        orderDto.setOrderSeq(123);
        orderDto.setCreateDate("20190930");
        orderDto.setCreateTime("222222");
        logger.info("新建controlOrderDto记录："+orderDto.toString());
        int result = orderDispatcher.addOrder(orderDto);
        logger.info("插入后的controlOrderDto："+result);
        logger.info("插入条数：" + result);
        //修改
//        orderDto.setOrderSeq(123);
        orderDto.setProductId("000539");
        orderDto.setCustomerId("2222222");
        logger.info("修改DTO：" + orderDto.toString());
        int result2 = orderDispatcher.updateOrder(orderDto);
        logger.info("更新条数：" + result2);
//        int result3 = orderDispatcher.deleteOrder(1);
//        logger.info("删除条数：" + result3);
    }

    @Test
    public void testCreateOrder(){
        BuyBo buyBo = new BuyBo();
        buyBo.setOrderSeq(BigInteger.valueOf(2019100906));
        buyBo.setBusinessCode("024");
        buyBo.setCustomerId("11111111");
        buyBo.setTransactionAmount(new BigDecimal(10000000));
        buyBo.setProductId("033315");
        BuyResponseBo order = orderDispatcher.createOrder(buyBo);
        logger.info("创建的订单为："+order);
    }

    @Test
    public void testPosition(){
        //查询所有
        List<PositionBalanceDto> positionList = userDispatcher.getPositionList();
        logger.info("查询结果："+positionList.toString());
        //根据id查询
        PositionBalanceDto position = userDispatcher.getPosition("232143","aaa12242");
        logger.info("查询结果：" + position.toString());

        //新增
        PositionBalanceDto positionDTO = new PositionBalanceDto();
        positionDTO.setProductId("000539");
        positionDTO.setCustomerId("4742668");
        positionDTO.setPositionStatus("N");
        logger.info("新建positionDto记录："+positionDTO.toString());
        int result = userDispatcher.addPosition(positionDTO);
        logger.info("插入后的positionDto："+result);
        logger.info("插入条数：" + result);

        //修改
        positionDTO.setCustomerId("4742668");
        positionDTO.setProductId("000539");
        positionDTO.setPositionStatus("Y");
        logger.info("修改DTO：" + positionDTO.toString());
        int result2 = userDispatcher.updatePosition(positionDTO);
        logger.info("更新条数：" + result2);
        int result3 = userDispatcher.deletePosition("000539", "4742668");
        logger.info("删除条数：" + result3);
    }

    @Test
    public void testControlUser() {
        //新增
        ControlUserDto addUserDto = new ControlUserDto();
        addUserDto.setOrderSeq(BigInteger.valueOf(1234568));
        addUserDto.setRequestTime("20191006");
        addUserDto.setOrderStep("INIT");
        logger.info("新建controlUserDto记录："+addUserDto.toString());
        int result = userDispatcher.addControlUser(addUserDto);
        logger.info("插入后的controlUserDto："+result);
        logger.info("插入条数：" + result);
        //查询所有
        List<ControlUserDto> userDtoList = userDispatcher.getControlUserList();
        logger.info("查询结果："+userDtoList.toString());
        //根据id查询
        ControlUserDto user = userDispatcher.getControlUser(BigInteger.valueOf(1234567));
        logger.info("查询结果：" + user.toString());
        //修改
        user.setRequestTime("20190111");
        logger.info("修改DTO：" + user.toString());
        int result2 = userDispatcher.updateControlUser(user);
        logger.info("更新条数：" + result2);
        int result3 = userDispatcher.deleteControlUser(BigInteger.valueOf(1234567));
        logger.info("删除条数：" + result3);
    }

    @Test
    public void testDescPosition(){
        BuyBo buyBo = new BuyBo();
        buyBo.setOrderSeq(BigInteger.valueOf(20191113));
        buyBo.setBusinessCode("024");
        buyBo.setCustomerId("9876543");
        buyBo.setTransactionAmount(new BigDecimal(5000));
        buyBo.setProductId("9989879");
        buyBo.setOrderStep("POSI");
        BuyResponseBo buyResponseBo = userDispatcher.descPosition(buyBo);
        logger.info("返回信息为："+buyResponseBo.toString());
    }
}
