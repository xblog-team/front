package com.xblog.front.comment.controller;

import com.xblog.front.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import com.xblog.front.comment.dto.AddCommentDto;

/**
 * 댓글과 관련된 controller
 * @author jihyeon
 */
@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    /**
     * 특정 게시물에 댓글을 추가하는 method
     * @param dto comment, postId로 구성됨
     * @return getPost method로 redirect
     */
    @PostMapping("/xblog/party/{partyId}/posts/{postId}/comment")
    public String createComment(AddCommentDto dto) {
        commentService.addComment(dto);
        return "redirect:/xblog/user/party/{partyId}/post/{postId}";
    }
}
