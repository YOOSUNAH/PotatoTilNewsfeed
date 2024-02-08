package com.example.potatotilnewsfeed.domain.comments.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class CommentController {


  @PostMapping("/tils/{tilId}/comments")
  public String createdComments(@PathVariable) { // 댓글 작성
    // @RequestBody  @ResponseBody  응답코드 : 200

  }


  @PutMapping("tils/{tilId}/comments/{commentId}")
  public String updatedComments() { // 댓글 수정
    // @RequestBody  @ResponseBody  응답코드 : 200



  }


  @DeleteMapping("/tils/{tilId}/comments/{commentId}")
  public String deletedComments() {  // 댓글 삭제
    //  응답코드 : 204
  }

  @PostMapping("/tils/{tilId}/comments/{commendItd}/likes")
  public String likeCommentsRegister() { // 댓글 좋아요 등록
    // 응답코드 : 201
  }

  @DeleteMapping("/v1/tils/{tilId}/comments/{commendItd}/likes")
  public String likeCommentsCancel() { // 댓글 좋아요 취소

  }

}
