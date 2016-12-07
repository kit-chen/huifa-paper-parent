package com.huifa.paper.parent.cnki.entity.admin;

import com.huifa.paper.parent.common.domain.BusinessEntity;

/**
 * Created by kchen on 2016/9/27.
 */
public class SysUser  extends BusinessEntity {

    private String name;         //用户名
    private String loginName;    //登录名
    private String password;     //密码
    private String phone;        //手机
    private Long roleId;         //角色id
    private String userType;       //admin:管理员，taobao:淘宝卖家，supply:供应商
    private Integer status;      //使用状态

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


}
