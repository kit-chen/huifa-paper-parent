package com.huifa.paper.parent.cnki.dto;

import com.huifa.paper.parent.common.dto.GenericDTO;

/**
 * Created by kchen on 2016-12-03.
 */
public class CheckTaskDto extends GenericDTO {

    private String title;    //标题
    private String author;   //作者
    private Long tid;        //淘宝订单号

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }
}
