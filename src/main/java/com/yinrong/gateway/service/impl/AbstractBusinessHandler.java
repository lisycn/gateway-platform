/**
 *
 */
package com.yinrong.gateway.service.impl;

import com.netfinworks.common.lang.StringUtil;
import com.netfinworks.validate.Validator;
import com.netfinworks.validate.exception.ValidationException;
import com.yinrong.gateway.constant.GatewayConstant;
import com.yinrong.gateway.enums.BaseField;
import com.yinrong.gateway.enums.ErrorCode;
import com.yinrong.gateway.enums.SuccessFailure;
import com.yinrong.gateway.model.ExtRequestData;
import com.yinrong.gateway.model.ExtResponseData;
import com.yinrong.gateway.model.SignData;
import com.yinrong.gateway.service.IBusinessHandler;
import com.yinrong.gateway.validator.factory.ValidatorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;
import java.beans.PropertyDescriptor;
import java.lang.reflect.ParameterizedType;

/**
 * <p>业务处理抽象类</p>
 * @author fjl
 * @version $Id: AbstractBusinessHandler.java, v 0.1 2013-11-12 下午6:12:54 fjl Exp $
 */
public abstract class AbstractBusinessHandler<T extends ExtRequestData> implements IBusinessHandler<T> {
    private Logger           logger = LoggerFactory.getLogger(this.getClass());

    /** 校验器工厂 */
    @Resource
    private ValidatorFactory validatorFactory;

    String accountSecurityLevelFlag;
    protected Logger getLogger() {
        return logger;
    }

    public void validate(T reqData) throws ValidationException {
        Validator validator = validatorFactory.load(reqData.getClass().getSimpleName());
        if (validator != null) {
            validator.validate(reqData);
        }
    }

    @SuppressWarnings("unchecked")
    public T newReqInstance() {
        try {
            ParameterizedType parameterizedType = (ParameterizedType ) getClass()
                .getGenericSuperclass();

            Class<T> entityClass = (Class<T> ) (parameterizedType.getActualTypeArguments()[0]);

            return entityClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public SignData getReqSignData(T reqData) {
        SignData sign = new SignData();
        sign.setPartnerId(reqData.getPartner_id());
        sign.setSign(reqData.getSign());
        sign.setSignType(reqData.getSign_type());
        sign.setCharset(reqData.get_input_charset());
        //获得签名字符串
        sign.setSignSrc(genSignString(reqData));
        return sign;
    }

    public SignData getRespSignData(ExtResponseData respData) {
        SignData sign = new SignData();
        sign.setPartnerId(respData.getPartner_id());
//        sign.setSignType(respData.getSign_type());
        sign.setCharset(respData.get_input_charset());
        //获得签名字符串
//        sign.setSignSrc(genSignString(respData));
        return sign;
    }

    /**
     * 设置成功响应
     * @param response
     * @return
     */
    protected ExtResponseData setSuccessResponse(ExtRequestData request, ExtResponseData response) {
        if (response == null) {
            response = new ExtResponseData();
        }
        BeanUtils.copyProperties(request, response);
        response.setIs_success(SuccessFailure.SUCCESS.getCode());
        return response;
    }

    /**
     * 设置响应码
     * @param response
     * @param responseCode
     * @return
     */
    protected ExtResponseData setResponse(ExtResponseData response, ErrorCode errorCode) {
        if (response == null) {
            response = new ExtResponseData();
        }
        response.setIs_success(SuccessFailure.FAILURE.getCode());
        response.setError_code(errorCode.getCode());
        response.setError_message(errorCode.getMessage());

        return response;
    }

    /**
     * 填充异常时的响应
     * @param request
     * @param response
     * @param e
     * @param msg
     * @return
     */
    protected ExtResponseData fillResponse(ExtRequestData request, ExtResponseData response,
                                           Exception e, String msg) {

        if (response == null) {
            response = new ExtResponseData();
        }
        String exMsg = e.getMessage();
        ErrorCode errorCode = null;
        if (e instanceof ValidationException) {
            errorCode = ErrorCode.ILLEGAL_ARGUMENT;
        } else {
            errorCode = ErrorCode.SYSTEM_ERROR;
        }
        String respMsg = errorCode.getMessage();
        if (!StringUtil.isEmpty(exMsg)) {
            //响应码描述+异常信息 构成完整响应描述信息
            if(!exMsg.equals(respMsg)){
                respMsg += ("[" + exMsg + "]");
            }
        } else {
            respMsg += ("[" + e.getClass().getName() + "]");
        }
        BeanUtils.copyProperties(request, response);
        response.setIs_success(SuccessFailure.FAILURE.getCode());
        response.setError_code(errorCode.getCode());
        response.setError_message(respMsg);
        if (StringUtil.isNotEmpty(msg)) {
            if (ErrorCode.SYSTEM_ERROR.equals(errorCode)) {
                logger.error("[APP<-MEMBER_GATEWAY]" + msg + "未知异常,request:" + request, e);
            } else {
                if (logger.isWarnEnabled()) {
                    Object[] errArray = new Object[] { msg, response.getError_code(),
                            response.getError_message(), request };
                    logger.warn("[APP<-MEMBER_GATEWAY]{}业务异常,错误码:{},错误信息:{},request:{}", errArray);
                }
            }
        }
        return response;
    }

    /**
     * 生成签名字符串
     * @param reqData
     * @return
     */
    private String genSignString(Object data) {
        PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(data.getClass());

        PropertyDescriptor pd = null;
        StringBuffer sb = new StringBuffer();
        try {
            boolean first = true;
            Object value = null;
            String key = null;
            for (int i = 0; i < pds.length; i++) {
                pd = pds[i];
                if (pd.getDisplayName().equals("class")) {
                    continue;
                }
                //排除空值
                value = pd.getReadMethod().invoke(data);
                if (value == null || value.equals(GatewayConstant.empty)) {
                    continue;
                }
                //排除 sign , sing_type 字段
                key = pd.getDisplayName();
                if(BaseField.SIGN.getCode().equals(key) || BaseField.SIGN_TYPE.getCode().equals(key)){
                    continue;
                }

                if (!first) {
                    sb.append(GatewayConstant.and);
                }
                sb.append(key).append(GatewayConstant.eq).append(value.toString());
                first = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return sb.toString();
    }

    public void setAccountSecurityLevelFlag(String accountSecurityLevelFlag) {
        this.accountSecurityLevelFlag = accountSecurityLevelFlag;
    }
}
