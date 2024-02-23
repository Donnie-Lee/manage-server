package com.ssyt.tqserver.pojo.response.backend;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class CurrentUser {

    private String name;
    private String avatar;
    private Long userid;
    private String email;
    private String signature;
    private String title;
    private String group;
    private String tags;
    private String notifyCount;
    private String unreadCount;
    private Set<String> access;
    private Geographic geographic;
    private String address;
    private String phone;

}
