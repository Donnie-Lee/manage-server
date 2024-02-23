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
 * 聊天记录
 * </p>
 *
 * @author Lzhen
 * @since 2024-02-19
 */
@Data
@TableName("chat_record")
public class ChatRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 聊天室id
     */
    private Long chatRoomId;

    /**
     * 发送人ID
     */
    private Long senderId;

    /**
     * 消息
     */
    private byte[] message;

    /**
     * 消息类型
     */
    private Integer messageType;

    /**
     * 发送时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime sendTime;

    /**
     * 是否撤回 0 否 1 是
     */
    private Boolean canceled;

    /**
     * 引用消息id
     */
    private Long quoteId;

}
