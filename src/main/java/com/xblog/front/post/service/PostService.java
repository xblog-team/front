package com.xblog.front.post.service;

import com.xblog.front.post.dto.AddPostDto;
import com.xblog.front.post.dto.GetPostDto;
import com.xblog.front.post.dto.ModifyPostRequest;
import com.xblog.front.post.dto.ModifyPostResponse;

import java.util.List;

public interface PostService {
    List<GetPostDto> getPostsByLatest(Long partyId);

    List<GetPostDto> getPostsByViews(Long partyId);

    List<GetPostDto> getPostListByCategory(Long categoryId);

    List<GetPostDto> getPostListByCategoryAndViews(Long categoryId);

    GetPostDto getPost(Long postId);

    AddPostDto addPost(AddPostDto addPostDto);

    ModifyPostResponse modifyPost(ModifyPostRequest request, Long postId);

    void deletePost(Long postId);
}
