/**
 *
 */
package com.yinrong.gateway.service.impl;


import com.yinrong.gateway.model.CreatePersonalRequest;
import com.yinrong.gateway.model.CreatePersonalResponse;
import com.yinrong.gateway.model.ExtResponseData;

/**
 * <p>创建个人户处理器</p>
 * @author fjl
 * @version $Id: CreatePersonalMemberHandler.java, v 0.1 2013-11-12 下午6:07:06 fjl Exp $
 */
public class CreatePersonalMemberHandler extends AbstractBusinessHandler<CreatePersonalRequest> {




    public ExtResponseData handle(CreatePersonalRequest reqData) {

        CreatePersonalResponse respData = new CreatePersonalResponse();
        try {
            System.out.println("个人开户测试");
        } catch (Exception e) {
            fillResponse(reqData, respData, e, "开个人户");
        }
        return respData;
    }

}
