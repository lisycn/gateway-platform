/**
 *
 */
package com.yinrong.gateway.controller;

import com.meidusa.fastjson.JSON;

import com.netfinworks.validate.Validator;
import com.netfinworks.validate.exception.ValidationException;
import com.yinrong.gateway.constant.GatewayConstant;
import com.yinrong.gateway.dto.HandleReqResult;
import com.yinrong.gateway.enums.BaseField;
import com.yinrong.gateway.enums.ErrorCode;
import com.yinrong.gateway.model.ExtRequestData;
import com.yinrong.gateway.model.ExtResponseData;
import com.yinrong.gateway.model.SignData;
import com.yinrong.gateway.service.IBusinessHandler;
import com.yinrong.gateway.service.ISecurityProvider;
import com.yinrong.gateway.utils.DataConvertUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>处理前端提交的请求</p>
 * @author fjl
 * @version $Id: ReceiveReqController.java, v 0.1 2013-11-11 下午6:48:26 fjl Exp $
 */
public class FrontendReqController extends AbstractController {
    private Logger                                        logger = LoggerFactory
                                                                     .getLogger(FrontendReqController.class);

    private ISecurityProvider securityProvider;

    private Validator                                     baseReqValidator;

    private RequestDataFilter                             dataFilter;

    private Map<String, IBusinessHandler<ExtRequestData>> handeMap;

    /*
     * 前置处理
     */
    HandleReqResult beforeHandler(HttpServletRequest req) {
        HandleReqResult result = new HandleReqResult();

        Map<String, String> data;
        try {
            //(1) 字符集处理
            data = dataFilter.hande(req);
            result.setCharset(data.get(BaseField.INPUT_CHARSET.getCode()));
            if (logger.isDebugEnabled()) {
                logger.debug("接收到的请求信息解码后:{}", JSON.toJSON(data));
            }
            //(2) 基本数据校验
            baseReqValidator.validate(data);
        } catch (ValidationException e) {
            return DataConvertUtil.converResult(result, e);
        }

        //获得服务接口
        String service = data.get(BaseField.SERVICE.getCode());
        IBusinessHandler<ExtRequestData> handler = getBusinessHandler(service);
        if (handler == null) {
            return DataConvertUtil.converResult(result, ErrorCode.ILLEGAL_SERVICE);
        }
        //根据服务代码，解析得到外部请求对象
        ExtRequestData reqData = DataConvertUtil.transferRequestParam(handler.newReqInstance(),
            data);
        if (logger.isDebugEnabled()) {
            logger.debug("转换成具体接口请求对象:{}", reqData);
        }
        //(3) 验签获取签名参数
        SignData signData = handler.getReqSignData(reqData);
        /*if (!securityProvider.verifySignature(signData)) {
            logger.error("验证签名失败:{}", reqData);
            return DataConvertUtil.converResult(result, ErrorCode.ILLEGAL_SIGN);
        }*/

        // 防XSS
        data = auniXSS(data);
        reqData = DataConvertUtil.transferRequestParam(handler.newReqInstance(), data);

        //(4) 业务参数验证
        try {
            handler.validate(reqData);
        } catch (ValidationException e) {
            logger.error("请求参数业务验证:" + reqData, e);
            return DataConvertUtil.converResult(result, e);
        }
        result.setReqData(reqData);
        result.setIsSuccess(Boolean.TRUE);
        return result;
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest req, HttpServletResponse resp)
                                                                                                  throws Exception {
        //(1) 请求处理
        HandleReqResult result = beforeHandler(req);
        if (!result.getIsSuccess()) {
            //TODO 输出错误
            ExtResponseData responseData = DataConvertUtil.convertError(result);
            writeResp(resp, responseData);
            return null;
        }
        IBusinessHandler<ExtRequestData> handler = getBusinessHandler(result.getService());
        //(2) 调用服务
        ExtResponseData responseData = handler.handle(result.getReqData());
        //(3) 响应处理
        afterHandler(resp, responseData, handler);
        return null;
    }

    /*
     * 后置处理
     */
    void afterHandler(HttpServletResponse resp, ExtResponseData respData,
                      IBusinessHandler<ExtRequestData> handler) {
        SignData signData = handler.getRespSignData(respData);
        //        securityProvider.generateSignature(signData);
        //        respData.setSign(signData.getSign());
        respData.setSign(null); // 返回不签名
        respData.setSign_type(null); // 返回不签名
        writeResp(resp, respData);
    }

    private void writeResp(HttpServletResponse resp, ExtResponseData respData) {
        try {
            //            if (logger.isDebugEnabled()) {
            //                logger.debug("发送编码前响应字符串:{}", respData);
            //            }
            String charset = StringUtils.isBlank(respData.get_input_charset()) ? GatewayConstant.charset_utf_8
                : respData.get_input_charset();
            //            String content = DataConvertUtil.toResponseString(respData,charset);
            //            if (logger.isDebugEnabled()) {
            //                logger.debug("发送编码后响应字符串:{}", content);
            //            }

            String content = JSON.toJSONString(respData);
            //输出字符流
            resp.setContentType("text/plain;charset=" + (charset));
            resp.setCharacterEncoding(charset);
            resp.getWriter().print(content);
            resp.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
            resp.setStatus(500);
        }
    }

    private Map<String, String> auniXSS(Map<String, String> reqMap) {
        Map<String, String> result = new HashMap<String, String>(reqMap.size());
        for (Map.Entry<String, String> entry : reqMap.entrySet()) {
            if (entry.getValue() == null) {
                result.put((String ) entry.getKey(), null);
            } else {
                String value = entry.getValue().replaceAll("&", "&amp;").replaceAll("<", "&lt;")
                    .replaceAll(">", "&gt;").replaceAll("\"", "&quot;").replaceAll("'", "&acute;");
                result.put((String ) entry.getKey(), value);
            }
        }

        return result;
    }

    public void setHandeMap(Map<String, IBusinessHandler<ExtRequestData>> handeMap) {
        this.handeMap = handeMap;
    }

    public IBusinessHandler<ExtRequestData> getBusinessHandler(String service) {
        return handeMap.get(service);
    }

    public void setSecurityProvider(ISecurityProvider securityProvider) {
        this.securityProvider = securityProvider;
    }

    public void setBaseReqValidator(Validator baseReqValidator) {
        this.baseReqValidator = baseReqValidator;
    }

    public void setDataFilter(RequestDataFilter dataFilter) {
        this.dataFilter = dataFilter;
    }
}
