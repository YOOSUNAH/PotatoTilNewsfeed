package com.example.potatotilnewsfeed.domain.comments.entity;

import com.example.potatotilnewsfeed.domain.comments.dto.CommentLikeResponseDto;
import com.example.potatotilnewsfeed.domain.comments.dto.CommentRequestDto;
import com.example.potatotilnewsfeed.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Entity
@Getter
@Table(name = "comment")
@NoArgsConstructor
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

  @OneToMany(mappedBy = "commentLike")
  @JoinColumn(name = "userId")
  private List<CommentLike> userList = new ArrayList<>();

  @OneToMany(mappedBy = "commentLike")
  @JoinColumn(name = "commentId")
  private List<CommentLike> commentList = new ArrayList<>();


}
