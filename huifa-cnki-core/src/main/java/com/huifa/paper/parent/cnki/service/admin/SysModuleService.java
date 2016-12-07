package com.huifa.paper.parent.cnki.service.admin;

import com.huifa.paper.parent.cnki.exception.ParameterException;
import com.huifa.paper.parent.cnki.vo.admin.SysModuleVo;

import java.util.List;

/**
 * Created by Administrator on 2016/9/27.
 */
public interface SysModuleService {

    List<SysModuleVo> getSysModuleVoList(SysModuleVo moduleVo);

    void createSysModule(SysModuleVo moduleVo) throws ParameterException;

    void updateSysModule(SysModuleVo moduleVo) throws ParameterException;

    void deleteSysModuleById(Long moduleId) throws ParameterException;
}
