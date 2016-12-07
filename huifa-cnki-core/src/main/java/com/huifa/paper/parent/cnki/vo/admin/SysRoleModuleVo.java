package com.huifa.paper.parent.cnki.vo.admin;

import com.huifa.paper.parent.cnki.entity.admin.SysRoleModule;

/**
 * Created by Administrator on 2016/9/27.
 */
public class SysRoleModuleVo extends SysRoleModule {

    private String moduleName;     //模块名称
    private String moduleUrl;      //模块地址
    private Long parentId;         //父id
    private Integer type;          //菜单类型

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleUrl() {
        return moduleUrl;
    }

    public void setModuleUrl(String moduleUrl) {
        this.moduleUrl = moduleUrl;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
