package com.example.potatotilnewsfeed.domain.comments.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
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

  @OneToMany
  @JoinColumn(name = "userId")
  private List<Comment> userList = new ArrayList<>();

  @OneToMany
  @JoinColumn(name = "commentId")
  private List<Comment> commentList = new ArrayList<>();

}
