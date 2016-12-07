package com.huifa.paper.parent.cnki.exception;


import com.huifa.paper.parent.common.constants.Constants;

/**
 * Created by Administrator on 2016/8/26.
 */
public class BusinessException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 3995132117400493622L;

    public BusinessException(String msg) {
        super(msg);
    }

    private Integer exceptionCode = Constants.EXCEPTION_CODE_PARAM_ERROR;

    public Integer getExceptionCode() {
        return exceptionCode;
    }
}
