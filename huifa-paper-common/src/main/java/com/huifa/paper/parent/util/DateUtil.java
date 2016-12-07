package com.huifa.paper.parent.util;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by kchen on 2015-11-05.
 */
public final class DateUtil {
	
	public static final String time_format_yyyyMMddhhmmssSSS = "yyyyMMddhhmmssSSS";

    /**
     * 完整时间 yyyy-MM-dd HH:mm:ss
     */
    public static final String simple = "yyyy-MM-dd HH:mm:ss";

    /**
     * 年月日(无下划线) yyyyMMdd
     */
    public static final String dtShort = "yyyyMMdd";

    public static final String format_yyyy_MM_dd = "yyyy-MM-dd";

    /**
     * 获取系统当期年月日(精确到天)，格式：yyyyMMdd
     *
     * @return
     */
    public static String getDate(Date time, String format) {
        if (StringUtils.isAnyBlank(format)) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        DateFormat df = new SimpleDateFormat(format);
        return df.format(time);
    }

    public static Date parseToDate(String str, String patten) {
        DateFormat df = new SimpleDateFormat(patten);
        try {
            return df.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将timestamp转换成date
     * @author hellostoy
     * @param tt
     * @return
     */
    public static Date timestampToDate(Timestamp tt){
        return new Date(tt.getTime());
    }
    
    /**
     * 爱编辑专用特殊逻辑(不能修改)
     * @param date
     * @param month
     * @return
     */
    public static Date addMonth(Date date,int month){
    	Date now = new Date();
    	if(null == date || date.before(now)){
    		date = new Date();
    	}
    	Calendar calendar=Calendar.getInstance();  
    	calendar.setTime(date);
    	calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+month);
    	return calendar.getTime();
    }
    
    /**
     * @param startDate
     * @param seconds 相差时间（单位秒）
     * @return
     */
    public static Date calculateEndDate(Date startDate,Long seconds){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.SECOND,seconds.intValue());
        return calendar.getTime();
//    	startDate.setTime(startDate.getTime()+seconds * 1000);
//    	return startDate;
    }
}
