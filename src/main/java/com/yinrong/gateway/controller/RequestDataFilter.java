/**
 *
 */
package com.yinrong.gateway.controller;

import com.meidusa.fastjson.JSON;

import com.netfinworks.validate.exception.ValidationException;
import com.yinrong.gateway.constant.GatewayConstant;
import com.yinrong.gateway.enums.BaseField;
import com.yinrong.gateway.utils.DataConvertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * <p>注释</p>
 * @author fjl
 * @version $Id: DataConvertFilter.java, v 0.1 2013-11-15 上午10:04:25 fjl Exp $
 */
public class RequestDataFilter {
    private Logger logger = LoggerFactory.getLogger(RequestDataFilter.class);

    public Map<String, String> hande(HttpServletRequest req) throws ValidationException {
        //(1) 字符集处理
        Map<?, ?> dataMap = req.getParameterMap();
        if (logger.isDebugEnabled()) {
            logger.debug("接收到的" + req.getMethod() + "请求信息:{}", JSON.toJSON(dataMap));
        }
        try {
            String charset = getCharset(req);

            return DataConvertUtil.paramCharsetConvert(dataMap, charset, req.getMethod());

        } catch (IllegalArgumentException e) {
            logger.error("请求参数字符集不支持:"+JSON.toJSON(dataMap),e);
            throw new ValidationException(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            logger.error("请求参数字符集不支持:"+JSON.toJSON(dataMap),e);
            throw new ValidationException("不支持的字符集");
        }
    }

    private String getCharset(HttpServletRequest req) {
        String charset = req.getParameter(BaseField.INPUT_CHARSET.getCode());
        Assert.hasText(charset, BaseField.INPUT_CHARSET.getCode() + "不能为空");
        //OS系统是否支持
        Assert.isTrue(Charset.isSupported(charset), charset + "字符编码不支持");
        //程序是否支持
        Assert.isTrue(GatewayConstant.charset_gb2312.equalsIgnoreCase(charset)
                      || GatewayConstant.charset_gbk.equalsIgnoreCase(charset)
                      || GatewayConstant.charset_utf_8.equalsIgnoreCase(charset),
                      charset + "字符编码不支持");
        return charset;
    }
}
