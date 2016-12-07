package com.huifa.paper.parent.cnki.dao.admin;

import com.huifa.paper.parent.cnki.entity.admin.SysModule;
import com.huifa.paper.parent.cnki.vo.admin.SysModuleVo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by zwz on 2016/9/27.
 */
public interface ISysModuleDao {

    void createSysModule(SysModule sysModule);

    void updateSysModule(Map<Serializable, Serializable> updateMap);

    void logicDelSysModuleById(Long id);

    void deleteSysModuleById(Long id);

    SysModuleVo getById(Long id);

    List<SysModuleVo> getSysModuleByMap(Map<Serializable, Serializable> params);

    Integer getSysModuleCountByMap(Map<Serializable, Serializable> params);

    List<SysModuleVo> getSysModuleByRoleAndParent(Map<Serializable, Serializable> params);
}
