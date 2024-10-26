package com.xblog.front.comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetCommentDto {
    private Long commentId;
    private String comment;
    private Long postId;
    private String userId;
}
