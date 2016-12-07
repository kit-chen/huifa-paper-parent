package com.huifa.paper.parent.cnki.dao.admin;

import com.huifa.paper.parent.cnki.entity.admin.SysUser;
import com.huifa.paper.parent.cnki.vo.admin.SysUserVo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by zwz on 2016/9/27.
 */
public interface ISysUserDao {

    /**
     * 保存用户信息
     * @param sysUser
     */
    public void createSysUser(SysUser sysUser);

    public void updateSysUser(Map<Serializable, Serializable> updateMap);

    public Integer logicDelSysUserById(Long id);

    public Integer deleteSysUserById(Long id);

    public SysUserVo getById(Long id);

    public SysUserVo login(SysUser sysUser);

    public List<SysUserVo> getSysUserByMap(Map<Serializable, Serializable> params);

    public Integer getSysUserCountByMap(Map<Serializable, Serializable> params);

}
