package com.ssyt.tqserver.pojo.response.api.contact;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssyt.tqserver.pojo.response.api.account.AccountInfoExt;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class ContactInfoResponse {

    /**
     * 通讯录用户
     */
    private AccountInfoExt accountInfo;

    /**
     * 添加时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 是否星标朋友
     */
    private Boolean stared;

    /**
     * 昵称备注
     */
    private String nickNameRemark;

    /**
     * 状态
     */
    private Integer status = 0;

}
