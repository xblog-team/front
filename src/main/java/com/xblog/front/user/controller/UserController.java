package com.xblog.front.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.xblog.front.user.dto.UpdatePasswordRequestDto;
import com.xblog.front.user.service.UserService;

/**
 * 사용자 관련 설정을 처리하는 컨트롤러 클래스입니다.
 * 이 클래스는 사용자 계정 설정 페이지를 렌더링하고 사용자 정보를 업데이트하는 요청을 처리합니다.
 */
@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * 사용자 설정 페이지를 조회합니다.
     *
     * @param userId 사용자의 고유 식별자
     * @param model  모델 객체
     * @return 사용자 설정 페이지의 템플릿 이름
     */
    @GetMapping("/user-setting")
    public String getUserSettingPage(@RequestHeader("X-USER-ID") String userId, Model model) {
        model.addAttribute("nickname", userService.getCurrentNickname(userId));
        return "user/user-account-settings";
    }
    
    /**
     * 사용자의 닉네임을 변경합니다.
     *
     * @param userId 사용자의 고유 식별자
     * @param newNickname 변경할 새 닉네임
     * @return 사용자 설정 페이지로 리다이렉트
     */
    @PutMapping("/change-nickname")
    public String changeNicknameMethod(@RequestHeader("X-USER-ID") String userId, @RequestParam String newNickname) {
        userService.changeNickname(userId, newNickname);
        return "redirect:/user-setting";
    }

    /**
     * 사용자의 비밀번호를 변경합니다.
     *
     * @param updatePasswordRequestDto 비밀번호 업데이트 요청 DTO
     * @return 사용자 설정 페이지로 리다이렉트
     */
    @PutMapping("/change-password")
    public String changePasswordMethod(@RequestBody UpdatePasswordRequestDto updatePasswordRequestDto) {
        userService.changePassword(updatePasswordRequestDto);
        return "redirect:/user-setting";
    }
}