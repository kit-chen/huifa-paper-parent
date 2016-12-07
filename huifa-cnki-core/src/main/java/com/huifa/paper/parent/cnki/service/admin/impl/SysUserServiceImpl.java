package com.huifa.paper.parent.cnki.service.admin.impl;

import com.huifa.paper.parent.cnki.dao.admin.ISysUserDao;
import com.huifa.paper.parent.cnki.entity.admin.SysUser;
import com.huifa.paper.parent.cnki.exception.ParameterException;
import com.huifa.paper.parent.cnki.service.admin.SysUserService;
import com.huifa.paper.parent.cnki.vo.admin.SysUserVo;
import com.huifa.paper.parent.common.constants.Constants;
import com.huifa.paper.parent.common.page.Page;
import com.huifa.paper.parent.service.BaseService;
import com.huifa.paper.parent.util.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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
@Service("sysUserService")
public class SysUserServiceImpl extends BaseService implements SysUserService {

    @Autowired
    private ISysUserDao sysUserDao;

    @Override
    public SysUserVo getSysUserById(Long id) {
        return sysUserDao.getById(id);
    }

    @Override
    public List<SysUserVo> getSysUserList(SysUserVo user, Page page) {
        Map<Serializable, Serializable> queryMap = new HashMap<>();
        queryMap.put("name", user.getName());
        queryMap.put("loginName", user.getLoginName());
        queryMap.put("phone", user.getPhone());
        queryMap.put("status", user.getStatus());
        this.putPageParams(queryMap, page);
        return sysUserDao.getSysUserByMap(queryMap);
    }

    @Override
    public SysUserVo login(SysUserVo sysUser) throws ParameterException {
        SysUser user = new SysUser();
        if (StringUtils.isBlank(sysUser.getLoginName())) {
            throw new ParameterException("登录名不能为空");
        }
        if (StringUtils.isBlank(sysUser.getPassword())) {
            throw new ParameterException("密码不能为空");
        }
        Map<Serializable, Serializable> queryMap = new HashMap<>();
        queryMap.put("loginName", user.getLoginName());
        List<SysUserVo> userList = sysUserDao.getSysUserByMap(queryMap);
        if (userList != null && userList.size() > 0) {
            SysUserVo account = userList.get(0);
            String inputPwd = MD5Utils.getMD5String(sysUser.getPassword());
            if (StringUtils.equals(inputPwd, account.getPassword())) {
                return account;
            }
        }
        return null;
    }

    @Override
    public boolean deleteUserById(Long id) {
        Integer count = sysUserDao.logicDelSysUserById(id);
        if (count > 0) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void createUser(SysUserVo userVo) throws ParameterException {
        if (StringUtils.isBlank(userVo.getLoginName())) {
            throw new ParameterException("登录名不能为空");
        }
        if (StringUtils.isBlank(userVo.getPassword())) {
            throw new ParameterException("密码不能为空");
        }
        Map<Serializable, Serializable> queryMap = new HashMap<>();
        queryMap.put("loginName", userVo.getLoginName());
        List<SysUserVo> userList = sysUserDao.getSysUserByMap(queryMap);
        if (userList.size() > 0) {
            throw new ParameterException("登录名已经存在，请尝试新的登录名注册！");
        }
        SysUser user = new SysUser();
        BeanUtils.copyProperties(userVo, user);
        user.setStatus(Constants.STATE_ENABLE);
        user.setDeleteStatus(Constants.STATE_ENABLE);
        user.setPassword(MD5Utils.getMD5String(user.getPassword()));
        sysUserDao.createSysUser(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateUser(SysUserVo userVo) throws ParameterException {
        if (userVo.getId() == null) {
            throw new ParameterException("请选择一项数据进行修改！");
        }
        Map<Serializable, Serializable> updateMap = new HashMap<>();
        updateMap.put("id", userVo.getId());
        updateMap.put("phone", userVo.getPhone());
        updateMap.put("status", userVo.getStatus());
        updateMap.put("roleId", userVo.getRoleId());
        updateMap.put("name", userVo.getName());
        sysUserDao.updateSysUser(updateMap);

    }
}
