package com.xblog.front.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 사용자의 비밀번호 변경 요청을 위한 DTO(Data Transfer Object) 클래스입니다.
 * 이 클래스는 기존 비밀번호와 새 비밀번호를 포함합니다.
 */
@Getter
@AllArgsConstructor
public class UpdatePasswordRequestDto {

    /**
     * 사용자의 기존 비밀번호입니다.
     */
    private String oldPassword;

    /**
     * 사용자가 새로 설정할 비밀번호입니다.
     */
    private String newPassword;
}