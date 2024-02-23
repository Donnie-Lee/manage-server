package com.ssyt.tqserver.framework.exception;

public class ApiException extends RuntimeException {

    private final Integer code;

    private final String message;


    public ApiException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiException(String message, Integer code, String message1) {
        super(message);
        this.code = code;
        this.message = message1;
    }

    public ApiException(String message, Throwable cause, Integer code, String message1) {
        super(message, cause);
        this.code = code;
        this.message = message1;
    }

    public ApiException(Throwable cause, Integer code, String message) {
        super(cause);
        this.code = code;
        this.message = message;
    }

    public ApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Integer code, String message1) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.message = message1;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
