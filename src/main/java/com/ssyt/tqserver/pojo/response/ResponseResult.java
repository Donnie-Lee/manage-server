package com.ssyt.tqserver.pojo.response;

import cn.hutool.http.HttpStatus;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Lzhen
 */

@Setter
@Getter
public class ResponseResult<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 消息提示
     */
    private String message;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 响应内容
     */
    private T data;

    /**
     * 时间戳
     */
    private long timestamp = System.currentTimeMillis();

    public ResponseResult(Integer code) {
        this.code = code;
    }

    public ResponseResult(Integer code, String message, T data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public ResponseResult(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    public ResponseResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public static <T> ResponseResult<T> success() {
        return new ResponseResult<T>(HttpStatus.HTTP_OK, "SUCCESS");
    }

    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<T>(HttpStatus.HTTP_OK, "SUCCESS", data);
    }

    public static <T> ResponseResult<T> success(String msg, T data) {
        return new ResponseResult<T>(HttpStatus.HTTP_OK, msg, data);
    }

    public static <T> ResponseResult<T> error(Integer code, String msg) {
        return new ResponseResult<T>(code, msg);
    }

    public static <T> ResponseResult<T> error(String msg) {
        return ResponseResult.error(HttpStatus.HTTP_INTERNAL_ERROR, msg);
    }

    public static <T> ResponseResult<T> error() {
        return ResponseResult.error(HttpStatus.HTTP_INTERNAL_ERROR, "FAIL");
    }

}

