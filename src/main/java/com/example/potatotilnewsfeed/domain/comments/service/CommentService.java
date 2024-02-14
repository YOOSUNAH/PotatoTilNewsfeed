package com.example.potatotilnewsfeed.domain.comments.service;

import com.example.potatotilnewsfeed.domain.comments.dto.CommentRequestDto;
import com.example.potatotilnewsfeed.domain.comments.dto.CommentResponseDto;
import com.example.potatotilnewsfeed.domain.comments.entity.Comment;
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


  // 댓글 작성
  public CommentResponseDto createComment(Long tilId, CommentRequestDto requestDto) {
    Comment register = new Comment(tilId, requestDto.getCommentId(),
        requestDto.getUserId(),
        requestDto.getContent());
    if (requestDto.getContent().length() > 64) {
      new IllegalArgumentException("64글자를 초과했습니다.");
    }
    commentRepository.save(register);
    return new CommentResponseDto("댓글이 등록되었습니다.", register.getTilId(), register.getCommentId(),
        register.getUserId(), register.getContent());
  }

  // 댓글 수정
  public CommentResponseDto updateComment(Long tilId, Long commentId,
      CommentRequestDto requestDto) {
    Comment update = new Comment(tilId, commentId, requestDto.getUserId(), requestDto.getContent());
    if (requestDto.getContent().length() > 64) {
      new IllegalArgumentException("64글자를 초과했습니다.");
    }
    commentRepository.save(update);
    return new CommentResponseDto("댓글이 수정되었습니다.", update.getTilId(),
        update.getCommentId(), update.getUserId(), update.getContent());
  }

  // 댓글 삭제
  public CommentResponseDto deleteComment(Long tilId, Long commentId,
      CommentRequestDto requestDto) {
    Comment exit = new Comment(tilId, commentId, requestDto.getUserId(), requestDto.getContent());
    commentRepository.delete(exit);

    return new CommentResponseDto("댓글이 삭제되었습니다.", exit.getUserId(), exit.getCommentId(),
        exit.getTilId(), exit.getContent());
  }

}
