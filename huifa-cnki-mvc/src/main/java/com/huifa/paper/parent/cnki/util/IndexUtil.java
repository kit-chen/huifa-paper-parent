package com.huifa.paper.parent.cnki.util;

import com.huifa.paper.parent.util.SSDBUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by kchen on 2016-12-02.
 */
@Component
public class IndexUtil {

    private static final Logger logger = LoggerFactory.getLogger(IndexUtil.class);

    @Autowired
    private SSDBUtils ssdbUtils;

}