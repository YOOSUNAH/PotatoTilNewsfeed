package com.example.potatotilnewsfeed.domain.comments.controller;

import com.example.potatotilnewsfeed.domain.comments.dto.CommentRequestDto;
import com.example.potatotilnewsfeed.domain.comments.dto.CommentResponseDto;
import com.example.potatotilnewsfeed.domain.comments.entity.Comment;
import com.example.potatotilnewsfeed.domain.comments.service.CommentService;
import com.example.potatotilnewsfeed.domain.user.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@AllArgsConstructor
public class CommentController { // 어떤 형태로 값을 주고 받을 것인가? json(숫자, 문자)

  private final CommentService commentService;

  @PostMapping("/tils/{tilId}/comments")
  public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long tilId, @RequestBody
      CommentRequestDto requestDto) {
    // 댓글 어떻게 작성, 응답코드 : 200
    CommentResponseDto post = commentService.createComment(tilId, requestDto);
    if (requestDto.content.length() <= 64) {
     String post = "작성한 댓글이 등록되었습니다.";
    } else {
      try {
        if (requestDto.content.length() > 64) {
          post = "댓글을 등록할 수 없습니다.";
        }
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      }
    }
    // (requestDto.content.length()) <= 64 ? "작성한 댓글이 등록되었습니다." : () -> new IllegalArgumentException("")
    return ResponseEntity.status(HttpStatus.CREATED).body(post);
  }

  @PutMapping("tils/{tilId}/comments/{commentId}")
  public ResponseEntity<?> updateComment(@PathVariable Long tilId,
      @RequestParam Long commentId, @ModelAttribute Comment comment) {
    // 댓글 수정, 응답코드 : 200
    Comment comment = commentService.updateComment(tilId, commentId, requestDto);
    comment =

    return ResponseEntity.status(HttpStatus.OK).body(comment);
  }


  @DeleteMapping("/tils/{tilId}/comments/{commentId}")
  public ResponseEntity<?> deleteComment(@PathVariable Long tilId,
      @RequestParam Long commentId) {
    Comment exit = commentService.deleteComment(tilId, commentId);

    return ResponseEntity.status(HttpStatus.OK).body(exit);



    // 댓글 삭제, 응답코드 : 204

  }

}
