package com.ssyt.tqserver.pojo.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(@NotBlank String username, @NotBlank String password, String checkCode) {

}
