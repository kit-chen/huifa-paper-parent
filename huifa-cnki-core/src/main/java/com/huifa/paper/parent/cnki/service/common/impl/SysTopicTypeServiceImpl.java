package com.huifa.paper.parent.cnki.service.common.impl;

import com.huifa.paper.parent.cnki.dao.common.ISysTopicTypeDao;
import com.huifa.paper.parent.cnki.service.common.SysTopicTypeService;
import com.huifa.paper.parent.cnki.vo.common.SysTopicTypeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kchen on 2016/10/12.
 */
@Service(value = "sysTopicTopicService")
public class SysTopicTypeServiceImpl implements SysTopicTypeService {

    @Autowired
    private ISysTopicTypeDao sysTopicTypeDao;

    @Override
    public List<SysTopicTypeVo> getList(){
        Map<Serializable,Serializable> queryMap = new HashMap<>();
        return sysTopicTypeDao.getSysTopicTypeByMap(queryMap);
    }
}