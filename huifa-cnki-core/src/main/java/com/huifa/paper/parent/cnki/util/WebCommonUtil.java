package com.huifa.paper.parent.cnki.util;

import com.huifa.paper.parent.cnki.vo.common.SystemTopicVo;
import com.huifa.paper.parent.util.HuiFaHtmlUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by kchen on 2016/10/19.
 */
public class WebCommonUtil {

    public static void getTopicWithHtml(List<SystemTopicVo> source){
        for(SystemTopicVo sysTopicVo : source){
            String content = HuiFaHtmlUtil.htmlFilter(HuiFaHtmlUtil.stripHtml(sysTopicVo.getContent()));
            sysTopicVo.setShortContent(content);
            if(content.length() > 41){
                sysTopicVo.setShortContent41(StringUtils.substring(content, 0, 41) + "...");
            }
            if(content.length() > 310){
                sysTopicVo.setShortContent(StringUtils.substring(content, 0, 310) + "...");
            }
            sysTopicVo.setContentText(content);
        }
    }
}
