package com.example.potatotilnewsfeed.domain.comments.entity;

import jakarta.persistence.*;
import lombok.Getter;


@Entity
@Getter
@Table(name = "comment")
public class Comment {  // 댓글

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long commentId;

  @JoinColumn(nullable = false, unique = true)
  private Long tilId;

  @JoinColumn(nullable = false, unique = true)
  private Long userId;

  @Column(nullable = false, unique = true)
  private String content;

}
