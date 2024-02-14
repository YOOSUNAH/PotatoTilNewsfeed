package com.example.potatotilnewsfeed.domain.comments.entity;

import com.example.potatotilnewsfeed.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Table(name = "commentLike")
@Setter
@NoArgsConstructor
public class CommentLike { // 댓글 좋아요

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long commentLikeId;

  @Column(nullable = false, unique = true)
  private Long userId;

  @Column(nullable = false, unique = true)
  private Long commentId;

  @ManyToOne
  @JoinColumn(name = "userid")
  private User user;

  @ManyToOne
  @JoinColumn(name = "commentid")
  private Comment comment;

  public CommentLike(Long commentLikeId, Long userId, Long commentId) {
    this.commentLikeId = commentLikeId;
    this.userId = userId;
    this.commentId = commentId;
  }
}
