package com.tl.ssm.domain;

import com.tl.ssm.utils.DateUtils;

import java.util.Date;
import java.util.List;

//订单
public class Orders {
    private Integer id;
    private String orderNum; //订单编号 不为空 唯一
    private Date orderTime; //下单时间
    private String orderTimeStr; //将时间类型转换成String类型
    private int peopleCount; //出现人数
    private String orderDesc; //订单描述（其他信息）
    private Integer payType; //支付方式(0 支付宝 1 微信 2其它)
    private String payTypeStr; //将Integer类型的支付方式转成String类型
    private Integer orderStatus; //订单状态(0 未支付 1 已支付)
    private String orderStatusStr; //将int类型的Status转换成String类型
    private Product product; //产品信息
    private Member member; //会员信息
    private List<Traveller> travellers; //旅客

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderTimeStr() {
        if (orderTime != null){
            orderTimeStr = DateUtils.dateToString(orderTime,"yyyy-MM-dd HH:mm");
        }
        return orderTimeStr;
    }

    public void setOrderTimeStr(String orderTimeStr) {
        this.orderTimeStr = orderTimeStr;
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getPayTypeStr() {
        //支付方式(0 支付宝 1 微信 2其它)
        if (payType != null){
            if (payType ==0 ){
                payTypeStr = "支付宝";
            }else if (payType == 1){
                payTypeStr = "微信";
            }else if (payType == 2){
                payTypeStr = "其他";
            }
        }
        return payTypeStr;
    }

    public void setPayTypeStr(String payTypeStr) {
        this.payTypeStr = payTypeStr;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<Traveller> getTravellers() {
        return travellers;
    }

    public void setTravellers(List<Traveller> travellers) {
        this.travellers = travellers;
    }

    public String getOrderStatusStr() {
        //订单状态(0 未支付 1 已支付)
        if (orderStatus != null){
            if (orderStatus == 0){
                orderStatusStr = "未支付";
            }else if (orderStatus == 1){
                orderStatusStr = "已支付";
            }
        }
        return orderStatusStr;
    }

    public void setOrderStatusStr(String orderStatusStr) {
        this.orderStatusStr = orderStatusStr;
    }
}
