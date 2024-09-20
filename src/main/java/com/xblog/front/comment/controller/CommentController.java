package com.xblog.front.comment.controller;

import com.xblog.front.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import com.xblog.front.comment.dto.AddCommentDto;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/xblog/party/{partyId}/posts/{postId}/comment")
    public String createPost(AddCommentDto dto) {
        commentService.addComment(dto);
        return "redirect:/xblog/user/party/{partyId}/post/{postId}";
    }
}
