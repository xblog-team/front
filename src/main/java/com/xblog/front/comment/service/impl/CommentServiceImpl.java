package com.xblog.front.comment.service.impl;

import com.xblog.front.comment.adaptor.CommentAdaptor;
import com.xblog.front.comment.dto.AddCommentDto;
import com.xblog.front.comment.dto.GetCommentDto;
import com.xblog.front.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentAdaptor commentAdaptor;

    @Override
    public List<GetCommentDto> getCommentsByPostId(Long postId) {
        return commentAdaptor.getCommentsByPostId(postId);
    }

    @Override
    public void addComment(AddCommentDto dto) {
        commentAdaptor.addComment(dto);
    }
}
