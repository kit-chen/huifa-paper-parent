package com.huifa.paper.parent.cnki.service.common.impl;

import com.github.pagehelper.PageHelper;
import com.huifa.paper.parent.cnki.dao.common.ISystemTopicDao;
import com.huifa.paper.parent.cnki.entity.common.SystemTopic;
import com.huifa.paper.parent.cnki.service.common.SystemTopicService;
import com.huifa.paper.parent.cnki.util.WebCommonUtil;
import com.huifa.paper.parent.cnki.vo.common.SystemTopicVo;
import com.huifa.paper.parent.common.page.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * Created by kchen on 2016-12-02.
 */
@Service(value = "systemTopicService")
public class SystemTopicServiceImpl implements SystemTopicService {

    private static final Logger logger = LoggerFactory.getLogger(SystemTopicServiceImpl.class);

    @Autowired
    private ISystemTopicDao systemTopicDao;

    @Override
    public SystemTopicVo getById(Long id) {
        return systemTopicDao.getById(id);
    }

    @Override
    public Map<String, Object> findByList(Page page, SystemTopicVo systemTopicVo) {
        Map<Serializable, Serializable> queryMap = new LinkedHashMap<>();
        if (null != systemTopicVo.getTopicType()) {
            queryMap.put("topicType", systemTopicVo.getTopicType());
        }
        PageHelper.startPage(page.getCurrentPage(), page.getPageSize());
        List<SystemTopicVo> list = systemTopicDao.getSysTopicByMap(queryMap);
        Map<String, Object> result = new LinkedHashMap<>();
        Integer totalCount = systemTopicDao.getSysTopicCountByMap(queryMap);
        result.put("total", totalCount);
        result.put("rows", list);
        return result;
    }

    @Override
    public List<SystemTopicVo> findByRecommend() {
        Page page = new Page();
        page.setCurrentPage(1);
        return null;
    }

    @Override
    public SystemTopicVo beforeOrAfter(Long currentId, String stepType) {
        Map<Serializable,Serializable> queryMap = new HashMap<>();
        queryMap.put("id",currentId);
        queryMap.put("stepType",stepType);
        List<SystemTopicVo> list = systemTopicDao.getBeforeOrAfter(queryMap);
        WebCommonUtil.getTopicWithHtml(list);
        if(null != list && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public SystemTopicVo createTopic(SystemTopicVo systemTopicVo) throws IOException {
        SystemTopic sysTopic = new SystemTopic();
        BeanUtils.copyProperties(systemTopicVo,sysTopic);
//        if(null != systemTopicVo.getItemImageFile() && !systemTopicVo.getItemImageFile().isEmpty()){
//            String date = DateUtil.getDate(new Date(),"yyyyMMdd");
//            String suffix = PaperTimeFileUtil.getFileSuffix(sysTopicDto.getItemImageFile().getOriginalFilename());
//            String fileName = RandomStringUtils.randomAlphanumeric(20).toLowerCase() + suffix;
//            String filePath = webProperties.attachmentPath + "topicImage/" + date + "/";
//            File file = new File(filePath);
//            if(!file.exists()){
//                file.mkdirs();
//            }
//            String itemImageUrl = webProperties.attachmentUrl + "topicImage/" + date + "/" + fileName;
//            FileUtils.copyInputStreamToFile(sysTopicDto.getItemImageFile().getInputStream(), new File(filePath + fileName));
//            sysTopic.setItemImageUrl(itemImageUrl);
//        }
        sysTopic.setCreateTime(new Date());
        systemTopicDao.createTopic(sysTopic);
        systemTopicVo.setId(sysTopic.getId());
        return systemTopicVo;
    }

    @Override
    public void updateTopic(SystemTopicVo systemTopicVo) {
        Map<Serializable,Serializable> updateMap = new LinkedHashMap<>();
        updateMap.put("id",systemTopicVo.getId());
        updateMap.put("title",systemTopicVo.getTitle());
        updateMap.put("content",systemTopicVo.getContent());
        updateMap.put("topicType",systemTopicVo.getTopicType());
        systemTopicDao.updateTopic(updateMap);
    }

    @Override
    public void deleteTopic(Long id) {
        systemTopicDao.deleteSysTopicById(id);
    }

    @Override
    public void logicDeleteTopic(Long id) {
        systemTopicDao.logicDelSysTopicById(id);
    }
}