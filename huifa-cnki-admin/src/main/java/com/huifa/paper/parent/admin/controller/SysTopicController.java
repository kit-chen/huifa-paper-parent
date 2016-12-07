package com.huifa.paper.parent.admin.controller;

import com.huifa.paper.parent.cnki.service.common.SystemTopicService;
import com.huifa.paper.parent.cnki.util.BaiDuCurlUtil;
import com.huifa.paper.parent.cnki.vo.admin.SysUserVo;
import com.huifa.paper.parent.cnki.vo.common.SystemTopicVo;
import com.huifa.paper.parent.common.page.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kchen on 2015-09-17.
 */
@Controller
@RequestMapping("/article")
public class SysTopicController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(SysTopicController.class);

    @Autowired
    private SystemTopicService systemTopicService;

    @RequestMapping(value = "/articleTabs", method = RequestMethod.GET)
    public String article() {
        return "systopic/article";
    }

    @RequestMapping(value = "/getArticleById", method = RequestMethod.POST)
    @ResponseBody
    public SystemTopicVo getArticleById(Long id) {
        try {
            return systemTopicService.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/getAllArticle", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getAllArticle(SystemTopicVo systemTopicVo) {
        try {
            Page page = new Page();
            page.setCurrentPage(systemTopicVo.getPage());
            page.setPageSize(systemTopicVo.getRows());
            return systemTopicService.findByList(page, systemTopicVo);
        } catch (Exception e) {
            logger.warn("查询所有的文章失败");
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/addArticle", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addArticle(SystemTopicVo systemTopicVo, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        try {
            SysUserVo sysUserVo = getSessionUserInfo(request);
            SystemTopicVo result = systemTopicService.createTopic(systemTopicVo);
//            curl(result);//推送
            map.put("state", "success");
        } catch (Exception e) {
            logger.warn("提交文章失败");
            map.put("state", "fail");
            e.printStackTrace();
        }
        return map;
    }

    private void curl(SystemTopicVo result) {
        String url = "http://data.zz.baidu.com/urls?site=www.papertime.cc&token=PtNTZtNs5ZyTUXDe ";//网站的服务器连接
        //需要推送的网址
        String targetUrl = "http://www.papertime.cc/industry/%s.html";
        String curl = String.format(targetUrl, result.getId());
        String[] param = {
                curl
        };
        String json = BaiDuCurlUtil.post(url, param);//执行推送方法
        logger.warn("推送结果为={}", json);
    }

    @RequestMapping(value = "/updateArticle", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateArticle(SystemTopicVo systemTopicVo, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        try {
            SystemTopicVo sysTopicVo = new SystemTopicVo();
            systemTopicService.updateTopic(sysTopicVo);
            map.put("state", "success");
        } catch (Exception e) {
            logger.warn("更新文章失败");
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "/deleteArticle", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteArticle(HttpServletRequest request, Long id) {
        Map<String, Object> map = new HashMap<>();
        try {
            systemTopicService.logicDeleteTopic(id);
            map.put("state", "success");
        } catch (Exception e) {
            logger.warn("删除文章失败");
            e.printStackTrace();
        }
        return map;
    }
}
