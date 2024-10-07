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
import com.xblog.front.user.dto.UpdatePasswordRequestDto;

import lombok.RequiredArgsConstructor;

/**
 * 사용자 관련 API와 상호작용하는 어댑터 구현 클래스입니다.
 * 이 클래스는 사용자 닉네임 변경, 닉네임 조회 및 비밀번호 업데이트와 같은 작업을 수행합니다.
 */
@Component
@RequiredArgsConstructor
public class UserAdaptorImpl implements UserAdaptor{
    private final String USER_URL = "/api/user";
    private final RestTemplate restTemplate;

    @Value("${gateway.api.url}")
    private String gatewayDomain;

    /**
     * HTTP 헤더를 생성합니다.
     *
     * @return 설정된 HTTP 헤더
     */
    public HttpHeaders makeHeaders(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        return httpHeaders;
    }

    /**
     * 사용자의 닉네임을 변경합니다.
     *
     * @param userId      사용자의 고유 식별자
     * @param newNickname 변경할 새 닉네임
     */
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

    /**
     * 현재 사용자의 닉네임을 조회합니다.
     *
     * @param userId 사용자의 고유 식별자
     * @return 사용자의 현재 닉네임
     */
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

    /**
     * 사용자의 비밀번호를 업데이트합니다.
     *
     * @param updatePasswordRequestDto 비밀번호 업데이트 요청 DTO
     */
    @Override
    public void updatePassword(UpdatePasswordRequestDto updatePasswordRequestDto) {
        HttpHeaders headers = makeHeaders();
        HttpEntity<UpdatePasswordRequestDto> httpEntity = new HttpEntity<>(updatePasswordRequestDto, headers);
        
        restTemplate.exchange(
            gatewayDomain + USER_URL + "/change-password",
            HttpMethod.PUT,
            httpEntity,
            void.class
        );
    }
}