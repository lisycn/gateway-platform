/**
 * 
 */
package com.yinrong.gateway.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * <p>创建个人会员响应数据</p>
 * @author fjl
 * @version $Id: CreatePersonalResponse.java, v 0.1 2013-11-12 下午7:04:31 fjl Exp $
 */
public class CreatePersonalResponse extends ExtResponseData {
    /**
     * 
     */
    private static final long serialVersionUID = -3860196478118995050L;
    /**
     * 会员号
     */
    private String member_id;

    public CreatePersonalResponse() {
        super();
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
