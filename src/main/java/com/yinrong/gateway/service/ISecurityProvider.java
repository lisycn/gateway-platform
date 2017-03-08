package com.yinrong.gateway.service;


import com.yinrong.gateway.model.SignData;

/**
 * 
 * <p>安全服务提供者</p>
 * @author fjl
 * @version $Id: ISecurityProvider.java, v 0.1 2013-11-12 下午3:01:10 fjl Exp $
 */
public interface ISecurityProvider {

	/**
	 * 验证签名
	 * @param data
	 * @return
	 */
	public boolean verifySignature(SignData data);
	
	/**
	 * 签名
	 * @param data
	 * @return
	 */
	public String generateSignature(SignData data);
	
}
