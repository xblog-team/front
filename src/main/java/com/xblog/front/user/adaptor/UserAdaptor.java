package com.xblog.front.user.adaptor;

import com.xblog.front.user.dto.UpdatePasswordRequestDto;

public interface UserAdaptor {
    void changeNickname(String userId, String newNickname);
    String getCurrentNickname(String userId);
    void updatePassword(UpdatePasswordRequestDto updatePasswordRequestDto);
}
