package com.xblog.front.post.service.impl;

import com.xblog.front.post.dto.AddPostDto;
import com.xblog.front.post.dto.GetPostDto;
import com.xblog.front.post.adaptor.PostAdaptor;
import com.xblog.front.post.dto.ModifyPostRequest;
import com.xblog.front.post.dto.ModifyPostResponse;
import com.xblog.front.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostAdaptor postAdaptor;

    @Override
    public List<GetPostDto> getPostsByViews(Long partyId) {
        return postAdaptor.getPostListByViews(partyId);
    }

    @Override
    public List<GetPostDto> getPostList(Long categoryId) {
        return postAdaptor.getPostList(categoryId);
    }

    @Override
    public GetPostDto getPost(Long postId) {
        return postAdaptor.getPost(postId);
    }

    @Override
    public AddPostDto addPost(AddPostDto addPostDto) {
        return postAdaptor.createPost(addPostDto);
    }

    @Override
    public ModifyPostResponse modifyPost(ModifyPostRequest request, Long postId) {
        return postAdaptor.modifyPost(request, postId);
    }

    @Override
    public void deletePost(Long postId) {
        postAdaptor.deletePost(postId);
    }
}
