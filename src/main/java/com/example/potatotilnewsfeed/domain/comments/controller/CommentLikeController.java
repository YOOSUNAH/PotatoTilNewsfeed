package com.example.potatotilnewsfeed.domain.comments.controller;

import com.example.potatotilnewsfeed.domain.comments.entity.CommentLike;
import com.example.potatotilnewsfeed.domain.comments.service.CommentLikeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@AllArgsConstructor
public class CommentLikeController {  // 댓글 좋아요

  private final CommentLikeService commentLikeService;

  @PostMapping("/tils/{tilId}/comments/{commendId}/likes")
  public ResponseEntity<CommentLike> likeCommentRegister(@PathVariable Long tilId,
      @PathVariable Long commendId) {
    // 댓글 좋아요 등록
    // 응답코드 : 201

  }

  @DeleteMapping("/tils/{tilId}/comments/{commendId}/likes")
  public ResponseEntity<CommentLike> likeCommentCancel(@PathVariable Long tilId,
      @PathVariable Long commendId) {
    // 댓글 좋아요 취소
    // 응답코드 :204

  }


}
