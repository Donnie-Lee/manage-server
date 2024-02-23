package com.ssyt.tqserver.service;

import com.ssyt.tqserver.entity.ChatRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 聊天记录 服务类
 * </p>
 *
 * @author Lzhen
 * @since 2024-02-19
 */
public interface IChatRecordService extends IService<ChatRecord> {

    List<ChatRecord> listByChatRoomId(Long chatRoomId);
}
