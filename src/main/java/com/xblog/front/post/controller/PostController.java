package com.xblog.front.post.controller;

import com.xblog.front.category.dto.GetCategoryDto;
import com.xblog.front.category.service.CategoryService;
import com.xblog.front.comment.dto.GetCommentDto;
import com.xblog.front.comment.service.CommentService;
import com.xblog.front.post.dto.AddPostDto;
import com.xblog.front.post.dto.GetPostDto;
import com.xblog.front.post.dto.ModifyPostRequest;
import com.xblog.front.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 게시물과 관련된 작업을 처리하는 controller
 * @author jihyeon
 */
@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final CategoryService categoryService;
    private final CommentService commentService;

    private List<GetCategoryDto> getCategoryDtos(Long partyId){
        return categoryService.getCategoryList(partyId);
    }

    /**
     * 그룹에 속하는 모든 게시물들 중 카테고리 상관 없이 최신순으로 정렬
     * @param partyId 그룹 아이디
     * @return /user/post.html로 이동
     */
    @GetMapping("/xblog/user/party/{partyId}/posts")
    public String getPostsByLatest(@PathVariable Long partyId, Model model){
        List<GetCategoryDto> categoryList = getCategoryDtos(partyId);
        List<GetPostDto> postList = postService.getPostsByLatest(partyId);

        model.addAttribute("partyId", partyId);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("postList", postList);
        model.addAttribute("categoryId", "all-category");
        return "/user/post";
    }

    /**
     * 그룹에 속한 게시물들을 조회수가 많은 순으로 카테고리 상관없이 가져오는 method
     * @param partyId 그룹 아이디
     * @return /user/post.html로 이동
     */
    @GetMapping("/xblog/user/party/{partyId}/posts/views")
    public String getPostsByViews(@PathVariable Long partyId, Model model) {
        List<GetCategoryDto> categoryList = getCategoryDtos(partyId);
        List<GetPostDto> postList = postService.getPostsByViews(partyId);

        model.addAttribute("partyId", partyId);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("postList", postList);
        model.addAttribute("categoryId", "all-category");
        return "/user/post";
    }

    /**
     * 그룹에 속한 카테고리들중 특정 카테고리에 속한 게시물들 가져오는 method
     * @param partyId 그룹 아이디
     * @param categoryId 카테고리 아이디
     * @return /user/post.html로 이동
     */
    @GetMapping("/xblog/user/party/{partyId}/category/{categoryId}")
    public String getPostsByCategory(@PathVariable Long partyId, @PathVariable Long categoryId, Model model) {
        List<GetCategoryDto> categoryList = getCategoryDtos(partyId);
        List<GetPostDto> postList = postService.getPostListByCategory(categoryId);

        model.addAttribute("partyId", partyId);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("postList", postList);
        model.addAttribute("categoryId", categoryId);
        return "/user/post";
    }

    /**
     * 그룹에 속한 카테고리들중 특정 카테고리에 속한 게시물들 조회수 많은 순으로 가져오는 method
     * @param partyId 그룹 아이디
     * @param categoryId 카테고리 아이디
     * @return /user/post.html로 이동
     */
    @GetMapping("/xblog/user/party/{partyId}/category/{categoryId}/views")
    public String getPostsByCategoryAndViews(@PathVariable Long partyId, @PathVariable Long categoryId, Model model) {
        List<GetCategoryDto> categoryList = getCategoryDtos(partyId);
        List<GetPostDto> postList = postService.getPostListByCategoryAndViews(categoryId);

        model.addAttribute("partyId", partyId);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("postList", postList);
        model.addAttribute("categoryId", categoryId);
        return "/user/post";
    }

    /**
     * 그룹에 속한 특정 게시물을 가져오는 method
     * @param partyId 그룹 아이디
     * @param postId 게시물 아이디
     * @return /user/postNote.html로 이동
     */
    @GetMapping("/xblog/user/party/{partyId}/posts/{postId}")
    public String getPost(@PathVariable Long partyId, @PathVariable Long postId, Model model) {
        GetPostDto postDto = postService.getPost(postId);
        GetCategoryDto categoryDto = categoryService.getCategory(postDto.getCategoryId());
        List<GetCommentDto> commentList = commentService.getCommentsByPostId(postId);

        model.addAttribute("post", postDto);
        model.addAttribute("category", categoryDto);
        model.addAttribute("commentList", commentList);

        model.addAttribute("partyId", partyId);
        model.addAttribute("postId", postId);
        return "/user/postNote";
    }

    /**
     * 특정 그룹에 게시물을 생성하는 method
     * @param addPostDto title, content, categoryId로 구성
     * @return getPost method로 redirect
     */
    @PostMapping("/xblog/user/party/{partyId}/posts/create")
    public String createPost(AddPostDto addPostDto){
        postService.addPost(addPostDto);
        return "redirect:/xblog/user/party/{partyId}/posts";
    }

    /**
     * 특정 게시물을 수정하는 페이지로 이동하는 method
     * @param partyId 그룹 아이디
     * @param postId 수정하고자 하는 게시물 아이디
     * @return /user/modifyPost로
     */
    @GetMapping("/xblog/user/party/{partyId}/posts/modify/{postId}")
    public String modifyPosts(@PathVariable Long partyId, @PathVariable Long postId, Model model) {
        GetPostDto post = postService.getPost(postId);
        List<GetCategoryDto> categoryList = getCategoryDtos(partyId);

        model.addAttribute("partyId", partyId);
        model.addAttribute("post", post);
        model.addAttribute("categoryList", categoryList);

        return "/user/modifyPost";
    }

    /**
     * 특정 게시물을 수정하는 method
     * @param request title, content, categoryId로 구성
     * @param postId 수정하고자하는 게시물 아이디
     * @return getPost method로 redirect
     */
    @PostMapping("/xblog/user/party/{partyId}/posts/modify/{postId}")
    public String modifyPost(ModifyPostRequest request, @PathVariable Long postId) {
        postService.modifyPost(request, postId);
        return "redirect:/xblog/user/party/{partyId}/posts/{postId}";
    }

    /**
     * 특정 게시물을 삭제하는 method
     * @param postId 삭제하고자 하는 게시물 아이디
     * @return getPost method로 redirect
     */
    @PostMapping("/xblog/user/party/{partyId}/posts/delete/{postId}")
    public String deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return "redirect:/xblog/user/party/{partyId}/posts";
    }
}
