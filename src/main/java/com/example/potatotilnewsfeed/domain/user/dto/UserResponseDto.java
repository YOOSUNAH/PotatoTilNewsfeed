package com.example.potatotilnewsfeed.domain.user.dto;

import com.example.potatotilnewsfeed.domain.user.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private final String nickname;
    private final String introduce;


    public UserResponseDto(User user) {
        this.nickname = user.getNickname();
        this.introduce = user.getIntroduce();
    }
}