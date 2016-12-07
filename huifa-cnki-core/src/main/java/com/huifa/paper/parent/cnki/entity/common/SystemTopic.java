package com.huifa.paper.parent.cnki.entity.common;

import com.huifa.paper.parent.common.domain.BusinessEntity;

import java.util.Date;

/**
 * Created by kchen on 2016-12-01.
 */
public class SystemTopic extends BusinessEntity {

    private String title;
    private String content;
    private Date createTime;
    private Integer topicType;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getTopicType() {
        return topicType;
    }

    public void setTopicType(Integer topicType) {
        this.topicType = topicType;
    }
}