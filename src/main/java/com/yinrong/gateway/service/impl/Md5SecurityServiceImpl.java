/**
 * 
 */
package com.yinrong.gateway.service.impl;

import com.netfinworks.mag.util.sign.MD5;

import com.yinrong.gateway.exception.BizException;
import com.yinrong.gateway.mock.AuthorizationClient;
import com.yinrong.gateway.model.SignData;
import com.yinrong.gateway.service.ISecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * <p>MD5算法</p>
 * @author fjl
 * @version $Id: Md5SecurityServiceImpl.java, v 0.1 2013-11-19 上午10:35:04 fjl Exp $
 */
public class Md5SecurityServiceImpl implements ISecurityService {
    private static Logger       logger = LoggerFactory.getLogger(Md5SecurityServiceImpl.class);

    @Resource
    private AuthorizationClient authorizationClient;


    public boolean verify(SignData data) {
        boolean result = false;
        if (logger.isDebugEnabled()) {
            logger.debug("verify signdata:{} with:MD5", data);
        }
        try {
            result = MD5.verify(data.getSignSrc(), data.getSign(), getKey(data), data.getCharset());
        } catch (Exception e) {
            logger.error("MD5 verify failure:{}", data);
            logger.error("MD5 verify failure", e);
        }
        return result;
    }

    public String sign(SignData data) {
        String result = null;
        if (logger.isDebugEnabled()) {
            logger.debug("sign signdata:{} with:MD5", data);
        }

        try {
            result = MD5.sign(data.getSignSrc(), getKey(data), data.getCharset());
            data.setSign(result);
        } catch (Exception e) {
            logger.error("MD5 sign failure:{}", data);
            logger.error("MD5 sign failure", e);
        }
        return result;
    }

    private String getKey(SignData data) throws BizException {
        return authorizationClient.getMerchantSecretKey(data.getPartnerId(), data.getSignType(), null);
    }
    /*
    private String getWalletKey(SignData data) throws BizException {
        return authorizationClient.getWalletSecretKey(data.getSignType(), null);
    }
    */
}
