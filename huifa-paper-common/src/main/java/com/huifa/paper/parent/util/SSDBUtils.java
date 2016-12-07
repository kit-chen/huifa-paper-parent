package com.huifa.paper.parent.util;

import com.huifa.paper.parent.common.ssdb.SSDB;
import com.huifa.paper.parent.common.ssdb.SSDBInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by kchen on 2015-08-14.
 */
@Component
public class SSDBUtils {

    private static final Logger logger = LoggerFactory.getLogger(SSDBUtils.class);

    @Autowired
    private SSDBInfo ssdbInfo;

    private static SSDB ssdb;

    private void resetSsdb() throws Exception {
        if (ssdb != null) {
            ssdb.close();
        }
        ssdb = new SSDB(ssdbInfo.getHost(), ssdbInfo.getPort());
    }

    public synchronized void save(String key, String value) {
        try {
            ssdb.set(key, value.getBytes("UTF-8"));
        } catch (Exception e) {
            try {
                resetSsdb();
                ssdb.set(key, value.getBytes("UTF-8"));
            } catch (Exception e1) {
                logger.warn("保存数据时重置ssdb出错");
                e1.printStackTrace();
            }
        }
    }
    
    public synchronized void saveThrowException(String key, String value) throws Exception {
        try {
            ssdb.set(key, value.getBytes("UTF-8"));
        } catch (Exception e) {
            resetSsdb();
            ssdb.set(key, value.getBytes("UTF-8"));
        }
    }

    public synchronized void save(String key, String value, Integer ttl) {
        try {
            ssdb.setx(key, value.getBytes("UTF-8"), ttl);
        } catch (Exception e) {
            try {
                resetSsdb();
                ssdb.setx(key, value.getBytes("UTF-8"), ttl);
            } catch (Exception e1) {
                logger.warn("保存数据时重置ssdb出错");
                e1.printStackTrace();
            }
        }
    }

    public synchronized String getValue(String key) {
        try {
            byte[] content = ssdb.get(key);
            if (null != content) {
                return new String(content,"UTF-8");
            }
            return null;
        } catch (Exception e) {
            try {
                resetSsdb();
                byte[] content = ssdb.get(key);
                if (null != content) {
                    return new String(content,"UTF-8");
                }
                return null;
            } catch (Exception e1) {
                logger.warn("获取ssdb数据时,重置ssdb出错");
                e1.printStackTrace();
            }
        }
        return null;
    }

    public synchronized void del(String key) {
        try {
            ssdb.del(key);
        } catch (Exception e) {
            try {
                resetSsdb();
                ssdb.del(key);
            } catch (Exception e1) {
                logger.warn("删除ssdb数据时,重置ssdb出错");
                e1.printStackTrace();
            }
        }
    }

    /**
     * 取得用户session 信息
     * @param key
     * @return
     */
    public String getUserSession(String key) {
        try{
            resetSsdb();
            String value = getValue(key);
            if (null != value) {
                return value;
            }
        }catch (Exception e){
            logger.warn("ssdb重置失败");
            e.printStackTrace();
        }
        return null;
    }
}