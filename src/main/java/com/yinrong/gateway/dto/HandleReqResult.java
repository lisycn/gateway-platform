/**
 * 
 */
package com.yinrong.gateway.dto;

import com.yinrong.gateway.model.ExtRequestData;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * <p>请求处理结果</p>
 * @author fjl
 * @version $Id: HandleResult.java, v 0.1 2013-11-12 上午10:15:25 fjl Exp $
 */
public class HandleReqResult {
    private String charset;     //字符集
    private Boolean isSuccess;   //成功标识
    private String errorCode;   //返回错误码
    private String errorMessage; //返回错误原因
    private ExtRequestData reqData;     //请求数据
    
    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ExtRequestData getReqData() {
        return reqData;
    }

    public void setReqData(ExtRequestData reqData) {
        this.reqData = reqData;
    }

    public String getService() {
        return reqData.getService();
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
