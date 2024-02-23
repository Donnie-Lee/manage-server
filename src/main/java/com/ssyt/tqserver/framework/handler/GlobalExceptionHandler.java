package com.ssyt.tqserver.framework.handler;

import com.ssyt.tqserver.pojo.response.ResponseResult;
import com.ssyt.tqserver.framework.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseResult<Void> defaultException(Exception e) {
        log.error("服务器内部异常", e);
        return ResponseResult.error(e.getMessage());
    }

    @ExceptionHandler(value = ApiException.class)
    public ResponseResult<Void> apiException(ApiException e) {
        log.error("业务数据异常", e);
        return ResponseResult.error(e.getCode(), e.getMessage());
    }
}
