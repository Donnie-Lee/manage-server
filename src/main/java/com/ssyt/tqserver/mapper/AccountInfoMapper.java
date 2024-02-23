package com.ssyt.tqserver.mapper;

import com.ssyt.tqserver.entity.AccountInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jakarta.validation.constraints.NotBlank;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author Lzhen
 * @since 2024-02-19
 */
public interface AccountInfoMapper extends BaseMapper<AccountInfo> {

    AccountInfo selectByMobile(@Param("mobile") String mobile);
}
