package com.example.potatotilnewsfeed.domain.comments.controller;


import com.example.potatotilnewsfeed.domain.comments.dto.CommentLikeResponseDto;
import com.example.potatotilnewsfeed.domain.comments.dto.CommentRequestDto;
import com.example.potatotilnewsfeed.domain.comments.dto.CommentResponseDto;
import com.example.potatotilnewsfeed.domain.comments.entity.CommentLike;
import com.example.potatotilnewsfeed.domain.comments.service.CommentLikeService;
import com.example.potatotilnewsfeed.domain.til.service.TilService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class CommentLikeController {  // 댓글 좋아요

  private final CommentLikeService commentLikeService;
  private final TilService tilService;


  @PostMapping("/tils/{tilId}/comments/{commendId}/likes")
  public ResponseEntity<CommentLikeResponseDto> likeRegisterComment(@PathVariable Long tilId,
      @PathVariable Long commentId, @RequestBody CommentRequestDto requestDto) {
    // 댓글 좋아요 등록 , 응답코드 : 201
    CommentLikeResponseDto likeUpdate = commentLikeService.likeRegisterComment(tilId, commentId,
        requestDto);

    return ResponseEntity.ok().body(likeUpdate);


  }

  @DeleteMapping("/tils/{tilId}/comments/{commendId}/likes")
  public ResponseEntity<CommentLikeResponseDto> likeCommentCancel(@PathVariable Long tilId,
      @PathVariable Long commentId, @RequestBody CommentRequestDto requestDto) {
    // 댓글 좋아요 취소, 응답코드 :204
    //CommentLikeResponseDto updateDto = commentLikeService.likeDeleteComment(tilId, commentId, requestDto);

    //return ResponseEntity.ok().body(updateDto);
    return null;

  }


}
