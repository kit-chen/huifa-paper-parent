package com.huifa.paper.parent.cnki.entity.admin;

import com.huifa.paper.parent.common.domain.BusinessEntity;

/**
 * Created by zwz on 2016/9/27.
 */
public class SysRole extends BusinessEntity {

    private String name;
    private Integer status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
