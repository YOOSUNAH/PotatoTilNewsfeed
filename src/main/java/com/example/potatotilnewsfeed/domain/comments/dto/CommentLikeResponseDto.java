package com.example.potatotilnewsfeed.domain.comments.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentLikeResponseDto {

  private Long tilId;
  private Long userId;
  private Long commentId;
}
