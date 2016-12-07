package com.huifa.paper.parent.cnki.dao.admin;

import com.huifa.paper.parent.cnki.entity.admin.SysRole;
import com.huifa.paper.parent.cnki.vo.admin.SysRoleVo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by zwz on 2016/9/27.
 */
public interface ISysRoleDao {

    void createSysRole(SysRole sysRole);

    void updateSysRole(Map<Serializable, Serializable> updateMap);

    void logicDelSysRoleById(Long id);

    void deleteSysRoleById(Long id);

    SysRoleVo getById(Long id);

    List<SysRoleVo> getSysRoleByMap(Map<Serializable, Serializable> params);

    Integer getSysRoleCountByMap(Map<Serializable, Serializable> params);

    List<SysRoleVo> getAllRoleByMap(Map<Serializable, Serializable> params);
}