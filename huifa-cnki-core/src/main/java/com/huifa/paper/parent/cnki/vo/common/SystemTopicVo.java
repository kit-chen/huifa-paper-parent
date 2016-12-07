package com.huifa.paper.parent.cnki.vo.common;

import com.huifa.paper.parent.cnki.entity.common.SystemTopic;

/**
 * Created by kchen on 2016-12-01.
 */
public class SystemTopicVo extends SystemTopic {

    private String topicTypeName;

    private String contentText;
    private String shortContent;
    private String shortContent41;

    private Integer page;
    private Integer rows;

    public String getTopicTypeName() {
        return topicTypeName;
    }

    public void setTopicTypeName(String topicTypeName) {
        this.topicTypeName = topicTypeName;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public String getShortContent() {
        return shortContent;
    }

    public void setShortContent(String shortContent) {
        this.shortContent = shortContent;
    }

    public String getShortContent41() {
        return shortContent41;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public void setShortContent41(String shortContent41) {
        this.shortContent41 = shortContent41;
    }
}