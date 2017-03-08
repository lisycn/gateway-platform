package com.yinrong.gateway.utils;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.UnsupportedEncodingException;

/**
 * 
 * <p>公用小方法/p>
 * @author weihui
 * @version $Id: Utils.java 47623 2013-06-03 08:54:07Z fangjilue $
 */
public class Utils {

    private Utils() {

    }
    
    public static int getByteLen(String s) {
        if (s == null) {
            return 0;
        }
        try {
            return s.getBytes("GBK").length;
        } catch (UnsupportedEncodingException e) {
            return s.getBytes().length;
        }
    }
    
    /**
     * 打印java 对象
     * @param obj
     * @return
     */
    public static String toString(Object obj) {
        return ToStringBuilder.reflectionToString(obj, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
