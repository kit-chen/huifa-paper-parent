package com.huifa.paper.parent.cnki.service.admin.impl;

import com.huifa.paper.parent.cnki.dao.admin.ISysModuleDao;
import com.huifa.paper.parent.cnki.entity.admin.SysModule;
import com.huifa.paper.parent.cnki.exception.ParameterException;
import com.huifa.paper.parent.cnki.service.admin.SysModuleService;
import com.huifa.paper.parent.cnki.vo.admin.SysModuleVo;
import com.huifa.paper.parent.service.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/27.
 */
@Service("sysModuleService")
public class SysModuleServiceImpl extends BaseService implements SysModuleService {


    @Autowired
    private ISysModuleDao sysModuleDao;

    @Override
    public List<SysModuleVo> getSysModuleVoList(SysModuleVo moduleVo) {
        Map<Serializable,Serializable> queryMap = new HashMap<>();
        return sysModuleDao.getSysModuleByMap(queryMap);
    }

    @Override
    public void createSysModule(SysModuleVo moduleVo) throws ParameterException {
        if(StringUtils.isBlank(moduleVo.getName())){
            throw new ParameterException("请填写一个菜单名！");
        }
        SysModule module = new SysModule();
        module.setDeleteStatus(1);
        BeanUtils.copyProperties(moduleVo,module);
        sysModuleDao.createSysModule(module);
    }

    @Override
    public void deleteSysModuleById(Long moduleId) throws ParameterException {
        this.sysModuleDao.logicDelSysModuleById(moduleId);
    }

    @Override
    public void updateSysModule(SysModuleVo moduleVo) throws ParameterException {
        if(StringUtils.isBlank(moduleVo.getName())){
            throw new ParameterException("请填写一个菜单名！");
        }
        Map<Serializable,Serializable> updateMap =new HashMap<>();
        updateMap.put("moduleName",moduleVo.getName());
        sysModuleDao.updateSysModule(updateMap);
    }
}
