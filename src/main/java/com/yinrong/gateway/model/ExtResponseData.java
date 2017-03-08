/**
 *
 */
package com.yinrong.gateway.model;

import com.meidusa.fastjson.annotation.JSONField;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

/**
 * <p>外部响应父类</p>
 * @author fjl
 * @version $Id: ResponseData.java, v 0.1 2013-11-12 下午1:30:58 fjl Exp $
 */
public class ExtResponseData implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -353514909015323836L;

    @JSONField(name = "is_success")
    private String is_success;                             //成功标识
    @JSONField(name = "partner_id")
    private String partner_id;                             //合作者身份ID
    @JSONField(name = "_input_charset")
    private String _input_charset;                         //参数编码字符集
    @JSONField(name = "sign")
    private String sign;                                   //签名
    @JSONField(name = "sign_type")
    private String sign_type;                              //签名方式
    @JSONField(name = "error_code")
    private String error_code;                             //返回错误码
    @JSONField(name = "error_message")
    private String error_message;                          //返回错误原因
    @JSONField(name = "memo")
    private String memo;                                   //备注

    public ExtResponseData() {
        super();
    }

    public String getIs_success() {
        return is_success;
    }

    public void setIs_success(String is_success) {
        this.is_success = is_success;
    }

    public String getPartner_id() {
        return partner_id;
    }

    public void setPartner_id(String partner_id) {
        this.partner_id = partner_id;
    }

    @JSONField(name = "_input_charset")
    public String get_input_charset() {
        return _input_charset;
    }

    @JSONField(name = "_input_charset")
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

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
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
