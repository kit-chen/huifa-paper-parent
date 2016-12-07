package com.huifa.paper.parent.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huifa.paper.parent.admin.util.IndexUtil;
import com.huifa.paper.parent.cnki.properties.WebProperties;
import com.huifa.paper.parent.cnki.vo.admin.SysUserVo;
import com.huifa.paper.parent.common.constants.Constants;
import com.huifa.paper.parent.common.page.Page;
import com.huifa.paper.parent.util.CookieUrlUtils;
import com.huifa.paper.parent.util.SSDBUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by kchen on 2016/8/17.
 */
public class BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    protected WebProperties webProperties;

    @Autowired
    protected SSDBUtils ssdbUtils;

    @Autowired
    protected IndexUtil indexUtil;

    /**
     * 保存用户session信息到ssdb
     *
     * @param request
     * @param sysUserVo
     */
    protected void saveSessionUserInfo(HttpServletRequest request, SysUserVo sysUserVo) {
        String sessionId = requestCookieId(request);
        String value = JSON.toJSONString(sysUserVo);
        ssdbUtils.save(sessionId + Constants.MANAGER_USER, value, Constants.USER_SESSION_TIME);
        request.setAttribute(Constants.MANAGER_USER, sysUserVo);
    }

    /**
     * 从ssdb 获取用户信息
     *
     * @param request
     * @return
     */
    protected SysUserVo getSessionUserInfo(HttpServletRequest request) {
        String sessionId = requestCookieId(request);
        String value = ssdbUtils.getUserSession(sessionId + Constants.MANAGER_USER);
        if (StringUtils.isNoneBlank(value)) {
            JSONObject json = JSON.parseObject(value);
            return JSON.toJavaObject(json, SysUserVo.class);
        }
        return null;
    }

    /**
     * 获取sessionId
     *
     * @param request
     * @return
     */
    protected String requestCookieId(HttpServletRequest request) {
        return CookieUrlUtils.readCookie(request, Constants.CK_S_ID);
    }


    protected Page getPage(Integer currentPage, Integer pageSize) {
        Page page = new Page();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        return page;
    }

}
