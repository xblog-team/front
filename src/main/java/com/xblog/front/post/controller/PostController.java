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

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final CategoryService categoryService;
    private final CommentService commentService;

    private List<GetCategoryDto> getCategoryDtos(Long partyId){
        return categoryService.getCategoryList(partyId);
    }

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

    @PostMapping("/xblog/user/party/{partyId}/posts/create")
    public String createPost(AddPostDto addPostDto){
        postService.addPost(addPostDto);
        return "redirect:/xblog/user/party/{partyId}/posts";
    }

    @PostMapping("/xblog/user/party/{partyId}/posts/modify/{postId}")
    public String modifyPost(ModifyPostRequest request, @PathVariable Long postId) {
        postService.modifyPost(request, postId);
        return "redirect:/xblog/user/party/{partyId}/posts/{postId}";
    }

    @PostMapping("/xblog/user/party/{partyId}/posts/delete/{postId}")
    public String deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return "redirect:/xblog/user/party/{partyId}/posts";
    }
}
