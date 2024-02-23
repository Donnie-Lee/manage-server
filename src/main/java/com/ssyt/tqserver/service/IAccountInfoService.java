package com.ssyt.tqserver.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.ssyt.tqserver.entity.AccountInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ssyt.tqserver.entity.ContactInfo;
import com.ssyt.tqserver.pojo.request.account.AccountPasswordLoginRequest;
import com.ssyt.tqserver.pojo.request.account.AccountSmsLoginRequest;
import com.ssyt.tqserver.pojo.response.ResponseResult;
import com.ssyt.tqserver.pojo.response.api.account.AccountInfoExt;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author Lzhen
 * @since 2024-02-19
 */
public interface IAccountInfoService extends IService<AccountInfo> {

    ResponseResult<SaTokenInfo> login(AccountPasswordLoginRequest accountPasswordLoginRequest);

    ResponseResult<SaTokenInfo> loginSms(AccountSmsLoginRequest accountSmsLoginRequest);

    AccountInfoExt accountInfo(Long accountId);

}
