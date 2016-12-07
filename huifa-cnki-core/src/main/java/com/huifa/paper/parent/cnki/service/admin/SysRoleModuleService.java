package com.huifa.paper.parent.cnki.service.admin;

import com.huifa.paper.parent.cnki.vo.admin.SysRoleModuleVo;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/28.
 */
public interface SysRoleModuleService {

    List<SysRoleModuleVo> getSysModuleByRole(Long roleId);

    void deleteRoleModule(Long roleId);

    Map<String,Object> getModule(List<SysRoleModuleVo> list);

    void initRoleModule(Long roleId);

    void updateRoleModule(Long roleId, Long moduleId, Integer state);

    List<Map<String,Object>> initMenuTree(Integer roleId, Integer parentId);
}
