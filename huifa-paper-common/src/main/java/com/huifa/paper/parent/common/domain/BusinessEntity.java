
package com.huifa.paper.parent.common.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

public class BusinessEntity implements Serializable {

    protected Long id;
    protected Integer deleteStatus;

    public Long getId() {
        return id;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }
}