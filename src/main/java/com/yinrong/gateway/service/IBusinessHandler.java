/**
 * 
 */
package com.yinrong.gateway.service;


import com.netfinworks.validate.exception.ValidationException;
import com.yinrong.gateway.model.ExtRequestData;
import com.yinrong.gateway.model.ExtResponseData;
import com.yinrong.gateway.model.SignData;

/**
 * 
 * <p>注释</p>
 * @author fjl
 * @version $Id: IBusinessHandler.java, v 0.1 2013-11-12 下午1:46:37 fjl Exp $
 */
public interface IBusinessHandler<T extends ExtRequestData> {
    
    /**
     * 请求参数验证
     * @param reqData
     * @throws ValidationException
     */
    public abstract void validate(T reqData) throws ValidationException;
    
    /**
     * 请求数据的签名数据对象
     * @param reqData
     * @return
     */
    public abstract SignData getReqSignData(T reqData);

    /**
     * 实例化一个请求接口对象
     * @return
     */
    public abstract T newReqInstance();

    /**
     * 处理接口
     * @param reqData 接口对象
     * @return
     */
    public abstract ExtResponseData handle(T reqData);
    
    
    /**
     * 响应数据的签名数据对象
     * @param respData
     */
    public abstract SignData getRespSignData(ExtResponseData respData);

}
