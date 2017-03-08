/**
 * 
 */
package com.yinrong.gateway.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

/**
 * <p>外部请求父类</p>
 * @author fjl
 * @version $Id: RequestData.java, v 0.1 2013-11-12 上午10:32:41 fjl Exp $
 */
public class ExtRequestData implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3933213434299606886L;
    private String service;                                //接口名称
    private String version;                                //版本号
    private String partner_id;                             //合作者身份ID
    private String _input_charset;                         //参数编码字符集
    private String sign;                                   //签名
    private String sign_type;                              //签名方式
    private String memo;                                   //备注

    public ExtRequestData() {
        super();
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPartner_id() {
        return partner_id;
    }

    public void setPartner_id(String partner_id) {
        this.partner_id = partner_id;
    }

    public String get_input_charset() {
        return _input_charset;
    }

    public void set_input_charset(String _input_charset) {
        this._input_charset = _input_charset;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
