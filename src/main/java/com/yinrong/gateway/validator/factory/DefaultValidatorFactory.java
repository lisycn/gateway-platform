/**
 * 
 */
package com.yinrong.gateway.validator.factory;

import com.netfinworks.common.lang.StringUtil;
import com.netfinworks.validate.Validator;
import com.netfinworks.validate.exception.ValidationException;

import java.util.Map;

/**
 * <p>校验器工厂默认实现</p>
 * @author fangjilue
 * @version $Id: DefaultValidatorFactory.java 47623 2013-06-03 08:54:07Z fangjilue $
 */
public class DefaultValidatorFactory implements ValidatorFactory {
    /** 账单校验器Map */
    private Map<String, Validator> memberValidatorMap;

    public Validator load(String validatorName) throws ValidationException {
        if (StringUtil.isBlank(validatorName)) {
            throw new ValidationException("校验器名称不能为空");
        }

        return memberValidatorMap.get(validatorName);
    }

    public void setMemberValidatorMap(Map<String, Validator> memberValidatorMap) {
        this.memberValidatorMap = memberValidatorMap;
    }
}
