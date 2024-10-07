package com.xblog.front.user.service;

import com.xblog.front.user.dto.UpdatePasswordRequestDto;

/**
 * 사용자 관련 서비스 인터페이스입니다.
 * 이 인터페이스는 사용자의 닉네임 변경 및 비밀번호 업데이트와 같은 
 * 사용자 관련 작업을 처리하는 메서드를 정의합니다.
 */
public interface UserService {

    /**
     * 주어진 사용자 ID에 대한 닉네임을 변경합니다.
     *
     * @param userId  변경할 사용자의 고유 ID
     * @param newNickname 새로 설정할 닉네임
     */
    void changeNickname(String userId, String newNickname);

    /**
     * 주어진 사용자 ID에 대한 현재 닉네임을 반환합니다.
     *
     * @param userId  조회할 사용자의 고유 ID
     * @return 사용자의 현재 닉네임
     */
    String getCurrentNickname(String userId);

    /**
     * 주어진 비밀번호 변경 요청 DTO를 사용하여 사용자의 비밀번호를 변경합니다.
     *
     * @param updatePasswordRequestDto 비밀번호 변경 요청을 포함하는 DTO
     */
    void changePassword(UpdatePasswordRequestDto updatePasswordRequestDto);
}