package com.huifa.paper.parent.cnki.service.admin.impl;

import com.huifa.paper.parent.cnki.dao.admin.ISysRoleDao;
import com.huifa.paper.parent.cnki.dao.admin.ISysRoleModuleDao;
import com.huifa.paper.parent.cnki.exception.ParameterException;
import com.huifa.paper.parent.cnki.service.admin.SysRoleService;
import com.huifa.paper.parent.cnki.vo.admin.SysRoleVo;
import com.huifa.paper.parent.common.constants.Constants;
import com.huifa.paper.parent.common.page.Page;
import com.huifa.paper.parent.service.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/27.
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends BaseService implements SysRoleService {

    @Autowired
    private ISysRoleDao sysRoleDao;

    @Autowired
    private ISysRoleModuleDao sysRoleModuleDao;

    @Override
    public Map<String, Object> getSysRoleList(SysRoleVo roleVo, Page page) throws ParameterException {
        Map<Serializable, Serializable> queryMap = new HashMap<>();
        if (page != null) {
            this.putPageParams(queryMap, page);
        }
        if (StringUtils.isNoneBlank(roleVo.getName())) queryMap.put("name", roleVo.getName());
        if (null != roleVo.getStatus()) queryMap.put("status", roleVo.getStatus());
        Map<String, Object> result = new HashMap<>();
        List<SysRoleVo> list = sysRoleDao.getSysRoleByMap(queryMap);
        Integer totalNum = sysRoleDao.getSysRoleCountByMap(queryMap);
        result.put("total", totalNum);
        result.put("rows", list);
        return result;
    }

    @Override
    public List<SysRoleVo> getAllRoleList() {
        Map<Serializable, Serializable> queryMap = new HashMap<>();
        return sysRoleDao.getAllRoleByMap(queryMap);
    }

    @Override
    public SysRoleVo getById(Long id) {
        return sysRoleDao.getById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void createSysRole(SysRoleVo roleVo) throws ParameterException {
        if (roleVo == null || StringUtils.isBlank(roleVo.getName())) {
            throw new ParameterException("角色名不能为空!");
        }
        roleVo.setStatus(Constants.STATE_ENABLE);
        roleVo.setDeleteStatus(Constants.STATE_ENABLE);
        sysRoleDao.createSysRole(roleVo);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateSysRole(SysRoleVo roleVo) throws ParameterException {
        if (roleVo == null || StringUtils.isBlank(roleVo.getName())) {
            throw new ParameterException("角色名不能为空!");
        }
        Map<Serializable, Serializable> updateMap = new HashMap<>();
        updateMap.put("name", roleVo.getName());
        updateMap.put("id", roleVo.getId());
        sysRoleDao.updateSysRole(updateMap);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteSysRole(Long roleId) throws ParameterException {
        if (roleId == null) {
            throw new ParameterException("请选择一项要删除的数据!");
        }
        sysRoleModuleDao.delModuleByRole(roleId);
        sysRoleDao.logicDelSysRoleById(roleId);
    }
}
