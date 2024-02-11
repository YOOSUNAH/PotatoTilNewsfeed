package com.example.potatotilnewsfeed.domain.comments.controller;

import com.example.potatotilnewsfeed.domain.comments.dto.CommentLikeResponseDto;
import com.example.potatotilnewsfeed.domain.comments.dto.CommentResponseDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class CommentLikeController {  // 댓글 좋아요

  @PostMapping("/tils/{tilId}/comments/{commendId}/likes")
  public CommentLikeResponseDto likeCommentRegister(@PathVariable CommentLikeResponseDto likeRegister) {
    // 댓글 좋아요 등록
    // 응답코드 : 201
  }

  @DeleteMapping("/v1/tils/{tilId}/comments/{commendId}/likes")
  public CommentLikeResponseDto likeCommentCancel(@PathVariable CommentResponseDto likeCancel) {
    // 댓글 좋아요 취소
  // 응답코드 :204

  }


}
