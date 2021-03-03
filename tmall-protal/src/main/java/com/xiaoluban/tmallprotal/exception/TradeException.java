package com.xiaoluban.tmallprotal.exception;

/**
 * @author txb
 * @date 2021/3/3 9:40
 */
public class TradeException extends RuntimeException{

    public TradeException() {
        super();
    }

    public TradeException(String message) {
        super("交易异常："+message);
    }

    public TradeException(String message, Throwable cause) {
        super("交易异常："+message, cause);
    }

    public TradeException(Throwable cause) {
        super(cause);
    }

    protected TradeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super("交易异常："+message, cause, enableSuppression, writableStackTrace);
    }
}
