/**
 *
 */
package com.yinrong.gateway.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * <p>创建个人会员请求数据</p>
 * @author fjl
 * @version $Id: CreatePersonalRequestData.java, v 0.1 2013-11-12 下午5:37:24 fjl Exp $
 */
public class CreatePersonalRequest extends ExtRequestData {
    /**
     *
     */
    private static final long serialVersionUID = -601602390526500233L;
    private String mobile;                                 //手机号
    private String uid;                                    //外部系统用户ID
    private String real_name;                              //真实姓名
    private String id_card_no;                             //身份证号
    private String member_name;                            //会员名称
    private String is_active;                              //是否激活会员，默认为T
    private String email;                                  //会员邮箱
    private String login_pwd;							  //登录密码
    

    public CreatePersonalRequest() {
        super();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getId_card_no() {
        return id_card_no;
    }

    public void setId_card_no(String id_card_no) {
        this.id_card_no = id_card_no;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin_pwd() {
		return login_pwd;
	}

	public void setLogin_pwd(String login_pwd) {
		this.login_pwd = login_pwd;
	}

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
