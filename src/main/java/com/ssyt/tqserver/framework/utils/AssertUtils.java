package com.ssyt.tqserver.framework.utils;

import com.ssyt.tqserver.framework.exception.ApiException;

public class AssertUtils {

    public static void assertExec(boolean condition, String message) {
        if(!condition){
            throw new ApiException(500, message);
        }
    }

    public static void assertExec(boolean condition, RuntimeException exception) {
        if(!condition){
            throw exception;
        }
    }

}
