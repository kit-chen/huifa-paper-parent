package com.huifa.paper.parent.cnki.service.tb;

import com.alibaba.fastjson.JSONObject;
import com.huifa.paper.parent.cnki.vo.tb.TaoBaoSellerVo;
import com.huifa.paper.parent.dto.TaoBaoSellerDto;

import java.math.BigDecimal;

/**
 * Created by kchen on 2016/11/25.
 */
public interface TaoBaoSellerService {

    TaoBaoSellerVo getById(Long id);

    TaoBaoSellerVo getByDomain(String domain);

    TaoBaoSellerVo getByAdminId(Long id);

    void saveSellerOauth(TaoBaoSellerDto taoBaoSellerDto);

    TaoBaoSellerVo saveSellerInfo(TaoBaoSellerVo taoBaoSellerVo);

    JSONObject getSellerList(TaoBaoSellerDto taoBaoSellerDto);

    JSONObject disableAgent(Long id);

    JSONObject enableAgent(Long id);

    JSONObject updateBalance(Long id, BigDecimal rechargeMoney);
}