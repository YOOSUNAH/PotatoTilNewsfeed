package com.example.potatotilnewsfeed.domain.comments.service;

import com.example.potatotilnewsfeed.domain.comments.dto.CommentRequestDto;
import com.example.potatotilnewsfeed.domain.comments.dto.CommentResponseDto;
import com.example.potatotilnewsfeed.domain.comments.repository.CommentRepository;
import com.example.potatotilnewsfeed.domain.user.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

  private final CommentRepository commentRepository;
  private final UserService userService;

  // 댓글 작성
  public CommentResponseDto createComment(String message, Long tilId, Long commentId, Long userId, String content) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    CommentResponseDto register = new CommentResponseDto(message, tilId, commentId, userId, content);
    return register;
  }

  // 댓글 수정
  public CommentResponseDto updateComment(String message, Long tilId, Long commentId, Long userId, String content) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    CommentResponseDto changeComment = new CommentResponseDto(message, tilId, commentId, userId, content);

    return changeComment;
  }

  // 댓글 삭제
  public void deleteComment(Long commentId) {


  }

}
