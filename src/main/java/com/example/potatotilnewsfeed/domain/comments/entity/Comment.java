package com.example.potatotilnewsfeed.domain.comments.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Comment {  // 댓글

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tilId;
    private Long commentId;
    private Long userId;
    private String content;

    //@Column(nullable = false)

    //@Column



}
