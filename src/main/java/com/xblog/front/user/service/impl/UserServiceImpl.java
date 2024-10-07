package com.xblog.front.user.service.impl;

import org.springframework.stereotype.Service;

import com.xblog.front.user.adaptor.UserAdaptor;
import com.xblog.front.user.dto.UpdatePasswordRequestDto;
import com.xblog.front.user.service.UserService;

import lombok.RequiredArgsConstructor;

/**
 * 사용자 관련 서비스 기능을 제공하는 구현 클래스입니다.
 * 이 클래스는 사용자 어댑터를 사용하여 닉네임 변경, 현재 닉네임 조회 및 비밀번호 변경을 수행합니다.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserAdaptor userAdaptor;
    
    /**
     * 사용자의 닉네임을 변경합니다.
     *
     * @param userId      사용자의 고유 식별자
     * @param newNickname 변경할 새 닉네임
     */
    @Override
    public void changeNickname(String userId, String newNickname) {
        userAdaptor.changeNickname(userId, newNickname);
    }

    /**
     * 현재 사용자의 닉네임을 조회합니다.
     *
     * @param userId 사용자의 고유 식별자
     * @return 사용자의 현재 닉네임
     */
    @Override
    public String getCurrentNickname(String userId) {
        return userAdaptor.getCurrentNickname(userId);
    }

    /**
     * 사용자의 비밀번호를 변경합니다.
     *
     * @param updatePasswordRequestDto 비밀번호 변경 요청 DTO
     */
    @Override
    public void changePassword(UpdatePasswordRequestDto updatePasswordRequestDto) {
        userAdaptor.updatePassword(updatePasswordRequestDto);
    }
}