package com.huifa.paper.parent.cnki.entity.tb;

import com.huifa.paper.parent.common.domain.BusinessEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by kchen on 2016-12-03.
 */
public class TaoBaoSeller extends BusinessEntity {

    private Long adminId;
    private Long sellerId;    //卖家id
    private String sellerNick;//卖家昵称
    private String shopUrl;   //店铺地址
    private Date createTime;  //创建时间
    private Date updateTime;  //上次授权时间
    private Long duration;     //有效时长
    //提交检测需要用到的数据域
    private String domain;    //短地址url标识
    private String realName;  //真实姓名
    private String tel;       //电话
    private String qq;        //qq
    private String email;     //邮箱
    private String buyLink;   //购买地址
    private Integer balance;  //余额
    private BigDecimal rechargeMoney; //充值金额
    private Integer state;    //状态  订购了应用需要后台审核才能使用
    private String notes;     //备注

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerNick() {
        return sellerNick;
    }

    public void setSellerNick(String sellerNick) {
        this.sellerNick = sellerNick;
    }

    public String getShopUrl() {
        return shopUrl;
    }

    public void setShopUrl(String shopUrl) {
        this.shopUrl = shopUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBuyLink() {
        return buyLink;
    }

    public void setBuyLink(String buyLink) {
        this.buyLink = buyLink;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public BigDecimal getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(BigDecimal rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}