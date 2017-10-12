package com.dw.web.config.shiro;

import org.apache.shiro.authc.AccountException;

/**
 * 自定义shiro登录相关异常
 * Created by zhangliewei on 2017/6/21.
 */
public class DeletedAccountException extends AccountException {
    /**
     * Creates a new DeletedAccountException.
     */
    public DeletedAccountException() {
        super();
    }

    /**
     * Constructs a new DeletedAccountException.
     *
     * @param message the reason for the exception
     */
    public DeletedAccountException(String message) {
        super(message);
    }

    /**
     * Constructs a new DeletedAccountException.
     *
     * @param cause the underlying Throwable that caused this exception to be thrown.
     */
    public DeletedAccountException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new DeletedAccountException.
     *
     * @param message the reason for the exception
     * @param cause   the underlying Throwable that caused this exception to be thrown.
     */
    public DeletedAccountException(String message, Throwable cause) {
        super(message, cause);
    }
}
