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

/**
 * 댓글 관련 adaptor
 * @author jihyeon
 */
@Component
@RequiredArgsConstructor
public class CommentAdaptorImpl implements CommentAdaptor {
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
     * 특정 게시물에 속한 댓글들을 모두 조회하는 요청을 gateway에 전달
     * @param postId 게시물 아이디
     * @return commentId, comment, postId, userId로 구성된 dto
     */
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

    /**
     * 댓글을 추가하는 요청을 gateway에 전달
     * @param dto comment, postId로 구성된 dto
     */
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
