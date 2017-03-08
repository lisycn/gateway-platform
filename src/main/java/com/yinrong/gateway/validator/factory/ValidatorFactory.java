/**
 * 
 */
package com.yinrong.gateway.validator.factory;

import com.netfinworks.validate.Validator;
import com.netfinworks.validate.exception.ValidationException;

/**
 * <p>校验器工厂</p>
 * @author fangjilue
 * @version $Id: ValidatorFactory.java 47623 2013-06-03 08:54:07Z fangjilue $
 */
public interface ValidatorFactory {
    
    /**
     * 
     * @param validatorName
     * @return
     * @throws ValidationException
     */
    public Validator load(String validatorName) throws ValidationException;
}
