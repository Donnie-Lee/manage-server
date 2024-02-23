package com.ssyt.tqserver.pojo.response.api.chat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssyt.tqserver.entity.AccountInfo;
import com.ssyt.tqserver.entity.ChatRecord;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ChatRoomResponse {


    /**
     * 主键id
     */
    private Long id;

    /**
     * 账户id
     */
    private List<AccountInfo> accountInfos;

    /**
     * 类型 0 单聊 1 群聊
     */
    private Integer type;

    /**
     * 聊天室名称
     */
    private String name;

    /**
     * 最新消息
     */
    private byte[] newestMsg;

    /**
     * 最新消息时间
     */
    private LocalDateTime newestMsgTime;

    /**
     * 消息类型 0 文本信息 1 语音信息 2 图片信息
     */
    private Integer newestMsgType;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    private List<ChatRecord> chatRecords;
}
