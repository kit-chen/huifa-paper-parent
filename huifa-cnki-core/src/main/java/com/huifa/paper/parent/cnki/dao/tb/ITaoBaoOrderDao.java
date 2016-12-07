package com.huifa.paper.parent.cnki.dao.tb;

import com.huifa.paper.parent.cnki.entity.tb.TaoBaoOrder;
import com.huifa.paper.parent.cnki.vo.tb.TaoBaoOrderVo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by kchen on 2016/10/27.
 */
public interface ITaoBaoOrderDao {

    TaoBaoOrderVo getById(Long id);

    TaoBaoOrderVo getByAdminId(Long adminId);

    TaoBaoOrderVo getByUserId(Long userId);

    TaoBaoOrderVo getByTid(Long tid);

    List<TaoBaoOrderVo> getTaoBaoOrderByMap(Map<String, Serializable> queryMap);

    Integer getTaoBaoOrderCountByMap(Map<String, Serializable> queryMap);

    void createTaoBaoOrder(TaoBaoOrder taoBaoOrder);

    void updateTaoBaoOrder(Map<String, Serializable> updateMap);

    void logicDelTaoBaoOrderById(Long id);

    void deleteTaoBaoOrderById(Long id);
}