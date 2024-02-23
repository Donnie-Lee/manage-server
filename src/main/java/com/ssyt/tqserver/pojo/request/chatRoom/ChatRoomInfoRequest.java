package com.ssyt.tqserver.pojo.request.chatRoom;

import jakarta.validation.constraints.NotNull;

public record ChatRoomInfoRequest(@NotNull Integer type,
                                  Long userId,
                                  Long groupId) {
}
