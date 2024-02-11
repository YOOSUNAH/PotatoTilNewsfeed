package com.example.potatotilnewsfeed.domain.comments.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;


@Entity
@Getter
@Table(name = "comment")
public class Comment {  // 댓글..

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long commentId;

  @JoinColumn(nullable = false, unique = true)
  private Long tilId;

  @JoinColumn(nullable = false, unique = true)
  private Long userId;

  @Column(nullable = false, length = 64)
  private String content;

}
