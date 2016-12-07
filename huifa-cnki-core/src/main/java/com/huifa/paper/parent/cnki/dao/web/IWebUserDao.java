package com.huifa.paper.parent.cnki.dao.web;

import com.huifa.paper.parent.cnki.entity.web.WebUser;
import com.huifa.paper.parent.cnki.vo.web.WebUserVo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by kchen on 2016-11-21.
 */
public interface IWebUserDao {

    WebUserVo getById(Long id);

    List<WebUserVo> getWebUserByMap(Map<Serializable,Serializable> queryMap);

    Integer getWebUserCountByMap(Map<Serializable,Serializable> queryMap);

    void createWebUser(WebUser webUser);

    WebUserVo getByOpenId(String openId);

    void modifyPassword(Map<Serializable,Serializable> updateMap);

    void updateWebUser(Map<Serializable,Serializable> updateMap);

    void logicDelWebUserById(Long id);

    void deleteDelWebUserById(Long id);

    void updateBalance(Map<Serializable,Serializable> updateMap);

    void updateStatus(Map<Serializable,Serializable> updateMap);
}