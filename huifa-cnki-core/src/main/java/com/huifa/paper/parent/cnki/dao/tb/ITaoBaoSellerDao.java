package com.huifa.paper.parent.cnki.dao.tb;

import com.huifa.paper.parent.cnki.entity.tb.TaoBaoSeller;
import com.huifa.paper.parent.cnki.vo.tb.TaoBaoSellerVo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by kchen on 2016/11/25.
 */
public interface ITaoBaoSellerDao {

    TaoBaoSellerVo getById(Long id);

    TaoBaoSellerVo getByAdminId(Long id);

    TaoBaoSellerVo getByDomain(String domain);

    TaoBaoSellerVo getTaoBaoSellerRechargeSum(Map<String, Serializable> queryMap);

    List<TaoBaoSellerVo> getTaoBaoSellerByMap(Map<String, Serializable> queryMap);

    Integer getTaoBaoSellerCountByMap(Map<String, Serializable> queryMap);

    void createTaoBaoSeller(TaoBaoSeller taoBaoSeller);

    Integer updateTaoBaoSeller(Map<String, Serializable> updateMap);

    void logicDelTaoBaoSellerById(Long id);

    void deleteDelTaoBaoSellerById(Long id);
}