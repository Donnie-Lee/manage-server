package com.ssyt.tqserver.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 聊天室信息
 * </p>
 *
 * @author Lzhen
 * @since 2024-02-19
 */
@Data
@TableName("chat_room")
public class ChatRoom implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 账户id
     */
    private String accountIds;

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

    private Boolean top;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}
