package com.huifa.paper.parent.cnki.service.admin;

import com.huifa.paper.parent.cnki.exception.ParameterException;
import com.huifa.paper.parent.cnki.vo.admin.SysRoleVo;
import com.huifa.paper.parent.common.page.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/27.
 */
public interface SysRoleService {

     Map<String,Object> getSysRoleList(SysRoleVo roleVo, Page page) throws ParameterException;

     List<SysRoleVo> getAllRoleList();

     SysRoleVo getById(Long id);

     void createSysRole(SysRoleVo roleVo) throws ParameterException;

     void updateSysRole(SysRoleVo roleVo) throws ParameterException;

     void deleteSysRole(Long roleId) throws ParameterException;
}
