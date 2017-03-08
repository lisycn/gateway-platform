/**
 * 
 */
package com.yinrong.gateway.mock;


import com.yinrong.gateway.enums.KeyType;
import com.yinrong.gateway.exception.BizException;

/**
 * <p>注释</p>
 * @author fjl
 * @version $Id: MerchantAuthorizationClient.java, v 0.1 2013-11-21 下午7:12:00 fjl Exp $
 */
public interface AuthorizationClient {

    /**
     * 获取商户公钥或md5
     * @param partnerId
     * @param signType
     * @return
     */
    public String getMerchantSecretKey(String partnerId, String signType, KeyType keyType) throws BizException;
    
    
    /**
     * 获取钱私钥
     * @param partnerId
     * @param signType
     * @param keyType
     * @return
     * @throws BizException
     */
    public String getWalletSecretKey(String signType, KeyType keyType) throws BizException;
    
}
