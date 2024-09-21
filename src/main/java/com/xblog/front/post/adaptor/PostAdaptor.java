package com.xblog.front.post.adaptor;

import com.xblog.front.post.dto.AddPostDto;
import com.xblog.front.post.dto.GetPostDto;
import com.xblog.front.post.dto.ModifyPostRequest;
import com.xblog.front.post.dto.ModifyPostResponse;
import org.springframework.stereotype.Component;

import java.util.List;

public interface PostAdaptor {
    List<GetPostDto> getPostListByLatest(Long partyId);

    List<GetPostDto> getPostListByViews(Long partyId);

    List<GetPostDto> getPostList(Long categoryId);

    GetPostDto getPost(Long postId);

    AddPostDto createPost(AddPostDto addPostDto);

    ModifyPostResponse modifyPost(ModifyPostRequest request, Long postId);

    void deletePost(Long postId);
}
