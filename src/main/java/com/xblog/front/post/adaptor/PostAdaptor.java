package com.xblog.front.post.adaptor;

import com.xblog.front.post.dto.AddPostDto;
import com.xblog.front.post.dto.GetPostDto;
import com.xblog.front.post.dto.ModifyPostRequest;
import com.xblog.front.post.dto.ModifyPostResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PostAdaptor {
    List<GetPostDto> getPostList(Long partyId);

    GetPostDto getPost(Long postId);

    AddPostDto createPost(AddPostDto addPostDto);

    ModifyPostResponse modifyPost(ModifyPostRequest request, Long postId);

    void deletePost(Long postId);
}
