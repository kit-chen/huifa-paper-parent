package com.huifa.paper.parent.admin.interceptor;

import com.huifa.paper.parent.cnki.util.HuiFaPaperUtil;
import com.huifa.paper.parent.cnki.vo.admin.SysUserVo;
import com.huifa.paper.parent.common.constants.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by kchen on 2016/7/7.
 */
@Component
public class UserInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger = LoggerFactory.getLogger(UserInterceptor.class);

    @Autowired
    private HuiFaPaperUtil huiFaPaperUtil;

    public String[] allowUrls; //还没发现可以直接配置不拦截的资源，所以在代码里面来排除

    public void setAllowUrls(String[] allowUrls) {
        this.allowUrls = allowUrls;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Long version = System.currentTimeMillis();
        request.setAttribute("systemVersion", version);
        if (null != modelAndView) {
            String sessionId = huiFaPaperUtil.putPaperFreeSessionId(request, response);
            if (StringUtils.isNotEmpty(sessionId)) {
                SysUserVo sysUserVo = huiFaPaperUtil.getAdminLoginUser(sessionId);
                if (null != sysUserVo) {
                    modelAndView.addObject(Constants.MANAGER_USER, sysUserVo);
                }
            }
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return huiFaPaperUtil.handleAdminRequestHeader(allowUrls, request, response);
    }
}