package com.example.potatotilnewsfeed.domain.comments.service;

import com.example.potatotilnewsfeed.domain.comments.dto.CommentRequestDto;
import com.example.potatotilnewsfeed.domain.comments.dto.CommentResponseDto;
import com.example.potatotilnewsfeed.domain.comments.entity.Comment;
import com.example.potatotilnewsfeed.domain.comments.repository.CommentRepository;
import com.example.potatotilnewsfeed.domain.til.repository.TilRepository;
import com.example.potatotilnewsfeed.domain.user.entity.User;
import com.example.potatotilnewsfeed.domain.user.repository.UserRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class CommentService {
  // 사용자에게 어떤 공통적인(?) 기능을 제공할 것인가?(비즈니스 로직)

  private final CommentRepository commentRepository;
  private final UserRepository userRepository;
  private final TilRepository tilRepository;



  // 댓글 작성
  public Comment createComment(Long commentId, CommentRequestDto commentRequestDto) {


    return null;
  }

  // 댓글 수정
  public Comment updateComment(CommentResponseDto commentRequestDto, Long commentId) {


    return null;
  }

  // 댓글 삭제
  public void deleteComment(Long commentId) {


  }

}
