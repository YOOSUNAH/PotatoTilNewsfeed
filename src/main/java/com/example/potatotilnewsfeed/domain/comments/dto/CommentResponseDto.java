package com.example.potatotilnewsfeed.domain.comments.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class CommentResponseDto {

  private String message;
  private Long tilId;
  private Long commentId;
  private Long userId;
  private String content;

  public CommentResponseDto(String message, Long tilId,
      Long commentId, Long userId, String content) {
    this.message = message;
    this.commentId = commentId;
    this.userId = userId;
    this.content = content;
    this.tilId = tilId;
  }

}
