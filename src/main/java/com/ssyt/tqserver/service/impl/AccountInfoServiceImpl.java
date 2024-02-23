package com.ssyt.tqserver.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.ssyt.tqserver.entity.AccountInfo;
import com.ssyt.tqserver.entity.ContactInfo;
import com.ssyt.tqserver.framework.utils.AssertUtils;
import com.ssyt.tqserver.framework.utils.BeanTransferUtils;
import com.ssyt.tqserver.mapper.AccountInfoMapper;
import com.ssyt.tqserver.pojo.request.account.AccountPasswordLoginRequest;
import com.ssyt.tqserver.pojo.request.account.AccountSmsLoginRequest;
import com.ssyt.tqserver.pojo.response.ResponseResult;
import com.ssyt.tqserver.pojo.response.api.account.AccountInfoExt;
import com.ssyt.tqserver.service.IAccountInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author Lzhen
 * @since 2024-02-19
 */
@Service
public class AccountInfoServiceImpl extends ServiceImpl<AccountInfoMapper, AccountInfo> implements IAccountInfoService {
    @Override
    public ResponseResult<SaTokenInfo> login(AccountPasswordLoginRequest accountPasswordLoginRequest) {
        AccountInfo accountInfo = baseMapper.selectByMobile(accountPasswordLoginRequest.username().trim());
        AssertUtils.assertExec(Objects.nonNull(accountInfo), "用户名或密码错误");
        AssertUtils.assertExec(StringUtils.equals(accountPasswordLoginRequest.password().trim(), accountInfo.getPassword()), "用户名或密码错误");
        StpUtil.login(accountInfo.getId());
        return ResponseResult.success(StpUtil.getTokenInfo());
    }

    @Override
    public ResponseResult<SaTokenInfo> loginSms(AccountSmsLoginRequest accountSmsLoginRequest) {
        AccountInfo accountInfo = baseMapper.selectByMobile(accountSmsLoginRequest.mobile().trim());
        if(Objects.isNull(accountInfo)){
            accountInfo = new AccountInfo();
            accountInfo.setNickname(accountSmsLoginRequest.mobile());
            accountInfo.setMobile(accountSmsLoginRequest.mobile());
            baseMapper.insert(accountInfo);
        }
        StpUtil.login(accountInfo.getId());
        return ResponseResult.success(StpUtil.getTokenInfo());
    }

    @Override
    public AccountInfoExt accountInfo(Long accountId) {
        AccountInfo accountInfo = baseMapper.selectById(accountId);
        AccountInfoExt accountInfoExt = BeanTransferUtils.transfer(accountInfo, AccountInfoExt.class);
        // TODO 填充补充信息
        return accountInfoExt;
    }
}
