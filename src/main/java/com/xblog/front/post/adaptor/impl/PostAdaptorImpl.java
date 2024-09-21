package com.xblog.front.post.adaptor.impl;

import com.xblog.front.post.adaptor.PostAdaptor;
import com.xblog.front.post.dto.AddPostDto;
import com.xblog.front.post.dto.GetPostDto;
import com.xblog.front.post.dto.ModifyPostRequest;
import com.xblog.front.post.dto.ModifyPostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Component
@RequiredArgsConstructor
public class PostAdaptorImpl implements PostAdaptor {
    private final RestTemplate restTemplate;

    @Value("http://localhost:8090")
    String gatewayDomain;


    public HttpHeaders makeHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return headers;
    }

    @Override
    public List<GetPostDto> getPostListByLatest(Long partyId) {
        HttpHeaders headers = makeHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List<GetPostDto>> exchange = restTemplate.exchange(
                gatewayDomain + "/api/posts/all/" + partyId,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {
                });
        return exchange.getBody();
    }

    @Override
    public List<GetPostDto> getPostListByViews(Long partyId) {
        HttpHeaders headers = makeHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List<GetPostDto>> exchange = restTemplate.exchange(
                gatewayDomain + "/api/posts/post-views/" + partyId,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {
                });
        return exchange.getBody();
    }

    @Override
    public List<GetPostDto> getPostList(Long categoryId) {
        HttpHeaders headers = makeHeaders();

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List<GetPostDto>> exchange = restTemplate.exchange(
                gatewayDomain + "/api/posts/post-categories/" + categoryId,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {
                });
        return exchange.getBody();
    }

    @Override
    public GetPostDto getPost(Long postId) {
        HttpHeaders headers = makeHeaders();

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<GetPostDto> exchange = restTemplate.exchange(
                gatewayDomain + "/api/posts/" + postId,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {});
        return exchange.getBody();
    }

    @Override
    public AddPostDto createPost(AddPostDto addPostDto) {
        HttpHeaders headers = makeHeaders();
        HttpEntity<AddPostDto> entity = new HttpEntity<>(addPostDto, headers);
        ResponseEntity<AddPostDto> exchange = restTemplate.exchange(
                gatewayDomain + "/api/posts",
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<>() {}
        );
        return exchange.getBody();
    }

    @Override
    public ModifyPostResponse modifyPost(ModifyPostRequest request, Long postId) {
        HttpHeaders headers = makeHeaders();
        HttpEntity<ModifyPostRequest> entity = new HttpEntity<>(request, headers);
        ResponseEntity<ModifyPostResponse> exchange = restTemplate.exchange(
                gatewayDomain + "/api/posts/" + postId,
                HttpMethod.PUT,
                entity,
                new ParameterizedTypeReference<>() {}
        );
        return exchange.getBody();
    }

    @Override
    public void deletePost(Long postId) {
        HttpHeaders headers = makeHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        restTemplate.exchange(
                gatewayDomain + "/api/posts/" + postId,
                HttpMethod.DELETE,
                entity,
                new ParameterizedTypeReference<>() {}
        );
    }
}
