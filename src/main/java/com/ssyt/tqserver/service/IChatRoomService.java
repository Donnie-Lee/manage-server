package com.ssyt.tqserver.service;

import com.ssyt.tqserver.entity.ChatRoom;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ssyt.tqserver.pojo.response.api.chat.ChatRoomResponse;

/**
 * <p>
 * 聊天室信息 服务类
 * </p>
 *
 * @author Lzhen
 * @since 2024-02-19
 */
public interface IChatRoomService extends IService<ChatRoom> {

    ChatRoomResponse chatRoomDetail(Long chatRoomId);

    Long create(ChatRoom chatRoom);
}
