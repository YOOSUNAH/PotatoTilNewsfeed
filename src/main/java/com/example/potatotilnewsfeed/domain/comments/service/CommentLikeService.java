package com.example.potatotilnewsfeed.domain.comments.service;

import com.example.potatotilnewsfeed.domain.comments.dto.CommentLikeResponseDto;
import com.example.potatotilnewsfeed.domain.comments.dto.CommentRequestDto;
import com.example.potatotilnewsfeed.domain.comments.entity.CommentLike;
import com.example.potatotilnewsfeed.domain.comments.repository.CommentLikeRepository;
import com.example.potatotilnewsfeed.domain.til.entity.Til;
import com.example.potatotilnewsfeed.domain.til.repository.TilRepository;
import com.example.potatotilnewsfeed.domain.til.service.TilService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentLikeService {

  // 댓글 좋아요 등록
  public CommentLikeResponseDto likeRegisterComment(Long tilId,
      Long commentId,
      CommentRequestDto requestDto) {
    CommentLike likeList = new CommentLike(tilId, commentId, requestDto.getUserId());
//   if(requestDto.getUserId().)
    return null;
  }

  // 댓글 좋아요 삭제
  public CommentLikeResponseDto likeDeleteComment() {

    return null;
  }

}
