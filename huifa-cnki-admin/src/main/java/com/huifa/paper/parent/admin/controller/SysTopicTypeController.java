package com.huifa.paper.parent.admin.controller;

import com.huifa.paper.parent.cnki.service.common.SysTopicTypeService;
import com.huifa.paper.parent.cnki.vo.common.SysTopicTypeVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by kchen on 2016/10/13.
 */
@Controller
@RequestMapping(value = "/sysTopicType")
public class SysTopicTypeController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(SysTopicTypeController.class);

    @Autowired
    private SysTopicTypeService sysTopicTypeService;

    @RequestMapping(value = "/getAllType", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public List<SysTopicTypeVo> getAllType(Model model) {
        try {
            return sysTopicTypeService.getList();
        } catch (Exception e) {
            logger.warn("warn={}", "error");
            e.printStackTrace();
        }
        return null;
    }
}