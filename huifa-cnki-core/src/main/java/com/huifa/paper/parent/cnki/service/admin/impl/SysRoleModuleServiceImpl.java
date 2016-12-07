package com.huifa.paper.parent.cnki.service.admin.impl;

import com.huifa.paper.parent.cnki.dao.admin.ISysModuleDao;
import com.huifa.paper.parent.cnki.dao.admin.ISysRoleModuleDao;
import com.huifa.paper.parent.cnki.entity.admin.SysRoleModule;
import com.huifa.paper.parent.cnki.service.admin.SysRoleModuleService;
import com.huifa.paper.parent.cnki.vo.admin.SysModuleVo;
import com.huifa.paper.parent.cnki.vo.admin.SysRoleModuleVo;
import com.huifa.paper.parent.common.constants.Constants;
import com.huifa.paper.parent.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Administrator on 2016/9/28.
 */
@Service("sysRoleModuleService")
public class SysRoleModuleServiceImpl extends BaseService implements SysRoleModuleService {

    @Autowired
    private ISysRoleModuleDao sysRoleModuleDao;

    @Autowired
    private ISysModuleDao sysModuleDao;

    @Override
    public List<SysRoleModuleVo> getSysModuleByRole(Long roleId) {
        Map<Serializable, Serializable> params = new HashMap<>();
        if (roleId.compareTo(0l) == 0) {
            //登录用户为内置用户
            List<SysModuleVo> list = sysModuleDao.getSysModuleByMap(params);
            List<SysRoleModuleVo> result = new LinkedList<>();
            for (SysModuleVo module : list) {
                SysRoleModuleVo sysRoleModuleVo = new SysRoleModuleVo();
                sysRoleModuleVo.setModuleName(module.getName());
                sysRoleModuleVo.setModuleUrl(module.getMenuUrl());
                sysRoleModuleVo.setType(module.getType());
                sysRoleModuleVo.setParentId(module.getParentId());
                result.add(sysRoleModuleVo);
            }
            return result;
        } else {
            params.put("roleId", roleId);
            return sysRoleModuleDao.getSysModuleByRole(params);
        }
    }

    @Override
    public Map<String, Object> getModule(List<SysRoleModuleVo> list) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<SysRoleModuleVo> baseModules = new LinkedList<>();
        List<SysRoleModuleVo> sysModules = new LinkedList<>();
        for (SysRoleModuleVo module : list) {
            if (module.getParentId().compareTo(0l) > 0) {
                if (module.getType().compareTo(1) == 0) {
                    baseModules.add(module);
                } else if (module.getType().compareTo(2) == 0) {
                    sysModules.add(module);
                }
            }
        }
        map.put("baseMenu", baseModules);
        map.put("sysMenu", sysModules);
        return map;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void initRoleModule(Long roleId) {
        Map<Serializable, Serializable> params = new HashMap<>();
        List<SysModuleVo> list = sysModuleDao.getSysModuleByMap(params);
        for (SysModuleVo moduleVo : list) {
            Map<Serializable, Serializable> queryMap = new HashMap<>();
            queryMap.put("roleId", roleId);
            queryMap.put("moduleId", moduleVo.getId());
            List<SysRoleModuleVo> roleModules = sysRoleModuleDao.getSysRoleModuleByRoleAndId(queryMap);
            if (null == roleModules || roleModules.size() <= 0) {
                SysRoleModule sysRoleModule = new SysRoleModule();
                sysRoleModule.setRoleId(roleId);
                sysRoleModule.setModuleId(moduleVo.getId());
                sysRoleModule.setStatus(Constants.STATE_DISABLE);
                sysRoleModule.setDeleteStatus(Constants.STATE_ENABLE);
                sysRoleModuleDao.createSysRoleModule(sysRoleModule);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateRoleModule(Long roleId, Long moduleId, Integer state) {
        Map<Serializable, Serializable> queryMap = new HashMap<>();
        queryMap.put("roleId", roleId);
        queryMap.put("moduleId", moduleId);
        List<SysRoleModuleVo> roleModules = sysRoleModuleDao.getSysRoleModuleByRoleAndId(queryMap);
        if (null != roleModules && roleModules.size() > 0) {
            SysRoleModuleVo sysRoleModule = roleModules.get(0);
            Map<Serializable, Serializable> updateMap = new HashMap<>();
            updateMap.put("id", sysRoleModule.getId());
            updateMap.put("status", state);
            sysRoleModuleDao.updateSysRoleModule(updateMap);
        }
    }

    public List<Map<String, Object>> initMenuTree(Integer roleId, Integer parentId) {
        List<Map<String, Object>> result = new LinkedList<>();
        Map<Serializable, Serializable> queryMap = new HashMap<>();
        queryMap.put("roleId", roleId);
        queryMap.put("parentId", parentId);
        List<SysModuleVo> moduleList = sysModuleDao.getSysModuleByRoleAndParent(queryMap);
        for (SysModuleVo module : moduleList) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("id", module.getId());
            map.put("text", module.getName());
            if (null != module.getStatus() && module.getStatus().compareTo(Constants.STATE_ENABLE) == 0) {
                map.put("checked", true);
            }
            queryMap.put("roleId", roleId);
            queryMap.put("parentId", module.getId());
            List<SysModuleVo> childModule = sysModuleDao.getSysModuleByRoleAndParent(queryMap);
            List<Map<String, Object>> childList = new LinkedList<>();
            for (SysModuleVo child : childModule) {
                Map<String, Object> childMap = new LinkedHashMap<>();
                childMap.put("id", child.getId());
                childMap.put("text", child.getName());
                if (null != child.getStatus() && child.getStatus().compareTo(Constants.STATE_ENABLE) == 0) {
                    childMap.put("checked", true);
                }
                childList.add(childMap);
            }
            if (childList.size() > 0) {
                map.put("children", childList);
            }
            result.add(map);
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteRoleModule(Long roleId) {
        sysRoleModuleDao.delModuleByRole(roleId);
    }
}
