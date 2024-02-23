package com.ssyt.tqserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ssyt.tqserver.entity.RelationRolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户角色关联信息表 Mapper 接口
 * </p>
 *
 * @author Lzhen
 * @since 2024-01-18
 */
public interface RelationRolePermissionMapper extends BaseMapper<RelationRolePermission> {

    Set<RelationRolePermission> selectByRoleIds(@Param("roleIds") List<Long> roleIds);
}
