package com.huifa.paper.parent.cnki.service.tb.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.huifa.paper.parent.cnki.dao.tb.ITaoBaoSellerDao;
import com.huifa.paper.parent.cnki.entity.tb.TaoBaoSeller;
import com.huifa.paper.parent.cnki.service.tb.TaoBaoSellerService;
import com.huifa.paper.parent.cnki.vo.tb.TaoBaoSellerVo;
import com.huifa.paper.parent.common.constants.Constants;
import com.huifa.paper.parent.dto.TaoBaoSellerDto;
import com.huifa.paper.parent.util.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kchen on 2016/11/25.
 */
@Service(value = "taoBaoSeller")
public class TaoBaoSellerServiceImpl implements TaoBaoSellerService {

    private static final Logger logger = LoggerFactory.getLogger(TaoBaoSellerServiceImpl.class);

    @Autowired
    private ITaoBaoSellerDao taoBaoSellerDao;

    @Override
    public TaoBaoSellerVo getById(Long id) {
        return taoBaoSellerDao.getById(id);
    }

    @Override
    public TaoBaoSellerVo getByDomain(String domain) {
        return taoBaoSellerDao.getByDomain(domain);
    }

    @Override
    public TaoBaoSellerVo getByAdminId(Long adminId) {
        return taoBaoSellerDao.getByAdminId(adminId);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void saveSellerOauth(TaoBaoSellerDto taoBaoSellerDto) {
        Map<String, Serializable> updateMap = new LinkedHashMap<>();
        updateMap.put("id", taoBaoSellerDto.getMemberId());
        updateMap.put("sellerId", taoBaoSellerDto.getSellerId());
        updateMap.put("sellerNick", taoBaoSellerDto.getSellerNick());
        updateMap.put("shopId", taoBaoSellerDto.getShopId());
        updateMap.put("shopUrl", taoBaoSellerDto.getShopUrl());
        updateMap.put("lastOauthTime", taoBaoSellerDto.getLastOauthTime());
        updateMap.put("duration", taoBaoSellerDto.getDuration());
        taoBaoSellerDao.updateTaoBaoSeller(updateMap);
    }

    @Override
    public TaoBaoSellerVo saveSellerInfo(TaoBaoSellerVo taoBaoSellerVo) {
        TaoBaoSellerVo sellerVo = taoBaoSellerDao.getById(taoBaoSellerVo.getId());
        if (null != sellerVo) {
            Map<String, Serializable> updateSellerMap = new LinkedHashMap<>();
            updateSellerMap.put("id", sellerVo.getId());
            updateSellerMap.put("domain",taoBaoSellerVo.getDomain());
            updateSellerMap.put("sellerNcik",taoBaoSellerVo.getSellerNick());
            updateSellerMap.put("realName",taoBaoSellerVo.getRealName());
            updateSellerMap.put("buyLink",taoBaoSellerVo.getBuyLink());
            updateSellerMap.put("shopUrl",taoBaoSellerVo.getShopUrl());
            updateSellerMap.put("tel",taoBaoSellerVo.getTel());
            updateSellerMap.put("qq",taoBaoSellerVo.getQq());
            taoBaoSellerDao.updateTaoBaoSeller(updateSellerMap);
        }
        return taoBaoSellerDao.getById(taoBaoSellerVo.getId());
    }

    @Override
    public JSONObject getSellerList(TaoBaoSellerDto taoBaoSellerDto) {
        Map<String, Serializable> queryMap = new LinkedHashMap<>();
        if (StringUtils.isNoneBlank(taoBaoSellerDto.getSellerNick())) {
            queryMap.put("sellerNick", taoBaoSellerDto.getSellerNick());
        }
        if (taoBaoSellerDto.getStartTime() != null) {
            queryMap.put("startTime", taoBaoSellerDto.getStartTime());
        }
        if (taoBaoSellerDto.getEndTime() != null) {
            queryMap.put("endTime", taoBaoSellerDto.getEndTime());
        }

        TaoBaoSellerVo rechargeSum = taoBaoSellerDao.getTaoBaoSellerRechargeSum(queryMap);
        JSONArray footer = new JSONArray();
        if (rechargeSum != null) {
            JSONObject sum = new JSONObject();
            sum.put("wangWang", "总计");
            sum.put("rechargeMoney", rechargeSum.getRechargeMoney());
            sum.put("balance", rechargeSum.getBalance());
            footer.add(sum);
        }

        PageHelper.startPage(taoBaoSellerDto.getPage(), taoBaoSellerDto.getRows());
        List<TaoBaoSellerVo> sellerList = taoBaoSellerDao.getTaoBaoSellerByMap(queryMap);
        Integer total = taoBaoSellerDao.getTaoBaoSellerCountByMap(queryMap);
        JSONObject result = new JSONObject();
        result.put("total", total);
        result.put("rows", sellerList);
        result.put("footer", footer);
        result.put("state", "success");
        result.put("desc", "查询成功");
        logger.info("查询淘宝代理列表成功,queryMap={}", JSON.toJSONString(queryMap));
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public JSONObject disableAgent(Long id) {
        JSONObject result = new JSONObject();
        TaoBaoSellerVo taobaoSeller = taoBaoSellerDao.getById(id);
        if (taobaoSeller == null) {
            logger.warn("注销淘宝代理失败,代理不存在,id={}", id);
            result.put("state", "fail");
            result.put("desc", "该代理不存在，请刷新后重试！");
            return result;
        }
        Map<String, Serializable> updateMap = new LinkedHashMap<>();
        updateMap.put("id", id);
        if (taobaoSeller.getBalance() <= 0) {
            updateMap.put("state", Constants.STATE_DISABLE);
        }
        updateMap.put("deleteStatus", Constants.STATE_DISABLE);
        Integer updateRows = taoBaoSellerDao.updateTaoBaoSeller(updateMap);
        if (updateRows == 1) {
            logger.info("注销淘宝代理成功,id={}", id);
            result.put("state", "success");
            result.put("desc", "注销成功");
        } else {
            logger.warn("注销淘宝代理失败,id={}", id);
            result.put("state", "fail");
            result.put("desc", "注销失败，请重试！");
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public JSONObject enableAgent(Long id) {
        JSONObject result = new JSONObject();
        Map<String, Serializable> updateMap = new LinkedHashMap<>();
        updateMap.put("id", id);
        List<TaoBaoSellerVo> taobaoSeller = taoBaoSellerDao.getTaoBaoSellerByMap(updateMap);
        if (taobaoSeller == null || taobaoSeller.size() <= 0) {
            logger.warn("激活淘宝代理失败,代理不存在,id={}", id);
            result.put("state", "fail");
            result.put("desc", "该代理不存在，请刷新后重试！");
            return result;
        }
        if (taobaoSeller.get(0).getBalance() > 0) {
            updateMap.put("state", Constants.STATE_ENABLE);
        }
        updateMap.put("deleteStatus", Constants.STATE_ENABLE);
        Integer updateRows = taoBaoSellerDao.updateTaoBaoSeller(updateMap);
        if (updateRows == 1) {
            logger.info("激活淘宝代理成功,id={}", id);
            result.put("state", "success");
            result.put("desc", "激活成功");
        } else {
            logger.warn("激活淘宝代理失败,id={}", id);
            result.put("state", "fail");
            result.put("desc", "激活失败，请重试！");
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public JSONObject updateBalance(Long id, BigDecimal rechargeMoney) {
        JSONObject result = new JSONObject();
        TaoBaoSellerVo taobaoSeller = taoBaoSellerDao.getById(id);
        if (taobaoSeller == null) {
            logger.warn("更新淘宝代理余额失败,代理不存在,id={}", id);
            result.put("state", "fail");
            result.put("desc", "该代理不存在，请刷新后重试！");
            return result;
        }
        Map<String, Serializable> updateMap = new LinkedHashMap<>();
        updateMap.put("id", id);
        updateMap.put("state", Constants.STATE_ENABLE);
        updateMap.put("rechargeMoney", taobaoSeller.getRechargeMoney().add(rechargeMoney));
        updateMap.put("balance", taobaoSeller.getBalance() + rechargeMoney.intValue() * Constants.BASE_NUM_TAOBAO);
        Integer updateRows = taoBaoSellerDao.updateTaoBaoSeller(updateMap);
        if (updateRows == 1) {
            logger.info("更新淘宝代理余额成功,id={}", id);
            result.put("state", "success");
            result.put("desc", "更新成功");
        } else {
            logger.warn("更新淘宝代理余额失败,id={}", id);
            result.put("state", "fail");
            result.put("desc", "更新失败，请重试！");
        }
        return result;
    }
}