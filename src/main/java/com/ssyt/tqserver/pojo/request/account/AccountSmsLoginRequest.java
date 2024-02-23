package com.ssyt.tqserver.pojo.request.account;

import jakarta.validation.constraints.NotBlank;

public record AccountSmsLoginRequest(@NotBlank(message = "手机号不能为空") String mobile,
                                     @NotBlank(message = "验证码不能为空") String checkCode) {
}
