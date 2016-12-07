package com.huifa.paper.parent.admin.controller;

import com.huifa.paper.parent.cnki.service.tb.TaoBaoSellerService;
import com.huifa.paper.parent.cnki.vo.admin.SysUserVo;
import com.huifa.paper.parent.cnki.vo.tb.TaoBaoSellerVo;
import com.huifa.paper.parent.common.constants.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by kchen on 2016/11/25.
 */
@Controller
@RequestMapping(value = "/seller")
public class TaoBaoSellerController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(TaoBaoSellerController.class);

    @Autowired
    private TaoBaoSellerService taoBaoSellerService;

    @RequestMapping(value = "/sellerCenter", method = RequestMethod.GET)
    public String sellerCenter(HttpServletRequest request, Model model) {
        try {
            SysUserVo sysUserVo = getSessionUserInfo(request);
            model.addAttribute("domain",Constants.CHECK_DOMAIN);
            if(StringUtils.equalsIgnoreCase(sysUserVo.getUserType(), Constants.USER_TYPE_TAOBAO)){
                TaoBaoSellerVo taoBaoSellerVo = taoBaoSellerService.getByAdminId(sysUserVo.getId());
                model.addAttribute("seller", taoBaoSellerVo);
            }
        } catch (Exception e) {
            logger.error("查询淘宝代理商账户信息失败");
            e.printStackTrace();
        }
        return "taobao/seller_center";
    }

    @RequestMapping(value = "/saveUserInfo", method = RequestMethod.POST)
    public String saveSellerInfo(TaoBaoSellerVo taoBaoSellerVo,Model model) {
        try {
            TaoBaoSellerVo sellerVo = taoBaoSellerService.saveSellerInfo(taoBaoSellerVo);
            model.addAttribute("seller", sellerVo);
        } catch (Exception e) {
            logger.error("查询淘宝代理商账户信息失败");
            e.printStackTrace();
        }
        return "taobao/seller_center";
    }
}