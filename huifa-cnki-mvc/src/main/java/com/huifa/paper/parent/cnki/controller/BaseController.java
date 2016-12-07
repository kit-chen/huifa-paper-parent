package com.huifa.paper.parent.cnki.controller;

import com.alibaba.fastjson.JSON;
import com.huifa.paper.parent.cnki.properties.TaoBaoProperties;
import com.huifa.paper.parent.cnki.service.tb.TaoBaoSellerService;
import com.huifa.paper.parent.cnki.vo.tb.TaoBaoSellerVo;
import com.huifa.paper.parent.cnki.vo.web.WebUserVo;
import com.huifa.paper.parent.common.constants.Constants;
import com.huifa.paper.parent.util.CookieUrlUtils;
import com.huifa.paper.parent.util.SSDBUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by kchen on 2016-11-28.
 */
public class BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private SSDBUtils ssdbUtils;

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected Model model;

    @Autowired
    protected TaoBaoSellerService taoBaoSellerService;

    @Autowired
    protected TaoBaoProperties taoBaoProperties;

    @ModelAttribute
    protected void init(HttpServletRequest request, HttpServletResponse response, Model model) {
        this.model = model;
        this.request = request;
        this.response = response;
    }

    /**
     * 保存用户session信息到ssdb
     *
     * @param webUserVo
     */
    protected void saveSessionUserInfo(WebUserVo webUserVo) {
        String sessionId = requestCookieId();
        String value = JSON.toJSONString(webUserVo);
        ssdbUtils.save(sessionId + Constants.LOGIN_USER, value, Constants.USER_SESSION_TIME);
        request.setAttribute(Constants.LOGIN_USER, webUserVo);
    }

    /**
     * 获取sessionId
     *
     * @return
     */
    protected String requestCookieId() {
        return CookieUrlUtils.readCookie(request, Constants.CK_S_ID);
    }

    /**
     * 删除用户session信息
     */
    protected void delSessionUserInfo() {
        String sessionId = requestCookieId();
        ssdbUtils.del(sessionId + Constants.LOGIN_USER);
        request.removeAttribute(Constants.LOGIN_USER);
    }

    public String getAgentInfo() {
        String url = request.getRequestURL().toString();
        logger.info("当前地址为url={}", url);
        String secUrl = StringUtils.replace(url, "http://", "");
        Integer whios = StringUtils.indexOf(secUrl, Constants.CHECK_DOMAIN);
        String agent = "";
        if (whios > 0) {
            agent = StringUtils.substring(secUrl, 0, whios - 1);
        } else {
            agent = "www";
        }
        logger.warn("当前二级域名为:url={}", agent);
        request.setAttribute("agent", agent);
        return agent;
    }

    public Boolean isTaoBaoDomain(){
        String serverName = request.getServerName();
        String checkDomain = taoBaoProperties.checkDomain;   //检测地址
        if (StringUtils.indexOf(serverName,checkDomain) > 0) {   //如果是免费检测地址
            String defaultDomain = StringUtils.substring(serverName,0,serverName.length() - checkDomain.length() - 1);
            String[] sub_domain = StringUtils.split(defaultDomain,"\\.");
            if (sub_domain.length >= 2) {                  //是的检测地址
                TaoBaoSellerVo taoBaoSeller = taoBaoSellerService.getByDomain(sub_domain[0]);
                if(null != taoBaoSeller){
                    model.addAttribute("seller",taoBaoSeller);
                    model.addAttribute("type",sub_domain[1]);
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }
}