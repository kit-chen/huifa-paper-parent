package com.huifa.paper.parent.admin.controller;

import com.huifa.paper.parent.cnki.service.admin.SysRoleModuleService;
import com.huifa.paper.parent.cnki.service.admin.SysRoleService;
import com.huifa.paper.parent.cnki.vo.admin.SysRoleVo;
import com.huifa.paper.parent.common.page.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Administrator on 2016/9/29.
 */
@Controller
@RequestMapping("/sysrole")
public class SysRoleController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(SysRoleController.class);

    @Autowired
    private SysRoleModuleService sysRoleModuleService;

    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String toViewList() {
        return "/sysrole/role_info";
    }

    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getList(SysRoleVo role, Integer page, Integer rows) {
        try {
            Page queryPage = getPage(page, rows);
            return sysRoleService.getSysRoleList(role, queryPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/get",method = RequestMethod.POST)
    @ResponseBody
    public SysRoleVo get(Long roleId) {
        Map<String, Object> result = new HashMap<>();
        try {
            SysRoleVo roleVo = sysRoleService.getById(roleId);
//            List<SysRoleModuleVo> modules = sysRoleModuleService.getSysModuleByRole(roleId);
//            result.put("modules", modules);
            return roleVo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/getRoleText", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String,Serializable>> getRoleText(){
        List<Map<String,Serializable>> mapList = new LinkedList<>();
        try{
            List<SysRoleVo> roles = sysRoleService.getAllRoleList();
            for(SysRoleVo role : roles){
                Map<String,Serializable> map = new LinkedHashMap<>();
                map.put("id",role.getId());
                map.put("text",role.getName());
                mapList.add(map);
            }
        }catch (Exception e){
            logger.warn("查询角色comboo信息失败");
            e.printStackTrace();
        }
        return mapList;
    }

    @RequestMapping(value = "/createOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> createOrUpdate(SysRoleVo roleVo) {
        Map<String, Object> result = new HashMap<>();
        try {
            if (null == roleVo.getId() || roleVo.getId() <= 0) {
                sysRoleService.createSysRole(roleVo);
            } else {
                sysRoleService.updateSysRole(roleVo);
            }
            result.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result", "failed");
            result.put("errorMsg", e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> delete(Long id) {
        Map<String, Object> result = new HashMap<>();
        try {
            sysRoleService.deleteSysRole(id);
            sysRoleModuleService.deleteRoleModule(id);
            result.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result", "failed");
            result.put("errorMsg", e.getMessage());
        }
        return result;
    }

}
