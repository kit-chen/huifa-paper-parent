package com.huifa.paper.parent.cnki.service.admin;

import com.huifa.paper.parent.cnki.exception.ParameterException;
import com.huifa.paper.parent.cnki.vo.admin.SysUserVo;
import com.huifa.paper.parent.common.page.Page;

import java.util.List;

/**
 * Created by Administrator on 2016/9/27.
 */
public interface SysUserService  {

    void createUser(SysUserVo userVo) throws ParameterException;

    void updateUser(SysUserVo userVo) throws ParameterException;

    boolean deleteUserById(Long id);

    SysUserVo login(SysUserVo sysUser) throws ParameterException;

    SysUserVo getSysUserById(Long id);

    List<SysUserVo> getSysUserList(SysUserVo user, Page page);
}
