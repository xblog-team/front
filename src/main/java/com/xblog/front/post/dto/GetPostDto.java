package com.xblog.front.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetPostDto {
    private Long postId;
    private String title;
    private String content;
    private Long categoryId;
    private String userId;
}
