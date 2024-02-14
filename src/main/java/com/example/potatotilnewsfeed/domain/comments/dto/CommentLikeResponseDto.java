package com.example.potatotilnewsfeed.domain.comments.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentLikeResponseDto {

  private String message;
  private Long tilId;
  private Long userId;
  private Long commentId;

  public CommentLikeResponseDto(String message, Long tilId, Long userId, Long commentId) {
    this.message = message;
    this.tilId = tilId;
    this.userId = userId;
    this.commentId = commentId;

  }
}
