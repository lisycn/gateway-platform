/**
 * 
 */
package com.yinrong.gateway.enums;

/**
 * <p>注释</p>
 * @author fjl
 * @version $Id: KeyType.java, v 0.1 2013-11-21 下午7:21:44 fjl Exp $
 */
public enum KeyType {


    PUBLIC("PUBLIC"), PRIVATE("PRIVATE");

    private String code;

    private KeyType(String code) {
        this.code = code;

    }

    public static KeyType getByCode(String code) {
        for (KeyType ls : KeyType.values()) {
            if (ls.code.equalsIgnoreCase(code)) {
                return ls;
            }
        }
        return null;
    }

    public boolean equalsCode(String code) {
        return getCode().equalsIgnoreCase(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
