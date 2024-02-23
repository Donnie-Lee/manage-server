package com.ssyt.tqserver.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ssyt.tqserver.entity.ContactInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 通讯录信息 Mapper 接口
 * </p>
 *
 * @author Lzhen
 * @since 2024-02-19
 */
public interface ContactInfoMapper extends BaseMapper<ContactInfo> {

    List<ContactInfo> selectByUserId(@Param("userId") long userId);
}
