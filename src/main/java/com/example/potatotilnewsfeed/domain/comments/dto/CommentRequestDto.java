package com.example.potatotilnewsfeed.domain.comments.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {

  private String content;
  private Long tilId;
  private Long commentId;
  private Long userId;

}
