package com.example.potatotilnewsfeed.domain.comments.service;

import com.example.potatotilnewsfeed.domain.comments.dto.CommentLikeResponseDto;
import com.example.potatotilnewsfeed.domain.comments.entity.CommentLike;
import com.example.potatotilnewsfeed.domain.comments.repository.CommentLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentLikeService {

  private final CommentLikeRepository commentLikeRepository;

  // 댓글 좋아요 등록
  public CommentLikeResponseDto likeRegisterComment() {
    // 댓글이 존재하면 좋아요 누르고 숫자+1, 종아요 총 개수 +1 등록
    // 게시글에 대한 좋아요 count + 1 (중복 좋아요 불가)
    likeCount++;
    commentLikeRepository.save();
  }

  // 댓글 좋아요 삭제
  public CommentLikeResponseDto likeDeleteComment() {
    // 1. 좋아요를 누른 User가 다시 같은 글에 대해 좋아요를 누르게 된다면 좋아요 취소가 된다.
    // 등록한 댓글을 삭제한다면 종아요 총 개수 -1 적용
    likeCount--;
    commentLikeRepository.save();

  }

}
