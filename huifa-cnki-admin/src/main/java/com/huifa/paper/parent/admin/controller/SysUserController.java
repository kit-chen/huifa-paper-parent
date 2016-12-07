package com.huifa.paper.parent.admin.controller;

import com.huifa.paper.parent.cnki.service.admin.SysUserService;
import com.huifa.paper.parent.cnki.vo.admin.SysUserVo;
import com.huifa.paper.parent.common.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/28.
 */
@Controller
@RequestMapping("/sysuser")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public String toViewUserList(){
        return "sysuser/admin_info";
    }

    @RequestMapping(value = "/getList",method = RequestMethod.POST)
    @ResponseBody
    public List<SysUserVo>  getUserList(SysUserVo user, Integer page, Integer rows){
        Page queryPage = new Page();
        queryPage.setPageSize(rows);
        queryPage.setCurrentPage(page);
        List<SysUserVo> userList = sysUserService.getSysUserList(user,queryPage);
        return userList;
    }

    @RequestMapping("/get")
    @ResponseBody
    public SysUserVo getUser(SysUserVo user,Integer page,Integer rows){
        try{
            SysUserVo userVo = sysUserService.getSysUserById(user.getId());
            return userVo;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/createOrUpdate",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> createOrUpdateUser(SysUserVo user){
        Map<String,Object> result = new HashMap<>();
        try{
            if(user.getId() == null || user.getId().compareTo(0l) <= 0){
                sysUserService.createUser(user);
            }else{
                sysUserService.updateUser(user);
            }
            result.put("result","success");
        }catch(Exception e){
            result.put("result","failed");
            result.put("errorMsg",e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> deleteUser(Long id){
        Map<String,Object> result = new HashMap<>();
        try{
            if(sysUserService.deleteUserById(id)){
                result.put("result","success");
            }else{
                result.put("result","failed");
                result.put("errorMsg","数据不存在或已经被删除");
            }
        }catch(Exception e){
            result.put("result","failed");
            result.put("errorMsg",e.getMessage());
        }
        return result;
    }
}
