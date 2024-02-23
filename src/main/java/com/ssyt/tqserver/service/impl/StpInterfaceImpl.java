package com.ssyt.tqserver.service.impl;

import cn.dev33.satoken.stp.StpInterface;
import com.ssyt.tqserver.service.ISysRoleService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class StpInterfaceImpl implements StpInterface {

    @Resource
    private ISysRoleService roleService;


    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        Set<String> authorityList = roleService.loadAuthorityByUserId(Long.parseLong(loginId.toString()));
        return authorityList.stream().filter(item -> !StringUtils.startsWith(item, "ROLE_"))
                .toList();
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        Set<String> authorityList = roleService.loadAuthorityByUserId(Long.parseLong(loginId.toString()));
        return authorityList.stream().filter(item -> StringUtils.startsWith(item, "ROLE_"))
                .toList();
    }
}
