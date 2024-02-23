package com.ssyt.tqserver.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ssyt.tqserver.pojo.request.LoginRequest;
import com.ssyt.tqserver.entity.SysUser;
import com.ssyt.tqserver.pojo.response.backend.CurrentUser;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author Lzhen
 * @since 2024-01-18
 */
public interface ISysUserService extends IService<SysUser> {

    Page<SysUser> list(Integer pageNo, Integer pageSize, String name);

    void loadByUsername(String username);

    SaTokenInfo doLogin(LoginRequest loginRequest);

    CurrentUser userinfo();

    void outLogin();
}
