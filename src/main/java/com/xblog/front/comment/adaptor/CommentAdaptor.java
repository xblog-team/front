package com.xblog.front.comment.adaptor;

import com.xblog.front.comment.dto.AddCommentDto;
import com.xblog.front.comment.dto.GetCommentDto;

import java.util.List;

public interface CommentAdaptor {
    List<GetCommentDto> getCommentsByPostId(Long postId);

    void addComment(AddCommentDto dto);
}
