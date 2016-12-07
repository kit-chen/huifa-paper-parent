package com.huifa.paper.parent.service;

import com.huifa.paper.parent.common.page.Page;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by kchen on 2016/8/28.
 */
public class BaseService {

    protected void putPageParams(Map<Serializable,Serializable> queryMap, Page page){
        queryMap.put("fromNum", (page.getCurrentPage() - 1) * page.getPageSize());
        queryMap.put("pageSize", page.getPageSize());
    }
}