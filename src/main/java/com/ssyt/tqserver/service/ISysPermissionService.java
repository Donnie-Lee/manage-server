package com.ssyt.tqserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ssyt.tqserver.entity.SysPermission;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 权限信息表 服务类
 * </p>
 *
 * @author Lzhen
 * @since 2024-01-18
 */
public interface ISysPermissionService extends IService<SysPermission> {

    Set<SysPermission> loadAuthorityByRoleIds(List<Long> roleIds);
}
