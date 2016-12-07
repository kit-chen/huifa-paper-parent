
package com.huifa.paper.parent.common.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;


public abstract class GenericDTO implements Serializable {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 3886269150595934285L;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }
}
