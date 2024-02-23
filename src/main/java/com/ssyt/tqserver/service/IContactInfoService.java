package com.ssyt.tqserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ssyt.tqserver.entity.ContactInfo;
import com.ssyt.tqserver.pojo.response.api.contact.ContactInfoResponse;

import java.util.List;

/**
 * <p>
 * 通讯录信息 服务类
 * </p>
 *
 * @author Lzhen
 * @since 2024-02-19
 */
public interface IContactInfoService extends IService<ContactInfo> {
    int a = 0;
    List<ContactInfoResponse> contacts();

}
