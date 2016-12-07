package com.huifa.paper.parent.cnki.interceptor;

import com.huifa.paper.parent.cnki.exception.SessionTimeoutException;
import com.huifa.paper.parent.cnki.intefaces.LoginCheck;
import com.huifa.paper.parent.cnki.util.HuiFaPaperUtil;
import com.huifa.paper.parent.cnki.vo.web.WebUserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by kchen on 2016/9/12.
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Autowired
    private HuiFaPaperUtil huiFaPaperUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            LoginCheck loginCheck = ((HandlerMethod) handler).getMethodAnnotation(LoginCheck.class);
            if (null != loginCheck) {
                String sessionId = huiFaPaperUtil.putPaperFreeSessionId(request, response);
                WebUserVo webUserVo = huiFaPaperUtil.getLoginUser(sessionId);
                logger.info("执行用户注解,判断用户操作该功能时是否已经登录");
                if (webUserVo == null) {
                    if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
                        response.setHeader("sessionstatus", "timeout");//在响应头设置session状态
                        response.setStatus(403);
                        logger.warn("ajax请求时，用户session过期");
                        return false;
                    } else {
                        //未登录跳转到登录页面
                        logger.warn("当前页面session已经失效，请重新登录");
                        //返回到配置文件中定义的路径
                        throw new SessionTimeoutException();
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
}