/**
 * 
 */
package com.yinrong.gateway.validator;

import com.meidusa.fastjson.JSON;

import com.netfinworks.validate.Validator;
import com.netfinworks.validate.exception.ValidationException;
import com.yinrong.gateway.constant.GatewayConstant;
import com.yinrong.gateway.enums.BaseField;
import com.yinrong.gateway.enums.SignType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * <p>注释</p>
 * @author fjl
 * @version $Id: BaseReqValidator.java, v 0.1 2013-11-12 上午11:29:15 fjl Exp $
 */
public class BaseReqValidator extends CommonValidator implements Validator{
    private Logger logger = LoggerFactory.getLogger(BaseReqValidator.class);

    public void validate(Object model) throws ValidationException {
        
        try {
            Map<?,?> data = (Map<?,?> ) model;
            //String charset = (String)data.get(BaseField.INPUT_CHARSET.getCode());
            String version = (String )data.get(BaseField.VERSION.getCode());
            assertNotBlank((String )data.get(BaseField.SERVICE.getCode()),BaseField.SERVICE.getCode());
            assertNotBlank(version,BaseField.VERSION.getCode());
            assertNotBlank((String )data.get(BaseField.PARTNER_ID.getCode()),BaseField.PARTNER_ID.getCode());
            //assertNotBlank(charset,BaseField.INPUT_CHARSET.getCode());
            assertNotBlank((String )data.get(BaseField.SIGN.getCode()),BaseField.SIGN.getCode());
            assertNotBlank((String )data.get(BaseField.SIGN_TYPE.getCode()),BaseField.SIGN_TYPE.getCode());

            assertNatureLengthLessEqual((String )data.get(BaseField.SERVICE.getCode()),BaseField.SERVICE.getLength(),BaseField.SERVICE.getCode());
            assertNatureLengthLessEqual((String )data.get(BaseField.PARTNER_ID.getCode()),BaseField.PARTNER_ID.getLength(),BaseField.PARTNER_ID.getCode());
            //assertNatureLengthLessEqual(charset,BaseField.INPUT_CHARSET.getLength(),BaseField.INPUT_CHARSET.getCode());
            assertNatureLengthLessEqual((String )data.get(BaseField.SIGN.getCode()),BaseField.SIGN.getLength(),BaseField.SIGN.getCode());
            assertNatureLengthLessEqual((String )data.get(BaseField.SIGN_TYPE.getCode()),BaseField.SIGN_TYPE.getLength(), BaseField.SIGN_TYPE.getCode());
            assertNatureLengthLessEqual((String )data.get(BaseField.MEMO.getCode()),BaseField.MEMO.getLength(), BaseField.MEMO.getCode());
            
            //Assert.isTrue(Charset.isSupported(charset),charset + "字符编码不支持");
            Assert.isTrue(GatewayConstant.version.equals(version), "版本号不符");
            
            //签名类型是否支持
            Assert.notNull(SignType.getByCode((String )data.get(BaseField.SIGN_TYPE.getCode())), "签名算法不支持");
        } catch (IllegalArgumentException e) {
            logger.error("请求参数格式验证出错:"+JSON.toJSON(model),e);
            throw new ValidationException(e.getMessage());
        }
    }

}
