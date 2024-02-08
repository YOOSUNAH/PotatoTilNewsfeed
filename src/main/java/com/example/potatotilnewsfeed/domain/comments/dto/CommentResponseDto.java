package com.example.potatotilnewsfeed.domain.comments.dto;

import com.example.potatotilnewsfeed.domain.comments.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CommentResponseDto {

    public String contents;
    public int tilId;
    public int userId;
    public int commentId;




}
