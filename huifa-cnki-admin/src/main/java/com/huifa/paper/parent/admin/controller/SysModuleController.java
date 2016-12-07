package com.huifa.paper.parent.admin.controller;

import com.huifa.paper.parent.cnki.service.admin.SysModuleService;
import com.huifa.paper.parent.cnki.vo.admin.SysModuleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/29.
 */
@Controller
@RequestMapping("/sysmodule")
public class SysModuleController {

    @Autowired
    private SysModuleService sysModuleService;

    @RequestMapping("/list")
    @ResponseBody
    public List<SysModuleVo> list(SysModuleVo sysModuleVo){
        return sysModuleService.getSysModuleVoList(sysModuleVo);
    }

    @RequestMapping("/createOrUpdate")
    public Map<String,Object> createOrUpdate(SysModuleVo sysModuleVo){

        return null;
    }

    @RequestMapping("/delete")
    public Map<String,Object> delete(Long Id){

        return null;
    }
}
