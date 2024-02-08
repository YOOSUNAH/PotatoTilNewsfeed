package com.example.potatotilnewsfeed.domain.user.dto;

import com.example.potatotilnewsfeed.domain.user.entity.User;
import lombok.Getter;

@Getter
public class UserRequestDto {
    private String nickname;
    private String password;
    private String introduce;
    public UserRequestDto(User user){
        this.nickname = user.getNickname();
        this.password = user.getPassword();
        this.introduce = user.getIntroduce();
    }
}