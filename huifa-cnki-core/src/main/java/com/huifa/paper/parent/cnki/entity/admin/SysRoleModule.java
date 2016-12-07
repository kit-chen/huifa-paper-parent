package com.huifa.paper.parent.cnki.entity.admin;

import com.huifa.paper.parent.common.domain.BusinessEntity;

/**
 * Created by zwz on 2016/9/27.
 */
public class SysRoleModule extends BusinessEntity {

    private Long roleId;      //角色id
    private Long moduleId;    //模块id
    private Integer status;      //状态

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
