package com.ssyt.tqserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssyt.tqserver.entity.ChatRoom;
import com.ssyt.tqserver.framework.utils.AssertUtils;
import com.ssyt.tqserver.framework.utils.BeanTransferUtils;
import com.ssyt.tqserver.mapper.ChatRoomMapper;
import com.ssyt.tqserver.pojo.response.api.chat.ChatRoomResponse;
import com.ssyt.tqserver.service.IAccountInfoService;
import com.ssyt.tqserver.service.IChatRecordService;
import com.ssyt.tqserver.service.IChatRoomService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;

/**
 * <p>
 * 聊天室信息 服务实现类
 * </p>
 *
 * @author Lzhen
 * @since 2024-02-19
 */
@Service
public class ChatRoomServiceImpl extends ServiceImpl<ChatRoomMapper, ChatRoom> implements IChatRoomService {

    @Resource
    private IAccountInfoService accountInfoService;

    @Resource
    private IChatRecordService chatRecordService;

    @Override
    public ChatRoomResponse chatRoomDetail(Long chatRoomId) {
        ChatRoom chatRoom = baseMapper.selectById(chatRoomId);
        AssertUtils.assertExec(Objects.nonNull(chatRoom), "聊天室信息不存在");

        ChatRoomResponse chatRoomResponse = BeanTransferUtils.transfer(chatRoom, ChatRoomResponse.class);
        // 获取聊天室用户信息
        chatRoomResponse.setAccountInfos(accountInfoService.listByIds(Arrays.asList(StringUtils.split(chatRoom.getAccountIds(), ","))));
        // 获取聊天记录信息
        chatRoomResponse.setChatRecords(chatRecordService.listByChatRoomId(chatRoomId));
        return chatRoomResponse;
    }

    @Override
    public Long create(ChatRoom chatRoom) {
        ChatRoom chatRoom1 = baseMapper.selectByAccountIds(chatRoom.getAccountIds());
        if(Objects.isNull(chatRoom1)){
            baseMapper.insert(chatRoom);
            chatRoom1 = chatRoom;
        }
        return chatRoom1.getId();
    }
}
