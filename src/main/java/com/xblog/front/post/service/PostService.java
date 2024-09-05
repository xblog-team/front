package com.xblog.front.post.service;

import com.xblog.front.post.dto.AddPostDto;
import com.xblog.front.post.dto.GetPostDto;
import com.xblog.front.post.dto.ModifyPostRequest;
import com.xblog.front.post.dto.ModifyPostResponse;

import java.util.List;

public interface PostService {
    List<GetPostDto> getPostByViews(Long partyId);

    List<GetPostDto> getPostList(Long categoryId);

    GetPostDto getPost(Long postId);

    AddPostDto addPost(AddPostDto addPostDto);

    ModifyPostResponse modifyPost(ModifyPostRequest request, Long postId);

    void deletePost(Long postId);
}
