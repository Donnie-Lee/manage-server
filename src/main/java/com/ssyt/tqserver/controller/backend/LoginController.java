package com.ssyt.tqserver.controller.backend;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.ssyt.tqserver.pojo.request.LoginRequest;
import com.ssyt.tqserver.pojo.response.backend.CurrentUser;
import com.ssyt.tqserver.pojo.response.ResponseResult;
import com.ssyt.tqserver.service.ISysUserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public record LoginController(ISysUserService userService) {

    @PostMapping("doLogin")
    public ResponseResult<SaTokenInfo> doLogin(@Validated @RequestBody LoginRequest loginRequest) {
        return ResponseResult.success(userService.doLogin(loginRequest));
    }

    @GetMapping("userinfo")
    public ResponseResult<CurrentUser> userinfo() {
        return ResponseResult.success(userService.userinfo());
    }

    @GetMapping("outLogin")
    public ResponseResult<Void> outLogin() {
        userService.outLogin();
        return ResponseResult.success();
    }

}
