package com.xblog.front.user.adaptor.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.xblog.front.user.adaptor.UserAdaptor;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserAdaptorImpl implements UserAdaptor{
    private final String USER_URL = "/api/user";
    private final RestTemplate restTemplate;

    @Value("${gateway.api.url}")
    private String gatewayDomain;

    public HttpHeaders makeHeaders(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        return httpHeaders;
    }

    @Override
    public void changeNickname(String userId, String newNickname){
        HttpHeaders headers = makeHeaders();
        headers.set("X-USER-ID", userId);
        HttpEntity<Void> httpEntity = new HttpEntity<>(headers);

        restTemplate.exchange(
            gatewayDomain + USER_URL + "/change-nickname?newNickname=" + newNickname, 
            HttpMethod.PUT,
            httpEntity,
            Void.class
        );
        
    }

    @Override
    public String getCurrentNickname(String userId) {
        HttpHeaders headers = makeHeaders();
        headers.set("X-USER-ID", userId);
        HttpEntity<Void> httpEntity = new HttpEntity<>(headers);

        return restTemplate.exchange(
            gatewayDomain + USER_URL + "/nickname",
            HttpMethod.GET,
            httpEntity,
            String.class
        ).getBody();
    }
}
