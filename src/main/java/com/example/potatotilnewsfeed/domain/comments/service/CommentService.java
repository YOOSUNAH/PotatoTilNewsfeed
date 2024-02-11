package com.example.potatotilnewsfeed.domain.comments.service;

import com.example.potatotilnewsfeed.domain.comments.dto.CommentResponseDto;
import com.example.potatotilnewsfeed.domain.comments.entity.Comment;
import com.example.potatotilnewsfeed.domain.comments.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
  // 사용자에게 어떤 공통적인(?) 기능을 제공할 것인가?(비즈니스 로직)

  private final CommentRepository commentRepository;


  // 댓글 작성
  public Comment createComment(Comment comment) {
// 64글자이면 등록 64글자를 초과하면 등록할 수 없습니다

    commentRepository.save(comment);

  }

  // 댓글 수정
  public Comment updateComment(Comment comment) {
    // 댓글이 존재하면 수정 아니면 (예외)존재하지 않습니다. exist



  }

  // 댓글 삭제
  public Comment deleteComment(Comment comment) {
    // 댓글이 존재하고 삭제할꺼면 true 아니면 false

  }

}
