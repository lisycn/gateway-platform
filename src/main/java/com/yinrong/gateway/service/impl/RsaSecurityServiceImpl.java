/**
 *
 */
package com.yinrong.gateway.service.impl;

import com.netfinworks.mag.util.sign.RSA;

import com.yinrong.gateway.enums.ErrorCode;
import com.yinrong.gateway.enums.KeyType;
import com.yinrong.gateway.enums.SignType;
import com.yinrong.gateway.exception.BizException;
import com.yinrong.gateway.mock.AuthorizationClient;
import com.yinrong.gateway.model.SignData;
import com.yinrong.gateway.service.ISecurityService;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * <p>RSA算法</p>
 * @author fjl
 * @version $Id: RsaSecurityService.java, v 0.1 2013-11-19 上午10:36:01 fjl Exp $
 */
public class RsaSecurityServiceImpl implements ISecurityService {
    private static Logger       logger = LoggerFactory.getLogger(RsaSecurityServiceImpl.class);
    @Resource
    private AuthorizationClient authorizationClient;


    /*
     * 使用商户公钥验签
     */
    public boolean verify(SignData data) {
        boolean result = false;
        if (logger.isDebugEnabled()) {
            logger.debug("verify signdata:{} with:RSA", data);
        }
        try {
            String signature = data.getSign();
            if (signature != null) {
                signature = signature.replace(' ', '+');
            }
            result = RSA.verify(data.getSignSrc(), signature, getKey(data, KeyType.PUBLIC),
                data.getCharset());
        } catch (Exception e) {
            logger.error("RSA verify failure:{}", data);
            logger.error("RSA verify failure", e);
        }
        return result;
    }

    /*
     * 使用钱包私钥签名
     */
    public String sign(SignData data) {
        String result = null;
        if (logger.isDebugEnabled()) {
            logger.debug("sign signdata:{} with:RSA", data);
        }

        try {
            //使用钱包id 去查密钥
            result = RSA.sign(data.getSignSrc(), getWalletKey(data, KeyType.PRIVATE), data.getCharset());
            data.setSign(result);
        } catch (Exception e) {
            logger.error("RSA sign failure:{}", data);
            logger.error("RSA sign failure", e);
        }
        return result;
    }

    /**
     * 只有RSA 支持加解密,使用钱包私钥解密
     * @param src
     * @param charset
     * @return
     * @throws BizException
     */
    public String decrypt(String src, String charset) throws BizException {
        if (StringUtils.isEmpty(src)) {
            return src;
        }
        try {
            SignData data = new SignData();
            //使用钱包id 去查密钥
            data.setSignType(SignType.RSA.getCode());
            byte[] bytes = RSA.decryptByPrivateKey(Base64.decodeBase64(src.getBytes(charset)),
                getWalletKey(data, KeyType.PRIVATE));
            return new String(bytes, charset);
        } catch (Exception e) {
            logger.error("RSA decrypt failure:src = {}, charset = {}", src, charset);
            logger.error("RSA decrypt failure", e);
            throw new BizException(ErrorCode.ILLEGAL_DECRYPT);
        }
    }

    private String getKey(SignData data, KeyType keyType) throws BizException {
        return authorizationClient.getMerchantSecretKey(data.getPartnerId(), data.getSignType(), keyType);
    }

    private String getWalletKey(SignData data, KeyType keyType) throws BizException {
        return authorizationClient.getWalletSecretKey(data.getSignType(), keyType);
    }
}
