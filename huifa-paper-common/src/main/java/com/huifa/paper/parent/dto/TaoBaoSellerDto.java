package com.huifa.paper.parent.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.huifa.paper.parent.common.dto.GenericDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * Created by kchen on 2016/11/8.
 */
public class TaoBaoSellerDto extends GenericDTO {

    private Long memberId;    //用户id
	private Long sellerId;    //卖家ID
    private String sellerNick;  //卖家昵称
    private Long shopId;      //店铺ID
    private String shopUrl;   //店铺地址
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastOauthTime;  //上次授权时间
    private Long duration;       //授权可用时长（从上次授权时算起的秒数）
    private String startTimeStr;
    private Date startTime;
    private String endTimeStr;
    private Date endTime;
    private int page; //当前页,名字必须为page
    private int rows; //每页大小,名字必须为rows

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
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

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopUrl() {
        return shopUrl;
    }

    public void setShopUrl(String shopUrl) {
        this.shopUrl = shopUrl;
    }

    public Date getLastOauthTime() {
        return lastOauthTime;
    }

    public void setLastOauthTime(Date lastOauthTime) {
        this.lastOauthTime = lastOauthTime;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }
    
    public boolean enable(){
    	if(null == sellerId || sellerId <=0 || StringUtils.isEmpty(sellerNick) || null == shopId || shopId <=0  || null == lastOauthTime || null == duration || duration <=0){
    		return false;
    	}
    	return true;
    }

    public String getStartTimeStr() {
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
