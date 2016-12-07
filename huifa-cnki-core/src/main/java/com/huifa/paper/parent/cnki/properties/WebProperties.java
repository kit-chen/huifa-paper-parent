package com.huifa.paper.parent.cnki.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by kchen on 2016/8/17.
 */
@Component
public class WebProperties {

    //ssdb地址
    @Value("#{configProperties['sys.ssdbHost']}")
    public String ssdbHost;
    //ssdb端口
    @Value("#{configProperties['sys.ssdbPort']}")
    public String ssdbPort;

    //qq登录qqAppId
    @Value("#{configProperties['sys.qqAppId']}")
    public String qqAppId;

    //qq登录qqAppKey
    @Value("#{configProperties['sys.qqAppKey']}")
    public String qqAppKey;

    //qq登录回调地址
    @Value("#{configProperties['sys.qqRedirectUrl']}")
    public String qqRedirectUrl;

    //微博登录appKey
    @Value("#{configProperties['sys.weiBoAppKey']}")
    public String weiBoAppKey;

    //微博登录appSecret
    @Value("#{configProperties['sys.weiBoAppSecret']}")
    public String weiBoAppSecret;

    //微博登录回调地址
    @Value("#{configProperties['sys.weiBoRedirectUrl']}")
    public String weiBoRedirectUrl;

    //微信登录wechatAppId
    @Value("#{configProperties['sys.weChatAppId']}")
    public String weChatAppId;

    //微信登录weChatSecret
    @Value("#{configProperties['sys.weChatSecret']}")
    public String weChatSecret;

    //微信登录回调地址
    @Value("#{configProperties['sys.weChatRedirectUrl']}")
    public String weChatRedirectUrl;

    @Value("#{configProperties['sys.weChatQRTicketUrl']}")
    public String weChatQRTicketUrl;

    //检测报告存放目录
    @Value("#{configProperties['report.file.path']}")
    public String reportFilePath;
}