package com.example.potatotilnewsfeed.domain.comments.dto;

import com.example.potatotilnewsfeed.domain.comments.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Getter
@AllArgsConstructor
public class CommentResponseDto {

    public String contents;
    public Long tilId;
    public Long userId;
    public Long commentId;


    // @AllArgsConstructor : 모든 필드를 생성
    // @RequiredArgsConstructor : 초기화되지 않은 final 필드와 @Notnull이 붙어있는 필드를 생성해준다.
}
