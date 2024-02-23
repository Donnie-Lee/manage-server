package com.ssyt.tqserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssyt.tqserver.entity.RelationRolePermission;
import com.ssyt.tqserver.entity.SysPermission;
import com.ssyt.tqserver.mapper.RelationRolePermissionMapper;
import com.ssyt.tqserver.mapper.SysPermissionMapper;
import com.ssyt.tqserver.service.ISysPermissionService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 权限信息表 服务实现类
 * </p>
 *
 * @author Lzhen
 * @since 2024-01-18
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {

    @Resource
    private RelationRolePermissionMapper rolePermissionMapper;

    @Override
    public Set<SysPermission> loadAuthorityByRoleIds(List<Long> roleIds) {
        HashSet<SysPermission> sysPermissions = new HashSet<>();
        // 获取角色权限关系
        Set<RelationRolePermission> relationRolePermissions = rolePermissionMapper.selectByRoleIds(roleIds);
        if (CollectionUtils.isEmpty(relationRolePermissions)) {
            return sysPermissions;
        }
        // 获取权限信息
        List<SysPermission> sysPermissionList = baseMapper.selectBatchIds(relationRolePermissions.stream().map(RelationRolePermission::getPermissionId).collect(Collectors.toSet()));
        if (CollectionUtils.isEmpty(sysPermissionList)) {
            return sysPermissions;
        }
        sysPermissions.addAll(sysPermissionList);
        return sysPermissions;
    }


}
