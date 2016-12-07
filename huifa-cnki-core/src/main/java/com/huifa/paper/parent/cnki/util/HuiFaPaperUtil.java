package com.huifa.paper.parent.cnki.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huifa.paper.parent.cnki.exception.SessionTimeoutException;
import com.huifa.paper.parent.cnki.vo.admin.SysUserVo;
import com.huifa.paper.parent.cnki.vo.web.WebUserVo;
import com.huifa.paper.parent.common.constants.Constants;
import com.huifa.paper.parent.util.CookieUrlUtils;
import com.huifa.paper.parent.util.SSDBUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by kchen on 2016/9/9.
 */
@Component
public class HuiFaPaperUtil {

    private static final Logger logger = LoggerFactory.getLogger(HuiFaPaperUtil.class);

    @Autowired
    private SSDBUtils ssdbUtils;

    public String putPaperFreeSessionId(HttpServletRequest request, HttpServletResponse response) {
        String sessionId = StringUtils.isAnyBlank(request.getRequestedSessionId()) ? request.getSession().getId() : request.getRequestedSessionId();
        String mySessionId = CookieUrlUtils.readCookie(request, Constants.CK_S_ID);
        if (StringUtils.isNoneBlank(mySessionId)) {
            logger.debug("当前客户端有系统保存的sessionId={}", mySessionId);
        } else {
            mySessionId = sessionId;
            CookieUrlUtils.writeCookie(response, Constants.CK_S_ID, sessionId);
        }
        return mySessionId;
    }

    public boolean handleRequestHeader(String[] allowUrls, HttpServletRequest request, HttpServletResponse response) {
        String sessionId = putPaperFreeSessionId(request, response);
        String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");
        requestUrl = StringEscapeUtils.escapeHtml4(requestUrl);
        WebUserVo webUserVo = getLoginUser(sessionId);
        if (null != allowUrls && allowUrls.length >= 1)
            for (String url : allowUrls) {
                if (requestUrl.contains(url) || StringUtils.contains(Constants.FIRST_URL, requestUrl)) {
                    return true;
                }
            }
        if (webUserVo == null) {
            return getAjax(request,response);
        }
        return true;
    }

    public boolean handleAdminRequestHeader(String[] allowUrls, HttpServletRequest request, HttpServletResponse response) {
        String sessionId = putPaperFreeSessionId(request, response);
        String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");
        requestUrl = StringEscapeUtils.escapeHtml4(requestUrl);
        SysUserVo sysUserVo = getAdminLoginUser(sessionId);
        if (null != allowUrls && allowUrls.length >= 1)
            for (String url : allowUrls) {
                if (requestUrl.contains(url) || StringUtils.equals(Constants.FIRST_URL, requestUrl)) {
                    return true;
                }
            }
        if (sysUserVo == null) {
            return getAjax(request,response);
        }
        return true;
    }



    private Boolean getAjax(HttpServletRequest request,HttpServletResponse response){
        if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
            response.setHeader("sessionstatus", "timeout");//在响应头设置session状态
            response.setStatus(403);
            logger.warn("ajax请求时，用户session过期");
            return false;
        } else {
            // 未登录  跳转到登录页面
            logger.warn("用户session过期，请重新登录");
            throw new SessionTimeoutException();
            //返回到配置文件中定义的路径
        }
    }

    /**
     * 获取用户登录信息
     */
    public WebUserVo getLoginUser(String sessionId) {
        String value = ssdbUtils.getUserSession(sessionId + Constants.LOGIN_USER);
        if (StringUtils.isNoneBlank(value)) {
            JSONObject json = JSON.parseObject(value);
            return JSON.toJavaObject(json, WebUserVo.class);
        }
        return null;
    }

    /**
     * 获取admin登录信息
     * @param sessionId
     * @return
     */
    public SysUserVo getAdminLoginUser(String sessionId) {
        String value = ssdbUtils.getUserSession(sessionId + Constants.MANAGER_USER);
        if (StringUtils.isNoneBlank(value)) {
            JSONObject json = JSON.parseObject(value);
            return JSON.toJavaObject(json, SysUserVo.class);
        }
        return null;
    }
}