package com.huifa.paper.parent.cnki.dao.admin;

import com.huifa.paper.parent.cnki.entity.admin.SysRoleModule;
import com.huifa.paper.parent.cnki.vo.admin.SysRoleModuleVo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by zwz on 2016/9/27.
 */
public interface ISysRoleModuleDao {

     void batchCreateSysRoleModule(Map<Serializable, Serializable> params);

     void delModuleByRole(Long roleId);

     List<SysRoleModuleVo> getSysModuleByRole(Map<Serializable, Serializable> params);

     Integer getSysModuleCountByRole(Map<Serializable, Serializable> params);

     List<SysRoleModuleVo> getSysRoleModuleByRoleAndId(Map<Serializable, Serializable> params);

     void createSysRoleModule(SysRoleModule sysRoleModule);

     void updateSysRoleModule(Map<Serializable, Serializable> updateMap);
}
