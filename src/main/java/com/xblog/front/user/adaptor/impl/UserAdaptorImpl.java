package com.xblog.front.user.adaptor.impl;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.xblog.front.user.adaptor.UserAdaptor;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserAdaptorImpl implements UserAdaptor{
    private final RestTemplate restTemplate;

    @Override
    public void changeNickname(String userId, String newNickname){
        
    }
}
