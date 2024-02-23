package com.ssyt.tqserver.controller.api;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.ssyt.tqserver.entity.ChatRoom;
import com.ssyt.tqserver.pojo.request.chatRoom.ChatRoomInfoRequest;
import com.ssyt.tqserver.pojo.response.ResponseResult;
import com.ssyt.tqserver.pojo.response.api.chat.ChatRoomResponse;
import com.ssyt.tqserver.service.IChatRoomService;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("chatRoom")
public record ChatRoomController(IChatRoomService chatRoomService) {

    @GetMapping
    public ResponseResult<ChatRoomResponse> chatRoomDetail(@Validated @Min(value = 1) @RequestParam("chatRoomId") Long chatRoomId){
        return ResponseResult.success(chatRoomService.chatRoomDetail(chatRoomId));
    }

    @PostMapping
    public ResponseResult<Long> create(@Validated @RequestBody ChatRoom chatRoom){
        return ResponseResult.success(chatRoomService.create(chatRoom));
    }
}
