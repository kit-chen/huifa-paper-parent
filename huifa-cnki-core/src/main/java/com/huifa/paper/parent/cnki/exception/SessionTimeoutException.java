package com.huifa.paper.parent.cnki.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by kchen on 2016/7/7.
 */
public class SessionTimeoutException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -3303216158460090677L;
    private static final Logger logger = LoggerFactory.getLogger(SessionTimeoutException.class);

    @Override
    public void printStackTrace() {
        //super.printStackTrace();
        logger.warn("用户登录超时或者需要登录，无需打印异常日志！");
    }
}
