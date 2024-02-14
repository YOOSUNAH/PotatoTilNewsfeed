package com.example.potatotilnewsfeed.domain.comments.controller;


import com.example.potatotilnewsfeed.domain.comments.dto.CommentLikeResponseDto;
import com.example.potatotilnewsfeed.domain.comments.service.CommentLikeService;
import com.example.potatotilnewsfeed.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class CommentLikeController {  // 댓글 좋아요

  private final CommentLikeService commentLikeService;


  @PostMapping("/tils/{tilId}/comments/{commentId}/likes")
  public ResponseEntity<CommentLikeResponseDto> likeRegisterComment(@PathVariable Long tilId,
      @PathVariable Long commentId,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    // 댓글 좋아요 등록 , 응답코드 : 201
    CommentLikeResponseDto updateLike = commentLikeService.likeRegisterComment(tilId, commentId,
        userDetails.getUser());

    return ResponseEntity.ok().body(updateLike);


  }

  @DeleteMapping("/tils/{tilId}/comments/{commentId}/likes")
  public ResponseEntity<CommentLikeResponseDto> likeDeleteComment(@PathVariable Long tilId,
      @PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
    // 댓글 좋아요 취소, 응답코드 :204
    commentLikeService.likeDeleteComment(tilId, commentId, userDetails.getUser());

    return ResponseEntity.noContent().build();

  }


}
