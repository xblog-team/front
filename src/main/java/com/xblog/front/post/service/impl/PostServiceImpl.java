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

/**
 * 게시물 관련 작업 수행하는 service
 * @author jihyeon
 */
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostAdaptor postAdaptor;

    /**
     * adaptor에서 받은 최신순 게시물 리스트들을 전달
     * @param partyId 그룹 아이디
     * @return postId, title, content, views, categoryId, userId로 구성된 dto 리스트
     */
    @Override
    public List<GetPostDto> getPostsByLatest(Long partyId) {
        return postAdaptor.getPostListByLatest(partyId);
    }

    /**
     * adaptor에서 받은 조회수 큰 순의 게시물 리스트들을 전달
     * @param partyId 그룹 아이디
     * @return postId, title, content, views, categoryId, userId로 구성된 dto 리스트
     */
    @Override
    public List<GetPostDto> getPostsByViews(Long partyId) {
        return postAdaptor.getPostListByViews(partyId);
    }

    /**
     * adaptor에서 받은 특정 카테고리에 속한 최신순 게시물 리스트들을 전달
     * @param categoryId 카테고리 아이디
     * @return postId, title, content, views, categoryId, userId로 구성된 dto 리스트
     */
    @Override
    public List<GetPostDto> getPostListByCategory(Long categoryId) {
        return postAdaptor.getPostListByCategory(categoryId);
    }

    /**
     * adaptor에서 받은 특정 카테고리에 속한 조회수가 큰 순의 리스트들을 전달
     * @param categoryId 카테고리 아이디
     * @return postId, title, content, views, categoryId, userId로 구성된 dto 리스트
     */
    @Override
    public List<GetPostDto> getPostListByCategoryAndViews(Long categoryId) {
        return postAdaptor.getPostListByCategoryAndViews(categoryId);
    }

    /**
     * adaptor에서 받은 특정 게시물을 전달
     * @param postId 게시물 아이디
     * @return postId, title, content, views, categoryId, userId로 구성된 dto
     */
    @Override
    public GetPostDto getPost(Long postId) {
        return postAdaptor.getPost(postId);
    }

    /**
     * 게시물 내용을 저장하는 method
     * @param addPostDto title, content, categoryId로 구성됨
     * @return title, content, categoryId로 구성된 dto
     */
    @Override
    public AddPostDto addPost(AddPostDto addPostDto) {
        return postAdaptor.createPost(addPostDto);
    }

    /**
     * 특정 게시물을 수정하는 method로 adaptor로 request, postId 전달
     * @param request title, content, categoryId로 구성됨
     * @param postId 게시물 아이디
     * @return postId, title, content, views, categoryId, userId로 구성된 dto
     */
    @Override
    public ModifyPostResponse modifyPost(ModifyPostRequest request, Long postId) {
        return postAdaptor.modifyPost(request, postId);
    }

    /**
     * 특정 게시물을 삭제하는 method로 adaptor로 postId 전달
     * @param postId 게시물 아이디
     */
    @Override
    public void deletePost(Long postId) {
        postAdaptor.deletePost(postId);
    }
}
