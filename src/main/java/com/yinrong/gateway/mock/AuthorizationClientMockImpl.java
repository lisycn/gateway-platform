/**
 * 
 */
package com.yinrong.gateway.mock;


import com.yinrong.gateway.enums.KeyType;
import com.yinrong.gateway.enums.SignType;
import com.yinrong.gateway.exception.BizException;

import javax.annotation.Resource;

/**
 * <p>注释</p>
 * @author fjl
 * @version $Id: MerchantAuthorizationClientMockImpl.java, v 0.1 2013-11-21 下午7:16:58 fjl Exp $
 */
public class AuthorizationClientMockImpl implements AuthorizationClient {

    @Resource(name="md5Key")
    private String md5Key;
    
    // 商户公钥
    @Resource(name="merchantPublicKey")
    private String merchantPublicKey;
    
    //钱包私钥
    @Resource(name="walletPrivateKey")
    private String walletPrivateKey;

    public String getMerchantSecretKey(String partnerId, String signType, KeyType keyType) {
        if(SignType.MD5.equalsCode(signType)){
            return md5Key;
        }
        if(SignType.RSA.equalsCode(signType)){
            return merchantPublicKey;
        }
        
        return null;
    }

    public String getWalletSecretKey(String signType, KeyType keyType) throws BizException {
        if(SignType.MD5.equalsCode(signType)){
            return md5Key;
        }
        if(SignType.RSA.equalsCode(signType)){
            return walletPrivateKey;
        }
        return null;
    }
    
}
