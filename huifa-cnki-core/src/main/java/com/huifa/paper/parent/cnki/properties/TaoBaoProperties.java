package com.huifa.paper.parent.cnki.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by kchen on 2016-12-05.
 */
@Component
public class TaoBaoProperties {

    @Value("#{configProperties['user_upload_path']}")
    public String userUploadPath;

    @Value("#{configProperties['user_download_path']}")
    public String userDownloadPath;

    @Value("#{configProperties['USERNAME']}")
    public String userName;

    @Value("#{configProperties['PWD']}")
    public String pwd;

    @Value("#{configProperties['APP_KEY']}")
    public String appKey;

    @Value("#{configProperties['CHARSET']}")
    public String charset;

    @Value("#{configProperties['GET_ORDER_URL']}")
    public String getOrderUrl;

    @Value("#{configProperties['SEND_ORDER_URL']}")
    public String sendOrderUrl;

    @Value("#{configProperties['RATE_ORDER_URL']}")
    public String rateOrderUrl;

    @Value("#{configProperties['GOOD_HEAD']}")
    public String goodHead;

    public Map<String, String> getUserInfo() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("username", userName);
        map.put("pwd", pwd);
        map.put("appKey", appKey);
        return map;
    }

    //domain
    @Value("#{configProperties['check.domain']}")
    public String checkDomain;
}