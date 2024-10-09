package com.xblog.front.comment.service.impl;

import com.xblog.front.comment.adaptor.CommentAdaptor;
import com.xblog.front.comment.dto.AddCommentDto;
import com.xblog.front.comment.dto.GetCommentDto;
import com.xblog.front.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 댓글 관련 service
 */
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentAdaptor commentAdaptor;

    /**
     * 특정 게시물에 있는 모든 댓글들 조회
     * @param postId 게시물 아이디
     * @return commentId, comment, postId, userId로 구성된 dto 리스트
     */
    @Override
    public List<GetCommentDto> getCommentsByPostId(Long postId) {
        return commentAdaptor.getCommentsByPostId(postId);
    }

    /**
     * 댓글 추가하는 method
     * @param dto comment, postId로 구성된 dto
     */
    @Override
    public void addComment(AddCommentDto dto) {
        commentAdaptor.addComment(dto);
    }
}
