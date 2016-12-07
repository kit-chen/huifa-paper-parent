package com.huifa.paper.parent.cnki.service.common;

import com.huifa.paper.parent.cnki.vo.common.SystemTopicVo;
import com.huifa.paper.parent.common.page.Page;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by kchen on 2016-12-02.
 */
public interface SystemTopicService {

    SystemTopicVo getById(Long id);

    Map<String, Object> findByList(Page page, SystemTopicVo systemTopicVo);

    List<SystemTopicVo> findByRecommend();

    SystemTopicVo beforeOrAfter(Long currentId, String stepType);

    SystemTopicVo createTopic(SystemTopicVo systemTopicVo) throws IOException;

    void updateTopic(SystemTopicVo systemTopicVo);

    void deleteTopic(Long id);

    void logicDeleteTopic(Long id);
}
