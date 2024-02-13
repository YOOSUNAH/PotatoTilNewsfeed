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
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/v1/tils")
@AllArgsConstructor
public class CommentController { // 어떤 형태로 값을 주고 받을 것인가? json(숫자, 문자)

  private final CommentService commentService;

  @PostMapping("/{tilId}/comments")
  public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long tilId, @RequestBody
      CommentRequestDto requestDto) {
    // 댓글 어떻게 작성, 응답코드 : 200
    commentService.createComment(tilId, requestDto);

    return null;
  }
//
  @PutMapping("tils/{tilId}/comments/{commentId}")
  public void updateComment(@PathVariable Long tilId,
      @RequestParam Long commentId, @ModelAttribute Comment comment) {
    // 댓글 수정, 응답코드 : 200

  }
//

  @DeleteMapping("/tils/{tilId}/comments/{commentId}")
  public void deleteComment(@PathVariable Long tilId,
      @RequestParam Long commentId) {
    // 댓글 삭제, 응답코드 : 204

  }

}
