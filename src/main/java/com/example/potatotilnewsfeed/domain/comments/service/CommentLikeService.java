package com.example.potatotilnewsfeed.domain.comments.service;

import com.example.potatotilnewsfeed.domain.comments.dto.CommentLikeResponseDto;
import com.example.potatotilnewsfeed.domain.comments.dto.CommentRequestDto;
import com.example.potatotilnewsfeed.domain.comments.entity.Comment;
import com.example.potatotilnewsfeed.domain.comments.entity.CommentLike;
import com.example.potatotilnewsfeed.domain.comments.repository.CommentLikeRepository;
import com.example.potatotilnewsfeed.domain.comments.repository.CommentRepository;
import com.example.potatotilnewsfeed.domain.til.entity.Til;
import com.example.potatotilnewsfeed.domain.til.repository.TilRepository;
import com.example.potatotilnewsfeed.domain.til.service.TilService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentLikeService {

  private final CommentLikeRepository commentLikeRepository;
  private final CommentRepository commentRepository;

  // 댓글 좋아요 등록
  public CommentLikeResponseDto likeRegisterComment(Long tilId, Long commentId, User user) {
    // 댓글에 대한 정보, 유저에 대한 정보
    Comment comment = commentRepository.findById(commentId).orElseThrow(
        () -> new NoSuchElementException("해당 Comment를 찾을 수 없습니다.")
    );

    // CommentLikeRepository에서 해당 User, Comment 정보가 이미 들어있는지를 체크
    if(commentLikeRepository.findByUserAndComment(user, comment).isPresent()) {
      throw new IllegalArgumentException("이미 좋아요한 댓글입니다.");
    }

    CommentLike commentLike = new CommentLike(user, comment);
    commentLikeRepository.save(commentLike);

    return new CommentLikeResponseDto("선택하신 댓글에 좋아요가 등록되었습니다.", tilId, commentId,
        requestDto.getUserId());
  }

  // 댓글 좋아요 삭제
  public CommentLikeResponseDto likeDeleteComment(Long tilId, Long commentId,
      CommentRequestDto requestDto) {
    CommentLike likeDelete = new CommentLike(tilId, commentId, requestDto.getUserId());
    commentLikeRepository.delete(likeDelete);

    return new CommentLikeResponseDto("선택하신 댓글에 좋아요가 삭제되었습니다.", tilId, commentId,
        requestDto.getUserId());
  }

}
