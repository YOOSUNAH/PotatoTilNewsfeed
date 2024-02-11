package com.example.potatotilnewsfeed.domain.comments.controller;

import com.example.potatotilnewsfeed.domain.comments.dto.CommentResponseDto;
import com.example.potatotilnewsfeed.domain.comments.entity.Comment;
import com.example.potatotilnewsfeed.domain.comments.service.CommentService;
import com.example.potatotilnewsfeed.domain.user.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@AllArgsConstructor
public class CommentController { // 어떤 형태로 값을 주고 받을 것인가? json(숫자, 문자)

  private final CommentService commentService;

  @PostMapping("/tils/{tilId}/comments")
  public ResponseEntity<Comment> createComment(@PathVariable Long tilId) {

    // 댓글 작성, 응답코드 : 200

  }

  @PutMapping("tils/{tilId}/comments/{commentId}")
  public ResponseEntity<Comment> updateComment(@PathVariable Long tilId, @PathVariable Long commentId) {
    // 댓글 수정, 응답코드 : 200


  }


  @DeleteMapping("/tils/{tilId}/comments/{commentId}")
  public ResponseEntity<Comment> deleteComment(@PathVariable Long tilId, @PathVariable Long commentId) {

    // 댓글 삭제, 응답코드 : 204

  }

}
