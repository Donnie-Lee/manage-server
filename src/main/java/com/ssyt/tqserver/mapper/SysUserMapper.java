package com.ssyt.tqserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssyt.tqserver.entity.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author Lzhen
 * @since 2024-01-18
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    Page<SysUser> page(Page<SysUser> page, @Param("name") String name);

    SysUser loadByUsername(@Param("username") String username);

}
