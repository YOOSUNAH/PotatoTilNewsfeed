package com.example.potatotilnewsfeed.domain.comments.entity;

import com.example.potatotilnewsfeed.domain.comments.dto.CommentLikeResponseDto;
import com.example.potatotilnewsfeed.domain.comments.dto.CommentRequestDto;
import com.example.potatotilnewsfeed.domain.til.entity.Til;
import com.example.potatotilnewsfeed.domain.user.entity.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
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

  private Long tilId;
  private Long userId;

  @Column(nullable = false, length = 64)
  private String content;

  @ManyToOne
  @JoinColumn(name = "userid")
  private User user;

  @ManyToOne
  @JoinColumn(name = "tilid")
  private Til til;

  @OneToMany(mappedBy = "commentlikeid")
  private List<CommentLike> likeList = new ArrayList<>();

  public Comment(Long tilId, Long commentId, Long userId, String content) {
    this.commentId = commentId;
    this.tilId = tilId;
    this.userId = userId;
    this.content = content;
  }
}
