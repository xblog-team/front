package com.xblog.front.post.controller;

import com.xblog.front.post.dto.AddPostDto;
import com.xblog.front.post.dto.GetPostDto;
import com.xblog.front.post.dto.ModifyPostRequest;
import com.xblog.front.post.dto.ModifyPostResponse;
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

    @GetMapping("/xblog/uesr/party/{partyId}/posts")
    public String getPosts(@PathVariable Long partyId, Model model) {
        List<GetPostDto> dto = postService.getPostList(partyId);
        return "/user/post";
    }

    @GetMapping("/xblog/user/party/{partyName}/post/{postId}")
    public String getPost(@PathVariable Long postId, Model model) {
        GetPostDto dto = postService.getPost(postId);
        model.addAttribute("post", dto);
        return "";
    }

    @PostMapping("/xblog/user/party/{partyName}/post/create")
    public String postPost(AddPostDto addPostDto, Model model){
        AddPostDto dto = postService.addPost(addPostDto);
        model.addAttribute("post", dto);
        return "";
    }

    @PutMapping("/xblog/user/party/{partyName}/post/modify/{postId}")
    public String modifyPost(ModifyPostRequest request, @PathVariable Long postId) {
        ModifyPostResponse response = postService.modifyPost(request, postId);
        return "";
    }

    @DeleteMapping("/xblog/user/party/{partyName}/post/delete/{postId}")
    public String deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return "";
    }
}
