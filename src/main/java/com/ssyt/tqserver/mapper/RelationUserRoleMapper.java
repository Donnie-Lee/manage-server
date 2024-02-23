package com.ssyt.tqserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ssyt.tqserver.entity.RelationUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * <p>
 * 用户角色关联信息表 Mapper 接口
 * </p>
 *
 * @author Lzhen
 * @since 2024-01-18
 */
public interface RelationUserRoleMapper extends BaseMapper<RelationUserRole> {

    Set<RelationUserRole> selectByUserId(@Param("userId") Long userId);
}
