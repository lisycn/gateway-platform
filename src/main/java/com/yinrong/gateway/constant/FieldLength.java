package com.yinrong.gateway.constant;

/**
 * <p>接口字段长度限定</p>
 * @author netfinworks
 * @version $Id: FieldLength.java, v 0.1 2010-11-29 下午04:53:54 netfinworks Exp $
 */
public class FieldLength {

    /** 字段长度限定:会员编号 */
    public static final int MEMBER_ID           = 12;

    /** 字段长度限定:手机号 */
    public static final int MOBILE              = 20; // 口袋有海外手机，长度格式不限制

    /** 字段长度限定：uid*/
    public static final int UID                 = 32;

    /** 字段长度限定：identity*/
    public static final int IDENTITY            = 50;

    /** 字段长度限定：identity pid */
    public static final int IDENTITY_PID        = 10;

    /** 字段长度限定：登录名 */
    public static final int LOGIN_NAME          = 50;

    /** 字段长度限定:会员名称 */
    public static final int MEMBER_NAME         = 50;

    /** 字段长度限定:商户名称 */
    public static final int MERCHANT_NAME         = 128;

    /** 字段长度限定：真实姓名 */
    public static final int REAL_NAME           = 50;

    /** 字段长度限定：身份证 */
    public static final int ID_CARD_NO          = 18;

    /** 字段长度限定：邮箱 */
    public static final int E_MAIL              = 30;

    /*-----------------银行字段-----------------------*/
    /** 字段长度限定：银行名称 */
    public static final int BANK_CODE           = 10;
    /** 字段长度限定：银行名称 */
    public static final int BANK_NAME           = 50;

    /** 字段长度限定：支行名称 */
    public static final int BANK_BRANCH         = 255;

    /** 字段长度限定：支行所在省份 */
    public static final int BANK_PROVINCE       = 128;

    /** 字段长度限定：支行所在城市*/
    public static final int BANK_CITY           = 128;

    /** 字段长度限定：户名*/
    public static final int BANK_ACCOUNT_NAME   = 90;

    /** 字段长度限定：银行账号*/
    public static final int BANK_ACCOUNT_NO     = 50;

    /*-------------------企业信息----------------------*/
    /** 字段长度限定:企业名称 */
    public static final int ENTERPRISE_NAME     = 200;

    /** 字段长度限定:企业法人 */
    public static final int LEGAL_PERSON        = 50;

    /** 字段长度限定:法人手机号 */
    public static final int LEGAL_PERSON_PHONE  = 11;

    /** 字段长度限定:网址 */
    public static final int WEBSITE             = 128;

    /** 字段长度限定：地址 */
    public static final int ADDRESS             = 200;

    /** 字段长度限定:执照号 */
    public static final int LICENSE_NO          = 128;

    /** 字段长度限定:执照所在地 */
    public static final int LICENSE_ADDRESS     = 200;

    /** 字段长度限定:执照过期日期 */
    public static final int LICENSE_EXPIRE_DATE = 8;

    /** 字段长度限定:营业范围*/
    public static final int BUSINESS_SCOPE      = 1024;

    /** 字段长度限定:联系电话*/
    public static final int TELEPHONE           = 20;

    /** 字段长度限定:组织机构代码*/
    public static final int ORGANIZATION_NO     = 128;
    /** 字段长度限定:企业简介*/
    public static final int SUMMARY             = 1024;
    /** 字段长度限定:标志位*/
    public static final int FLAG             = 1;

}
