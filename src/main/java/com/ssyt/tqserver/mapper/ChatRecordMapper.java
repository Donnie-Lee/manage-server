package com.ssyt.tqserver.mapper;

import com.ssyt.tqserver.entity.ChatRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 聊天记录 Mapper 接口
 * </p>
 *
 * @author Lzhen
 * @since 2024-02-19
 */
public interface ChatRecordMapper extends BaseMapper<ChatRecord> {

    List<ChatRecord> listByChatRoomId(@Param("chatRoomId") Long chatRoomId);
}
