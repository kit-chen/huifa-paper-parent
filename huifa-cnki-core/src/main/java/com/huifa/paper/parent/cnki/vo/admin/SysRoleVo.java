package com.huifa.paper.parent.cnki.vo.admin;

import com.huifa.paper.parent.cnki.entity.admin.SysRole;

/**
 * Created by Administrator on 2016/9/27.
 */
public class SysRoleVo extends SysRole {
    /**
     * 
     */
    private static final long serialVersionUID = -8048013254216671880L;
    private Integer roleCount;

    public Integer getRoleCount() {
        return roleCount;
    }

    public void setRoleCount(Integer roleCount) {
        this.roleCount = roleCount;
    }
}
