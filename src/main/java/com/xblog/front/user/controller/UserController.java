package com.xblog.front.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.xblog.front.user.adaptor.UserAdaptor;


@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserAdaptor userAdaptor;

    @GetMapping("/user-setting")
    public String getUserSettingPage(@RequestHeader("X-USER-ID") String userId, Model model) {
        model.addAttribute("nickname", userAdaptor.getCurrentNickname(userId));
        return "user/user-account-settings";
    }
    
    @PutMapping("/user-setting")
    public String changeNicknameMethod(@RequestHeader("X-USER-ID") String userId, @RequestParam String newNickname) {
        userAdaptor.changeNickname(userId, newNickname);
        return "redirect:/user-setting";
    }
}
