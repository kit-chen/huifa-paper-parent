package com.huifa.paper.parent.cnki.interceptor;

import com.huifa.paper.parent.cnki.properties.WebProperties;
import com.huifa.paper.parent.cnki.util.HuiFaPaperUtil;
import com.huifa.paper.parent.cnki.vo.web.WebUserVo;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    private HuiFaPaperUtil huiFaPaperUtil;

    @Autowired
    protected WebProperties webProperties;

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
                WebUserVo webUserVo = huiFaPaperUtil.getLoginUser(sessionId);
                if (null != webUserVo) {
                    request.setAttribute("loginUser", webUserVo);
                }
                request.setAttribute("appId", webProperties.weChatAppId);
                request.setAttribute("redirectUri", webProperties.weChatRedirectUrl);
            }
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return huiFaPaperUtil.handleRequestHeader(allowUrls, request, response);
    }
}