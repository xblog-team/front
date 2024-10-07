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


/**
 * 게시물 관련 adaptor
 * @author jihyeon
 */
@Component
@RequiredArgsConstructor
public class PostAdaptorImpl implements PostAdaptor {
    private final RestTemplate restTemplate;

    @Value("${gateway.api.url}")
    String gatewayDomain;

    /**
     * APPLICATION_JSON으로 contentType과 accept 설정한 HttpHeaders를 만드는 method
     * @return HttpHeaders
     */
    public HttpHeaders makeHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return headers;
    }

    /**
     * 특정 그룹에 속한 최신순의 게시물들을 가져오는 method
     * @param partyId 그룹 아이디
     * @return postId, title, content, views, categoryId, userId로 구성된 dto 리스트
     */
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

    /**
     * 특정 그룹에 속한 조회수 큰 순의 게시물들을 가져오는 method
     * @param partyId 그룹 아이디
     * @return postId, title, content, views, categoryId, userId로 구성된 dto 리스트
     */
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

    /**
     * 그룹의 특정 카테고리에 속한 최신순의 게시물들을 가져오는 method
     * @param categoryId 카테고리 아이디
     * @return postId, title, content, views, categoryId, userId로 구성된 dto 리스트
     */
    @Override
    public List<GetPostDto> getPostListByCategory(Long categoryId) {
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

    /**
     * 그룹의 특정 카테고리에 속한 조회수 큰 순의 게시물들을 가져오는 method
     * @param categoryId 카테고리 아이디
     * @return postId, title, content, views, categoryId, userId로 구성된 dto 리스트
     */
    @Override
    public List<GetPostDto> getPostListByCategoryAndViews(Long categoryId) {
        HttpHeaders headers = makeHeaders();

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List<GetPostDto>> exchange = restTemplate.exchange(
                gatewayDomain + "/api/posts/post-categories/" + categoryId +"/views",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {
                });
        return exchange.getBody();
    }

    /**
     * 특정 게시물들을 가져오는 method
     * @param postId 게시물 아이디
     * @return postId, title, content, views, categoryId, userId로 구성된 dto 리스트
     */
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

    /**
     * 게시물을 추가하는 요청을 gateway로 전송
     * @param addPostDto title, content, categoryId로 구성됨
     * @return titlem content, categoryId로 구성된 dto
     */
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

    /**
     * 특정 게시물을 수정하는 요청을 gateway로 전송
     * @param request title, content, categoryId로 구성됨
     * @param postId 게시물 아이디
     * @return postId, title, content, views, categoryId, userId로 구성된 response
     */
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

    /**
     * 특정 게시물을 삭제하는 요청을 gateway로 전송
     * @param postId 게시물 아이디
     */
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
