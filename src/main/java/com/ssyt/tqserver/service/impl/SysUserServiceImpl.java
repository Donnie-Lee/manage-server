package com.ssyt.tqserver.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssyt.tqserver.framework.utils.AssertUtils;
import com.ssyt.tqserver.pojo.request.LoginRequest;
import com.ssyt.tqserver.entity.SysUser;
import com.ssyt.tqserver.mapper.SysUserMapper;
import com.ssyt.tqserver.pojo.response.backend.CurrentUser;
import com.ssyt.tqserver.service.ISysRoleService;
import com.ssyt.tqserver.service.ISysUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Set;

import static com.ssyt.tqserver.framework.constant.RsaKeyConstants.PRIVATE_KEY;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author Lzhen
 * @since 2024-01-18
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Resource
    private ISysRoleService sysRoleService;

    @Override
    public Page<SysUser> list(Integer pageNo, Integer pageSize, String name) {
        Page<SysUser> page = Page.of(pageNo, pageSize);
        baseMapper.page(page, name);
        return page;
    }

    @Override
    public void loadByUsername(String username) {
        SysUser user = baseMapper.loadByUsername(username);
        // 获取用户权限
        Set<String> authorityList = sysRoleService.loadAuthorityByUserId(user.getUserId());
    }

    @Override
    public SaTokenInfo doLogin(LoginRequest loginRequest) {
        SysUser user = baseMapper.loadByUsername(loginRequest.username());
        AssertUtils.assertExec(Objects.nonNull(user), "用户名或密码不正确");

        // 密码加密 'admin123456'
        String decryptByPrivate = SaSecureUtil.rsaDecryptByPrivate(PRIVATE_KEY, user.getPassword());
        AssertUtils.assertExec(loginRequest.password().equals(decryptByPrivate), "用户名或密码不正确");

        // 密码校验
        StpUtil.login(user.getUserId());
        return StpUtil.getTokenInfo();
    }

    @Override
    public CurrentUser userinfo() {
        SysUser sysUser = baseMapper.selectById(Long.parseLong(StpUtil.getLoginId().toString()));
        if(Objects.isNull(sysUser)) {
            return null;
        }
        return  CurrentUser.builder()
                .name(sysUser.getRealName())
                .userid(sysUser.getUserId())
                .phone(sysUser.getMobile())
                .title(sysUser.getUsername())
                .avatar(sysUser.getAvatar())
                .access(sysRoleService.loadAuthorityByUserId(sysUser.getUserId()))
                .build();
    }

    @Override
    public void outLogin() {
        StpUtil.logout();
    }
}
