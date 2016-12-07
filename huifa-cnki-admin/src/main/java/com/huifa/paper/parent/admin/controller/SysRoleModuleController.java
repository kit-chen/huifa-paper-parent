package com.huifa.paper.parent.admin.controller;

import com.huifa.paper.parent.cnki.service.admin.SysRoleModuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kchen on 2016/10/9.
 */
@Controller
@RequestMapping(value = "/sysRoleModule")
public class SysRoleModuleController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(SysRoleModuleController.class);

    @Autowired
    private SysRoleModuleService sysRoleModuleService;

    @RequestMapping(value = "/roleModuleInfo", method = RequestMethod.GET)
    public String roleModuleInfo() {
        return "rolemodule/role_module";
    }

    @RequestMapping(value = "/initMenuTree", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> initRoleModule(Integer roleId) {
        List<Map<String, Object>> result = null;
        try {
            result = sysRoleModuleService.initMenuTree(roleId, 0);
        } catch (Exception e) {
            result = new ArrayList<>();
            logger.warn("查询用户权限失败");
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/initRoleModule")
    @ResponseBody
    public void initRoleModule(Long roleId) {
        sysRoleModuleService.initRoleModule(roleId);
    }

    @RequestMapping(value = "/updateRoleModuleById", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> updateRoleModuleById(Long roleId, Long moduleId, Boolean checked) {
        Map<String, String> map = new LinkedHashMap<>();
        try {
            Integer state = 0;
            if (checked) {
                state = 1;
            }
            sysRoleModuleService.updateRoleModule(roleId, moduleId, state);
            map.put("status", "success");
        } catch (Exception e) {
            map.put("status", "fail");
            logger.warn("更新用户权限失败");
            e.printStackTrace();
        }
        return map;
    }
}