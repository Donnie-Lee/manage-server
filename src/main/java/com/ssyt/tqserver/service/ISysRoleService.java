package com.ssyt.tqserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ssyt.tqserver.entity.SysRole;

import java.util.Set;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author Lzhen
 * @since 2024-01-18
 */
public interface ISysRoleService extends IService<SysRole> {

    Set<String> loadAuthorityByUserId(Long userId);
}
