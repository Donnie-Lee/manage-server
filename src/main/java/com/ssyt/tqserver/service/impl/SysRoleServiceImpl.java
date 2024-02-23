package com.ssyt.tqserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssyt.tqserver.entity.RelationUserRole;
import com.ssyt.tqserver.entity.SysPermission;
import com.ssyt.tqserver.entity.SysRole;
import com.ssyt.tqserver.mapper.RelationUserRoleMapper;
import com.ssyt.tqserver.mapper.SysRoleMapper;
import com.ssyt.tqserver.service.ISysPermissionService;
import com.ssyt.tqserver.service.ISysRoleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author Lzhen
 * @since 2024-01-18
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Resource
    private ISysPermissionService sysPermissionService;

    @Resource
    private RelationUserRoleMapper userRoleMapper;

    @Override
    public Set<String> loadAuthorityByUserId(Long userId) {
        Set<String> authorityList = new HashSet<>();
        // 获取用户角色关联关系
        Set<RelationUserRole> relationUserRoles = userRoleMapper.selectByUserId(userId);
        if(CollectionUtils.isEmpty(relationUserRoles)){
            return authorityList;
        }

        // 获取角色信息
        List<SysRole> sysRoles = baseMapper.selectBatchIds(relationUserRoles.stream().map(RelationUserRole::getRoleId).collect(Collectors.toSet()));
        if(CollectionUtils.isEmpty(sysRoles)){
            return authorityList;
        }
        authorityList.addAll(sysRoles.stream().map(item -> "ROLE_" + item.getRoleCode()).collect(Collectors.toSet()));

        // 获取权限信息
        Set<SysPermission> sysPermissions = sysPermissionService.loadAuthorityByRoleIds(sysRoles.stream().map(SysRole::getRoleId).toList());
        authorityList.addAll(sysPermissions.stream().map(SysPermission::getPermissionCode).collect(Collectors.toSet()));
        return authorityList;
    }
}
