package com.huifa.paper.parent.cnki.dao.common;

import com.huifa.paper.parent.cnki.entity.common.SystemTopic;
import com.huifa.paper.parent.cnki.vo.common.SystemTopicVo;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by kchen on 2016-12-03.
 */
public interface ISystemTopicDao {

    SystemTopicVo getById(Long id);

    List<SystemTopicVo> getBeforeOrAfter(Map<Serializable, Serializable> updateMap);

    SystemTopicVo createTopic(SystemTopic systemTopic) throws IOException;

    void updateTopic(Map<Serializable, Serializable> updateMap);

    void logicDelSysTopicById(Long id);

    void deleteSysTopicById(Long id);

    List<SystemTopicVo> getSysTopicByMap(Map<Serializable, Serializable> params);

    Integer getSysTopicCountByMap(Map<Serializable, Serializable> params);
}
