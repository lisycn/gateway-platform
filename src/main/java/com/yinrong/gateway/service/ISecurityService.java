/**
 * 
 */
package com.yinrong.gateway.service;


import com.yinrong.gateway.model.SignData;

/**
 * <p>安全服务接口</p>
 * @author fjl
 * @version $Id: SecurityService.java, v 0.1 2013-11-19 上午10:24:07 fjl Exp $
 */
public interface ISecurityService {
    /**
     * 验签
     * @param data
     * @return
     */
    public boolean verify(SignData data);
    
    /**
     * 签名
     * @param data
     * @return
     */
    public String sign(SignData data);
}
