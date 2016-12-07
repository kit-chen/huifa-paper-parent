package com.huifa.paper.parent.admin.controller;

import com.huifa.paper.parent.cnki.service.admin.SysRoleModuleService;
import com.huifa.paper.parent.cnki.service.admin.SysUserService;
import com.huifa.paper.parent.cnki.vo.admin.SysUserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by kchen on 2016/9/26.
 */
@Controller
@RequestMapping(value = "/main")
public class MainController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleModuleService sysRoleModuleService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, SysUserVo user, Model model) {
        try {
            logger.info("登录验证！");
            user = sysUserService.login(user);
            if (user != null) {
                saveSessionUserInfo(request,user);
                return "redirect:index.xhtml";
            } else {
                model.addAttribute("errorMsg", "用户名密码错误！");
                return "login";
            }
        } catch (Exception e) {
            logger.warn(e.getMessage());
            e.printStackTrace();
            model.addAttribute("errorMsg", e.getMessage());
            return "login";
        }
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model) {
        Map<String, Object> menuMap = new LinkedHashMap<>();
        try {
            SysUserVo sysUserVo = getSessionUserInfo(request);
            if (sysUserVo.getRoleId().compareTo(0l) == 0) {
                menuMap.putAll(sysRoleModuleService.getModule(sysRoleModuleService.getSysModuleByRole(0l)));
            } else {
                menuMap.putAll(sysRoleModuleService.getModule(sysRoleModuleService.getSysModuleByRole(sysUserVo.getRoleId())));
            }
            model.addAttribute("baseMenu", menuMap.get("baseMenu"));
            model.addAttribute("sysMenu", menuMap.get("sysMenu"));
        } catch (Exception e) {
            logger.warn("进入主页失败");
            e.printStackTrace();
        }
        return "index";
    }
}