/**
 *
 */
package com.yinrong.gateway.enums;

import org.apache.commons.lang.StringUtils;


/**
 * <p>注释</p>
 * @author fjl
 * @version $Id: ErrorCode.java, v 0.1 2013-11-12 下午4:11:40 fjl Exp $
 */
public enum ErrorCode {
    SYSTEM_ERROR("SYSTEM_ERROR","系统内部错误"),

    SESSION_TIMEOUT("SESSION_TIMEOUT","session超时"),

    ILLEGAL_ACCESS_SWITCH_SYSTEM("ILLEGAL_ACCESS_SWITCH_SYSTEM","商户不允许访问该类型的接口"),

    PARTNER_ID_NOT_EXIST("PARTNER_ID_NOT_EXIST","合作方Id不存在"),

    TRADE_DATA_MATCH_ERROR("TRADE_DATA_MATCH_ERROR","交易信息有误"),

    ILLEGAL_REQUEST("ILLEGAL_REQUEST","无效请求"),

    EXTERFACE_INVOKE_CONTEXT_EXPIRED("EXTERFACE_INVOKE_CONTEXT_EXPIRED","接口调用上下文过期"),

    ILLEGAL_SIGN_TYPE("ILLEGAL_SIGN_TYPE","签名类型不正确"),

    ILLEGAL_SIGN("ILLEGAL_SIGN","验签未通过"),
    CARD_INFO_MATCH_ERROR("CARD_INFO_MATCH_ERROR","银行卡信息有误"),

    ILLEGAL_DECRYPT("ILLEGAL_DECRYPT","解密失败"),

    ILLEGAL_ARGUMENT("ILLEGAL_ARGUMENT","参数错误"),

    ILLEGAL_SERVICE("ILLEGAL_SERVICE","服务接口不存在"),

    IDENTITY_EXIST_ERROR("IDENTITY_EXIST_ERROR","会员标识已经存在"),

    MEMBER_NOT_EXIST("MEMBER_NOT_EXIST","会员不存在"),

    ACCOUNT_NOT_EXIST("ACCOUNT_NOT_EXIST","账户不存在"),

    MOBILE_EXIST_ERROR("MOBILE_EXIST_ERROR","修改手机号失败"),
    EMAIL_EXIST_ERROR("EMAIL_EXIST_ERROR","修改邮箱失败"),

    MEMBER_TYPE_ERROR("MEMBER_TYPE_ERROR","会员类型错误"),

    BANK_CARD_NOT_EXIST("BANK_CARD_NOT_EXIST","银行卡不存在"),

    ILLEGAL_BANK_CARD_NO("ILLEGAL_BANK_CARD_NO","银行卡号不正确"),

    QCARD_CAN_NOT_UNBUNDLING("QCARD_CAN_NOT_UNBUNDLING","快捷卡不能解绑"),

    NORMAL_CARD_IS_EXIST("NORMAL_CARD_IS_EXIST","提现卡已存在"),

    NORMAL_CARD_IS_NOT_EXIST("NORMAL_CARD_IS_NOT_EXIST","提现卡不存在"),

    DUPLICATE_VERIFY("DUPLICATE_VERIFY","认证信息重复"),
    
    ACCOUNT_TYPE_ERROR("ACCOUNT_TYPE_ERROR","账户类型错误"),
    ACCOUNT_INFO_ERROR("ACCOUNT_INFO_ERROR",""),
    MEMBER_AUTH_FAIL("MEMBER_AUTH_FAIL","会员授权失败"),
    
    MEMBER_BINDING_ERROR("MEMBER_BINDING_ERROR","会员绑定失败"),
    MEMBER_UNBINDING_ERROR("MEMBER_UNBINDING_ERROR","会员解绑失败"),
    MEMBER_BINDING_INNERDATA_ERROR("MEMBER_BINDING_INNERDATA_ERROR","会员系统内部法人或法人电话为空"),
    MEMBER_BINDING_PHONEORNAME_ERROR("MEMBER_BINDING_PHONEORNAME_ERROR","法人姓名或者手机号不匹配")
    ;


    private String code;
    private String message;

    private ErrorCode(String code, String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static ErrorCode getByCode(String code){
        if(StringUtils.isBlank(code)){
            return null;
        }
        for(ErrorCode item : values()){
            if(item.getCode().equals(code)){
                return item;
            }
        }
        return null;
    }


}
