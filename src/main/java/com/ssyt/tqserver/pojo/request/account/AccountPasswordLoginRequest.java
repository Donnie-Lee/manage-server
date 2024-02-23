package com.ssyt.tqserver.pojo.request.account;

import jakarta.validation.constraints.NotBlank;

public record AccountPasswordLoginRequest(@NotBlank(message = "用户名不能为空") String username,
                                          @NotBlank(message = "密码不能为空") String password) {
}
