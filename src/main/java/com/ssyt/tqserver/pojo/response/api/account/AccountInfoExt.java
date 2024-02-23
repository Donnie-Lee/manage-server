package com.ssyt.tqserver.pojo.response.api.account;

import com.ssyt.tqserver.entity.AccountInfo;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

 @Data
public class AccountInfoExt extends AccountInfo {

    private List<TrendInfo> trends = Collections.EMPTY_LIST;

}
