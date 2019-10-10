package com.octopus.common.enums;

/**
 * 文件创建时写入注释内容
 *
 * @author zp3778
 * @version 1.0.0
 * @date Created in 16:38 2019/9/27
 */
public enum MicroService {
    ORDER_ESTBLISH("ESTB","ORDER","MQ001","建立订单"),
    CUSTOMER_CHECK("CHKC","USER","MQ002","客户预检查"),
    PRODUCT_CHECK("CHKP","PRODUCT","MQ003","产品预检查"),
    VOLUME_FROZON("VOLF","PRODUCT","MQ004","额度控制"),
    PAY_BILL("PAYA","PAY","MQ005","购买支付"),
    POSITION_INCREASE("POSI","USER","MQ006","加仓"),
    FINISH("FNSH","ORDER","MQ007","订单完成");

    MicroService(String orderStep, String destiny, String mqSeq, String mark) {
        this.orderStep = orderStep;
        this.destiny = destiny;
        this.mqSeq = mqSeq;
        this.mark = mark;
    }

    public String getOrderStep() {
        return orderStep;
    }

    public void setOrderStep(String orderStep) {
        this.orderStep = orderStep;
    }

    public String getDestiny() {
        return destiny;
    }

    public void setDestiny(String destiny) {
        this.destiny = destiny;
    }

    public String getMqSeq() {
        return mqSeq;
    }

    public void setMqSeq(String mqSeq) {
        this.mqSeq = mqSeq;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    String orderStep;
    String destiny;
    String mqSeq;
    String mark;


    public static String getOrderStep(MicroService microService){
        return microService.getOrderStep();
    }
}
