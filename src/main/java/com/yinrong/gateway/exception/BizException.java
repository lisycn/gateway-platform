/**
 * 
 */
package com.yinrong.gateway.exception;


import com.yinrong.gateway.enums.ErrorCode;

/**
 * <p>网关异常类</p>
 * @author fjl
 * @version $Id: BizException.java, v 0.1 2013-11-13 上午10:03:57 fjl Exp $
 */
public class BizException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 4852957385720871802L;
    /**
     * 应答码
     */
    private ErrorCode code;

    /**
     * 构造方法
     * @param code
     */
    public BizException(ErrorCode code) {
        super(code.getMessage());
        this.code = code;
    }

    /**
     * 构造方法
     * @param code
     * @param message
     */
    public BizException(ErrorCode code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * 构造方法
     * @param code
     * @param message
     * @param cause
     */
    public BizException(ErrorCode code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ErrorCode getCode() {
        return code;
    }

    public String getCodeStr() {
        return code.getCode();
    }

    public void setCode(ErrorCode code) {
        this.code = code;
    }

}
