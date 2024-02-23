package com.ssyt.tqserver.controller.api;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.RandomUtil;
import com.ssyt.tqserver.entity.AccountInfo;
import com.ssyt.tqserver.entity.ContactInfo;
import com.ssyt.tqserver.framework.utils.AssertUtils;
import com.ssyt.tqserver.pojo.request.account.AccountPasswordLoginRequest;
import com.ssyt.tqserver.pojo.request.account.AccountSmsLoginRequest;
import com.ssyt.tqserver.pojo.response.ResponseResult;
import com.ssyt.tqserver.pojo.response.api.account.AccountInfoExt;
import com.ssyt.tqserver.pojo.response.api.contact.ContactInfoResponse;
import com.ssyt.tqserver.service.IAccountInfoService;
import com.ssyt.tqserver.service.IContactInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("account")
public record AccountController(StringRedisTemplate stringRedisTemplate,
                                IAccountInfoService accountInfoService,
                                IContactInfoService contactInfoService
) {

    private static final String CHECK_CODE_INTERVAL_KEY = "LOGIN_CHECK_CODE_INTERVAL:";
    private static final String CHECK_CODE_KEY = "LOGIN_CHECK_CODE:";
    private static final Integer CHECK_CODE_INTERVAL = 1;
    private static final Integer CHECK_CODE_EXPIRE = 5;

    @PostMapping("login")
    public ResponseResult<SaTokenInfo> login(@Validated @RequestBody AccountPasswordLoginRequest accountPasswordLoginRequest) {
        return accountInfoService.login(accountPasswordLoginRequest);
    }


    @PostMapping("loginSms")
    public ResponseResult<SaTokenInfo> loginSms(@Validated @RequestBody AccountSmsLoginRequest accountSmsLoginRequest) {
        String checkCode = stringRedisTemplate.boundValueOps(CHECK_CODE_KEY + accountSmsLoginRequest.mobile()).get();
        AssertUtils.assertExec(StringUtils.isNoneBlank(checkCode), "验证码已过期,请重新获取");
        AssertUtils.assertExec(StringUtils.equals(checkCode, accountSmsLoginRequest.checkCode()), "验证码错误");
        stringRedisTemplate.delete(Arrays.asList(CHECK_CODE_INTERVAL_KEY + accountSmsLoginRequest.mobile(), CHECK_CODE_KEY + accountSmsLoginRequest.mobile()));
        return accountInfoService.loginSms(accountSmsLoginRequest);
    }

    @GetMapping("getCheckCode")
    public ResponseResult<Void> getCheckCode(@RequestParam("mobile") String mobile) {
        // 从缓存中获取验证码的获取间隔
        BoundValueOperations<String, String> intervalOptions = stringRedisTemplate.boundValueOps(CHECK_CODE_INTERVAL_KEY + mobile);
        BoundValueOperations<String, String> loginCheckCodeOptions = stringRedisTemplate.boundValueOps(CHECK_CODE_KEY + mobile);

        // 是否通过间隔
        AssertUtils.assertExec(StringUtils.isBlank(intervalOptions.get()), "获取验证码太频繁，请稍后再试");
        int checkCode = RandomUtil.randomInt(1000, 9999);
        log.info("获取验证码成功，手机号码:{}, 验证码: {}", mobile, checkCode);

        intervalOptions.set(mobile, CHECK_CODE_INTERVAL, TimeUnit.MINUTES);
        loginCheckCodeOptions.set(String.valueOf(checkCode), CHECK_CODE_EXPIRE, TimeUnit.MINUTES);
        return ResponseResult.success();
    }

    @GetMapping("currentUser")
    public ResponseResult<AccountInfoExt> currentUser() {
        return ResponseResult.success(accountInfoService.accountInfo(Long.parseLong(StpUtil.getLoginId().toString())));
    }

    @GetMapping("accountInfo/{accountId}")
    public ResponseResult<AccountInfoExt> accountInfo(@PathVariable("accountId") Long accountId) {
        return ResponseResult.success(accountInfoService.accountInfo(accountId));
    }

    @GetMapping("contacts")
    public ResponseResult<List<ContactInfoResponse>> contacts() {
        return ResponseResult.success(contactInfoService.contacts());
    }

}
