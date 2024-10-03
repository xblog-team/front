package com.xblog.front.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdatePasswordRequestDto {
    private String userId;
    private String oldPassword;
    private String newPassword;
}
