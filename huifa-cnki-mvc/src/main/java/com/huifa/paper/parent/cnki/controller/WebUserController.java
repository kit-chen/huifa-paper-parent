package com.huifa.paper.parent.cnki.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by kchen on 2016-11-28.
 */
@RequestMapping(value = "/webUser")
public class WebUserController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(WebUserController.class);

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(){
        return "";
    }

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String register(){
        return "";
    }
}
