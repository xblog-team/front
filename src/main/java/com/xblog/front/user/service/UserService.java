package com.xblog.front.user.service;

import com.xblog.front.user.dto.UpdatePasswordRequestDto;

public interface UserService {
    void changeNickname(String userId, String newNickname);
    String getCurrentNickname(String userId);
    void changePassword(UpdatePasswordRequestDto updatePasswordRequestDto);
}
