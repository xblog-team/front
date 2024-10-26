package com.xblog.front.post.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ModifyPostRequest {
    private String title;
    private String content;
    private Long categoryId;
}
