package com.xblog.front.comment.adaptor.impl;

import com.xblog.front.comment.adaptor.CommentAdaptor;
import com.xblog.front.comment.dto.AddCommentDto;
import com.xblog.front.comment.dto.GetCommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentAdaptorImpl implements CommentAdaptor {
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
    public List<GetCommentDto> getCommentsByPostId(Long postId) {
        HttpHeaders headers = makeHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List<GetCommentDto>> exchange = restTemplate.exchange(
                gatewayDomain+"/api/comments/" + postId,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {
                });
        return exchange.getBody();
    }

    @Override
    public void addComment(AddCommentDto dto) {
        HttpHeaders headers = makeHeaders();
        HttpEntity<AddCommentDto> entity = new HttpEntity<>(dto, headers);
        restTemplate.exchange(
                gatewayDomain+"/api/comment",
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<>() {
                });
    }
}
