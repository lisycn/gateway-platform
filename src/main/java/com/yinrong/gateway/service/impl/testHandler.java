package com.yinrong.gateway.service.impl;

import com.netfinworks.validate.exception.ValidationException;
import com.yinrong.gateway.model.ExtRequestData;
import com.yinrong.gateway.model.ExtResponseData;
import com.yinrong.gateway.model.SignData;
import com.yinrong.gateway.service.IBusinessHandler;

/**
 * @author yinrong
 * @className testHandler
 * @description ${description}
 * @date 2017/3/7
 */
public class testHandler implements IBusinessHandler {
    public void validate(ExtRequestData reqData) throws ValidationException {

    }

    public SignData getReqSignData(ExtRequestData reqData) {
        return null;
    }

    public ExtRequestData newReqInstance() {
        return null;
    }

    public ExtResponseData handle(ExtRequestData reqData) {
        System.out.println("service: test");
        return null;
    }

    public SignData getRespSignData(ExtResponseData respData) {
        return null;
    }
}
