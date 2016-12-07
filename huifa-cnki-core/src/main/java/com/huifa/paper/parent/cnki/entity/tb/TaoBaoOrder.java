package com.huifa.paper.parent.cnki.entity.tb;

import com.huifa.paper.parent.common.domain.BusinessEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by kchen on 2016/11/25.
 */
public class TaoBaoOrder extends BusinessEntity {

    private Long adminId;      //后台用户id
    private Long userId;       //如果用户登录了则有用户id
    private Long orderId;      //订单编号
    private String sellerNick; //卖家昵称
    private String buyerNick;  //买家昵称
    private String status;     //状态
    private BigDecimal payment;//订单金额
    private Integer number;    //商品数量
    private Date createTime;   //时间

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getSellerNick() {
        return sellerNick;
    }

    public void setSellerNick(String sellerNick) {
        this.sellerNick = sellerNick;
    }

    public String getBuyerNick() {
        return buyerNick;
    }

    public void setBuyerNick(String buyerNick) {
        this.buyerNick = buyerNick;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}