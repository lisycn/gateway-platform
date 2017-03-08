package com.yinrong.gateway.utils;

import com.netfinworks.validate.exception.ValidationException;
import com.yinrong.gateway.constant.GatewayConstant;
import com.yinrong.gateway.dto.HandleReqResult;
import com.yinrong.gateway.enums.ErrorCode;
import com.yinrong.gateway.enums.SuccessFailure;
import com.yinrong.gateway.model.ExtRequestData;
import com.yinrong.gateway.model.ExtResponseData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.http.HttpMethod;

import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author yinrong
 * @className DataConvertUtil
 * @description ${description}
 * @date 2017/3/7
 */
public class DataConvertUtil {

    private static final Charset BASIC_REQUEST_ENCODING = Charset.forName("iso8859-1");

    /**
     * 请求原始数据转换成Map
     * @param data
     * @param charset
     * @return
     * @throws UnsupportedEncodingException
     */
    public static Map<String, String> paramCharsetConvert(Map<?, ?> data, String charset,
                                                          String methodStr)
            throws UnsupportedEncodingException {

        Map<String, String> formattedParameters = new HashMap<String, String>(data.size());
        for (Map.Entry<?, ?> entry : data.entrySet()) {
            if (entry.getValue() == null || Array.getLength(entry.getValue()) == 0) {
                formattedParameters.put((String) entry.getKey(), null);
            } else {
                if (HttpMethod.GET.name().equals(methodStr)) {
                    formattedParameters.put(
                            (String) entry.getKey(),
                            new String((( String) Array.get(entry.getValue(), 0)).getBytes(BASIC_REQUEST_ENCODING), charset));
                } else {
                    formattedParameters.put((String) entry.getKey(),
//                        (String) Array.get(entry.getValue(), 0));
                            URLDecoder.decode((String) Array.get(entry.getValue(), 0), charset));
                }
            }
        }

        return formattedParameters;
    }

    /**
     * 请求Map 转换成请求业务对象
     * @param reqdata
     * @param dataMap
     * @return
     */
    public static ExtRequestData transferRequestParam(ExtRequestData reqdata,
                                                      Map<String, String> dataMap) {
        //Class<?> clazz = reqdata.getClass().getSuperclass() ;
        //Field[] fields = clazz.getDeclaredFields();
        BeanWrapper beanw = new BeanWrapperImpl(reqdata);
        for (Map.Entry<String, String> item : dataMap.entrySet()) {
            if (beanw.isWritableProperty(item.getKey())) {
                beanw.setPropertyValue(item.getKey(), item.getValue());
            }
        }

        return reqdata;
    }

    /**
     * 转换错误响应
     * @param result
     * @return
     */
    public static ExtResponseData convertError(HandleReqResult result) {
        ExtResponseData data = new ExtResponseData();
        data.setError_code(result.getErrorCode());
        data.setError_message(result.getErrorMessage());
        data.setIs_success(SuccessFailure.FAILURE.getCode());
        //TODO 其它信息
        data.set_input_charset(result.getCharset());
        return data;
    }

    /**
     * 转换异常响应
     * @param result
     * @param e
     * @return
     */
    public static HandleReqResult converResult(HandleReqResult result, ValidationException e) {
        if (result == null) {
            result = new HandleReqResult();
        }
        result.setIsSuccess(Boolean.FALSE);
        result.setErrorCode(ErrorCode.ILLEGAL_ARGUMENT.getCode());
        result.setErrorMessage(e.getMessage());
        return result;
    }

    /**
     * 转换错误响应
     * @param result
     * @param error
     * @return
     */
    public static HandleReqResult converResult(HandleReqResult result, ErrorCode error) {
        if (result == null) {
            result = new HandleReqResult();
        }
        result.setIsSuccess(Boolean.FALSE);
        result.setErrorCode(error.getCode());
        result.setErrorMessage(error.getMessage());
        return result;
    }

    /**
     * 打印map
     * @param data
     * @return
     */
    public static String toString(Map<?, ?> data) {
        if (data == null || data.isEmpty()) {
            return GatewayConstant.empty;
        }
        Iterator<?> it = data.keySet().iterator();
        String key = null;
        String value = null;
        StringBuffer sb = new StringBuffer();
        boolean first = true;
        while (it.hasNext()) {
            key = (String) it.next();
            value = (String) data.get(key);
            if (!first) {
                sb.append(GatewayConstant.and);
            }
            sb.append(key).append(GatewayConstant.eq).append(value);
            first = false;
        }
        return sb.toString();
    }

    /**
     * 转换成响应字符串
     * @param data
     * @return
     */
    public static String toResponseString(ExtResponseData data, String charset) {
        PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(data.getClass());

        PropertyDescriptor pd = null;
        StringBuffer sb = new StringBuffer();
        try {
            boolean first = true;
            Object value = null;
            for (int i = 0; i < pds.length; i++) {
                pd = pds[i];
                if (pd.getDisplayName().equals("class")) {
                    continue;
                }

                value = pd.getReadMethod().invoke(data);
                if (value == null || value.equals(GatewayConstant.empty)) {
                    continue;
                }
                value = URLEncoder.encode(value.toString(), charset);
                if (!first) {
                    sb.append(GatewayConstant.and);
                }
                sb.append(pd.getDisplayName()).append(GatewayConstant.eq).append(value.toString());
                first = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

}
