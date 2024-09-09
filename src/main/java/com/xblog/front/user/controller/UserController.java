package com.xblog.front.user.controller;

import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/user-setting")
    public String getUserSettingPage() {
        return "user/user-account-settings";
    }
    
}
