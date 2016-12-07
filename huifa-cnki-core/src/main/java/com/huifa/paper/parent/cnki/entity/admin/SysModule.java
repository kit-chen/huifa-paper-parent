package com.huifa.paper.parent.cnki.entity.admin;

import com.huifa.paper.parent.common.domain.BusinessEntity;

/**
 * Created by zwz on 2016/9/27.
 */
public class SysModule extends BusinessEntity {

    private String name;
    private Long parentId;
    private String menuUrl;
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }
}
