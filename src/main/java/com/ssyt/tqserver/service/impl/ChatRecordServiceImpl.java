package com.ssyt.tqserver.service.impl;

import com.ssyt.tqserver.entity.ChatRecord;
import com.ssyt.tqserver.mapper.ChatRecordMapper;
import com.ssyt.tqserver.service.IChatRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 聊天记录 服务实现类
 * </p>
 *
 * @author Lzhen
 * @since 2024-02-19
 */
@Service
public class ChatRecordServiceImpl extends ServiceImpl<ChatRecordMapper, ChatRecord> implements IChatRecordService {

    @Override
    public List<ChatRecord> listByChatRoomId(Long chatRoomId) {
        return baseMapper.listByChatRoomId(chatRoomId);
    }
}
