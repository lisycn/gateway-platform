/**
 *
 */
package com.yinrong.gateway.validator.impl;

import com.netfinworks.common.lang.StringUtil;

import com.netfinworks.validate.Validator;
import com.netfinworks.validate.exception.ValidationException;
import com.yinrong.gateway.constant.FieldLength;
import com.yinrong.gateway.model.CreatePersonalRequest;
import com.yinrong.gateway.utils.ValidateUtils;
import com.yinrong.gateway.validator.CommonValidator;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

/**
 * <p>开个人户参数验证器</p>
 * @author fjl
 * @version $Id: CreatePersonalRequestValidator.java, v 0.1 2013-11-13 下午1:08:41 fjl Exp $
 */
public class CreatePersonalRequestValidator extends CommonValidator implements Validator {


    public void validate(Object model) throws ValidationException {
        try {
            CreatePersonalRequest req = (CreatePersonalRequest) model;
            
            if(StringUtil.isBlank(req.getMobile())&&StringUtil.isBlank(req.getEmail())){
            	throw new IllegalArgumentException("邮箱与手机号必须输入一个");
            }
//            assertNotBlank(req.getMobile(), "手机号");
            assertNotBlank(req.getUid(), "外部系统用户ID");

            assertNatureLengthLessEqual(req.getMobile(), FieldLength.MOBILE, "手机号");
            assertNatureLengthLessEqual(req.getUid(), FieldLength.UID, "外部系统用户ID");
            assertLengthLessEqual(req.getReal_name(), FieldLength.REAL_NAME, "真实姓名");
            assertLengthLessEqual(req.getIs_active(), FieldLength.FLAG, "是否激活会员");
            assertNatureLengthLessEqual(req.getId_card_no(), FieldLength.ID_CARD_NO, "身份证号");
            assertLengthLessEqual(req.getMember_name(), FieldLength.MEMBER_NAME, "会员名称");
            assertLengthLessEqual(req.getEmail(), FieldLength.E_MAIL, "会员邮箱");
            assertLengthEqualOrEmpty(req.getLogin_pwd(), 64, "登陆密码");

            if(StringUtil.isNotBlank(req.getMobile())){
            	Assert.isTrue(ValidateUtils.isMobile(req.getMobile()), "手机号格式非法");
            }else if(StringUtil.isNotBlank(req.getEmail())){
            	Assert.isTrue(ValidateUtils.isMail(req.getEmail()), "邮箱格式非法");
            }

            if (StringUtils.isNotEmpty(req.getId_card_no())) {
                Assert.isTrue(ValidateUtils.isIdCard(req.getId_card_no()), "身份证号格式非法");
            }
        } catch (Exception e) {
            throw new ValidationException(e.getMessage());
        }
    }

}
