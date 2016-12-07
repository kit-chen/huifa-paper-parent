package com.huifa.paper.parent.cnki.dao.common;

import com.huifa.paper.parent.cnki.vo.common.SysTopicTypeVo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by kchen on 2016/10/10.
 */
public interface ISysTopicTypeDao {

    List<SysTopicTypeVo> getSysTopicTypeByMap(Map<Serializable, Serializable> queryMap);

}