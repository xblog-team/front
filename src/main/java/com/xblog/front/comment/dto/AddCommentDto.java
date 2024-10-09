package com.xblog.front.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddCommentDto {
    private String comment;
    private Long postId;
}
