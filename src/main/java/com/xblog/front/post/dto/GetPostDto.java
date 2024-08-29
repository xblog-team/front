package com.xblog.front.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetPostDto {
    private Long postId;
    private String title;
    private String content;
    private Long CategoryId;
    private String userId;
}
