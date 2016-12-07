package com.huifa.paper.parent.cnki.controller;

import com.huifa.paper.parent.cnki.service.tb.TaoBaoSellerService;
import com.huifa.paper.parent.cnki.vo.tb.TaoBaoSellerVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by kchen on 2016-11-28.
 */
@Controller
@RequestMapping
public class IndexController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        try {
            if(isTaoBaoDomain()){
                //当前不需要登录提交
                return "checkDirect/submit";
            }
        } catch (Exception e) {

        }
        return "index";
    }
}