package com.xblog.front.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class ModifyPostResponse {
    private Long postId;
    private String title;
    private String content;
    private Long views;
    private Long categoryId;
    private String userId;
}
