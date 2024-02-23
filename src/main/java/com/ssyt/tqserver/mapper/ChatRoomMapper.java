package com.ssyt.tqserver.mapper;

import com.ssyt.tqserver.entity.ChatRoom;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 聊天室信息 Mapper 接口
 * </p>
 *
 * @author Lzhen
 * @since 2024-02-19
 */
public interface ChatRoomMapper extends BaseMapper<ChatRoom> {

    ChatRoom selectByAccountIds(@Param("accountIds") String accountIds);

}
