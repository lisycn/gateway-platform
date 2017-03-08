/**
 * 
 */
package com.yinrong.gateway.service.impl;


import com.yinrong.gateway.model.SignData;
import com.yinrong.gateway.service.ISecurityProvider;
import com.yinrong.gateway.service.ISecurityService;

import java.util.Map;

/**
 * @author fjl
 * @version $Id: SecurityProviderImpl.java, v 0.1 2013-11-13 下午1:39:50 fjl Exp $
 */
public class SecurityProviderImpl implements ISecurityProvider {
    private Map<String,ISecurityService> securityMap ;
    
    public boolean verifySignature(SignData data) {
        return getSecrityService(data.getSignType()).verify(data);
    }

    public String generateSignature(SignData data) {
        return getSecrityService(data.getSignType()).sign(data);
    }
    
    
    private ISecurityService getSecrityService(String signType){
        return securityMap.get(signType.toUpperCase());
    }

    public void setSecurityMap(Map<String, ISecurityService> securityMap) {
        this.securityMap = securityMap;
    }
}
