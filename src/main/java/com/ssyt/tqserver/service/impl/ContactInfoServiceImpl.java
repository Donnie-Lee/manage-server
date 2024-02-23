package com.ssyt.tqserver.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssyt.tqserver.entity.AccountInfo;
import com.ssyt.tqserver.entity.ContactInfo;
import com.ssyt.tqserver.framework.utils.BeanTransferUtils;
import com.ssyt.tqserver.mapper.ContactInfoMapper;
import com.ssyt.tqserver.pojo.response.api.account.AccountInfoExt;
import com.ssyt.tqserver.pojo.response.api.contact.ContactInfoResponse;
import com.ssyt.tqserver.service.IAccountInfoService;
import com.ssyt.tqserver.service.IContactInfoService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 通讯录信息 服务实现类
 * </p>
 *
 * @author Lzhen
 * @since 2024-02-19
 */
@Service
public class ContactInfoServiceImpl extends ServiceImpl<ContactInfoMapper, ContactInfo> implements IContactInfoService {

    @Resource
    private IAccountInfoService accountInfoService;

    @Override
    public List<ContactInfoResponse> contacts() {
        List<ContactInfoResponse> contactInfoResponseList = new ArrayList<>();
        List<ContactInfo> contactInfos = baseMapper.selectByUserId(Long.parseLong(StpUtil.getLoginId().toString()));
        if (CollectionUtils.isNotEmpty(contactInfos)) {
            List<AccountInfo> accountInfos = accountInfoService.listByIds(contactInfos.stream().map(ContactInfo::getContactUserId).collect(Collectors.toSet()));
            // TODO 拼音分组排序
            // TODO 当前用户状态查看
            for (ContactInfo contactInfo : contactInfos) {
                ContactInfoResponse contactInfoResponse = BeanTransferUtils.transfer(contactInfo, ContactInfoResponse.class);
                accountInfos.stream()
                        .filter(accountInfo -> accountInfo.getId().equals(contactInfo.getContactUserId()))
                        .map(item -> BeanTransferUtils.transfer(item, AccountInfoExt.class))
                        .findFirst()
                        .ifPresent(contactInfoResponse::setAccountInfo);
                contactInfoResponseList.add(contactInfoResponse);
            }
        }
        return contactInfoResponseList;
    }
}
