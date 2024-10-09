package com.xblog.front.comment.service;

import com.xblog.front.comment.dto.AddCommentDto;
import com.xblog.front.comment.dto.GetCommentDto;

import java.util.List;

public interface CommentService {
    List<GetCommentDto> getCommentsByPostId(Long postId);

    void addComment(AddCommentDto dto);
}
