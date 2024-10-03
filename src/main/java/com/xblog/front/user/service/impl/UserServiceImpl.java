package com.xblog.front.user.service.impl;

import org.springframework.stereotype.Service;

import com.xblog.front.user.adaptor.UserAdaptor;
import com.xblog.front.user.dto.UpdatePasswordRequestDto;
import com.xblog.front.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserAdaptor userAdaptor;
    
    @Override
    public void changeNickname(String userId, String newNickname) {
        userAdaptor.changeNickname(userId, newNickname);
    }

    @Override
    public String getCurrentNickname(String userId) {
        return userAdaptor.getCurrentNickname(userId);
    }

    @Override
    public void changePassword(UpdatePasswordRequestDto updatePasswordRequestDto) {
        userAdaptor.updatePassword(updatePasswordRequestDto);
    }

}
