package com.huifa.paper.parent.cnki.controller;

import com.alibaba.fastjson.JSONObject;
import com.huifa.paper.parent.cnki.dto.CheckTaskDto;
import com.huifa.paper.parent.cnki.service.tb.TaoBaoSellerService;
import com.huifa.paper.parent.cnki.util.TaoBaoUtil;
import com.huifa.paper.parent.cnki.vo.tb.TaoBaoOrderVo;
import com.huifa.paper.parent.cnki.vo.tb.TaoBaoSellerVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kchen on 2016-12-03.
 */
@Controller(value = "/checkDirect")
public class CheckDirectController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(CheckDirectController.class);

    @Autowired
    private TaoBaoSellerService taoBaoSellerService;

    @Autowired
    private TaoBaoUtil taoBaoUtil;

    @RequestMapping(value = "/{type}/submit", method = RequestMethod.POST)
    @ResponseBody
    public String submit(@RequestParam(value = "file") MultipartFile file,
                         CheckTaskDto checkTaskDto, @PathVariable("type") String type) {
        String result = "";
        try {

        } catch (Exception e) {
            logger.warn("plmc提交失败");
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/getTaoBaoOrder", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getTaoBaoOrder(Long tid) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", "fail");
        try {
            TaoBaoOrderVo orderInfo = taoBaoUtil.getOrderInfo(tid);
            if (null != orderInfo) {
                map.put("status", "success");
            }
        } catch (Exception e) {
            logger.warn("获取订单号出错");
            e.printStackTrace();
        }
        return map;
    }
}