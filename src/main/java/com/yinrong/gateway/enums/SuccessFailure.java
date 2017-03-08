/**
 * 
 */
package com.yinrong.gateway.enums;

import org.apache.commons.lang.StringUtils;

/**
 * <p>注释</p>
 * @author fjl
 * @version $Id: SuccessFailure.java, v 0.1 2013-11-12 下午4:54:07 fjl Exp $
 */
public enum SuccessFailure {
    SUCCESS("T","成功"),
    FAILURE("F","失败");
    
    private String code;
    private String message;
    
    private SuccessFailure(String code, String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
    
    public static SuccessFailure getByCode(String code){
        if(StringUtils.isBlank(code)){
            return null;
        }
        for(SuccessFailure item : values()){
            if(item.getCode().equals(code)){
                return item;
            }
        }
        return null;
    }

}
