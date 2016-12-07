package com.huifa.paper.parent.cnki.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huifa.paper.parent.cnki.dao.tb.ITaoBaoOrderDao;
import com.huifa.paper.parent.cnki.properties.TaoBaoProperties;
import com.huifa.paper.parent.cnki.vo.tb.TaoBaoOrderVo;
import com.huifa.paper.parent.common.constants.Constants;
import com.huifa.paper.parent.util.DateUtil;
import com.huifa.paper.parent.util.HttpUtils;
import com.huifa.paper.parent.util.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by kchen on 2016-12-03.
 */
@Component
public class TaoBaoUtil {

    private static final Logger logger = LoggerFactory.getLogger(TaoBaoUtil.class);

    @Autowired
    private ITaoBaoOrderDao taoBaoOrderDao;

    @Autowired
    private TaoBaoProperties taoBaoProperties;

    public TaoBaoOrderVo getOrderInfo(Long tid) throws IOException {
        return getOrderAndGood(getOrderInfo(taoBaoProperties.getOrderUrl, tid, taoBaoProperties.getUserInfo()), tid);
    }

    private TaoBaoOrderVo getOrderAndGood(Map<String, Object> orderMap, Long tid) {
        TaoBaoOrderVo orderInfo = new TaoBaoOrderVo();
        JSONObject order = (JSONObject) orderMap.get(tid);
        if (StringUtils.equals(order.getString("status"), Constants.WAIT_SELLER_SEND_GOODS)) {
            String buyerNick = order.get("buyer_area").toString();
            Double payment = order.getDouble("payment");
            Integer num = order.getInteger("num");
            Long sellerId = order.getLong("seller_id");
            String sellerNick = order.get("seller_nick").toString();
            orderInfo.setSellerNick(sellerNick);
            orderInfo.setBuyerNick(buyerNick);
            orderInfo.setPayment(BigDecimal.valueOf(payment));
            orderInfo.setNumber(num);
            orderInfo.setOrderId(tid);
            logger.info("当前订单号信息order={}", order.toString());
            return orderInfo;
        }
        return null;
    }

    /**
     * 获取订单状态
     *
     * @param url
     * @param tid
     * @param userInfo
     * @return
     * @throws IOException
     */
    public static Map<String, Object> getOrderInfo(String url, Long tid, Map<String, String> userInfo) throws IOException {
        Map<String, Object> map = new LinkedHashMap<>();
        Map<String, Object> result = new LinkedHashMap<>();
        map.put("username", userInfo.get("username"));
        map.put("pwd", userInfo.get("pwd"));
        if (tid != null) {
            logger.info("当前订单号为orderId={}", tid);
            map.put("tid", tid);
            map.put("time", DateUtil.getDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
            String sign = getSign(map, userInfo.get("appKey"));
            map.put("sign", sign);
            String order = HttpUtils.httpPost(url, map);
            logger.info("从淘宝获取到的order={}", order);
            JSONObject json = JSONObject.parseObject(order);
            if (StringUtils.equals(json.get("code").toString(), "0")) {
                logger.warn("从淘宝获取数据出错errorMsg={}", json.get("errmsg").toString());
                throw new RuntimeException("从淘宝获取数据出错:" + json.get("errmsg").toString());
            } else {
                result.put(tid + "", json.get("data"));
                logger.info("从淘宝获取信息成功order={}", order);
            }
        }
        return result;
    }

    public static Boolean sendOrder(String url, String[] tids, Map<String, String> userInfo) throws IOException {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("username", userInfo.get("username"));
        map.put("pwd", userInfo.get("pwd"));
        for (String str : tids) {
            if (StringUtils.isNoneBlank(str.trim())) {
                map.put("tid", str.trim());
                map.put("time", DateUtil.getDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
                String sign = getSign(map, userInfo.get("appKey"));
                map.put("sign", sign);
                logger.info("当前发货订单号:{}", str.trim());
                String order = HttpUtils.httpPost(url, map);
                JSONObject json = JSONObject.parseObject(order);
                if (StringUtils.equals(json.get("code").toString(), "0")) {
                    logger.warn("从淘宝获取数据出错errorMsg={}", json.get("errmsg").toString());
                    throw new RuntimeException("从淘宝获取数据出错errorMsg=" + json.get("errmsg").toString());
                } else {
                    JSONObject data = JSONObject.parseObject(json.getString("data"));
                    String shipping = data.getString("shipping");
                    if (null != shipping) {
                        JSONArray array = JSONArray.parseArray(shipping);
                        String isSuccess = array.getString(0);
                        if (null != isSuccess) {
                            JSONObject result = JSONObject.parseObject(isSuccess);
                            if (StringUtils.equals(result.getString("is_success"), "true")) {
                                logger.info("发货成功,result={}", result.toString());
                                return true;
                            }
                            logger.info("自动发货不成功,result={}", result.toString());
                        } else {
                            throw new RuntimeException("自动发货失败，请重新检测");
                        }
                    } else {
                        throw new RuntimeException("自动发货失败,淘宝接口异常，请重新检测");
                    }
                }
            }
        }
        return false;
    }

    public static String getSign(Map<String, Object> map, String token) {
        String wait = "username" + map.get("username") + "pwd" + map.get("pwd") + "tid" + map.get("tid").toString().trim() + "time" + map.get("time") + token;
        return MD5Utils.getMD5String(wait);
    }
}
