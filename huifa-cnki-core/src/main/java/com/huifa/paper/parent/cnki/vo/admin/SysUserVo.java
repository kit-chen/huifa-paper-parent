package com.huifa.paper.parent.cnki.vo.admin;


import com.huifa.paper.parent.cnki.entity.admin.SysUser;

/**
 * Created by Administrator on 2016/9/27.
 */
public class SysUserVo  extends SysUser {

    private String roleName;   //角色名称

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
