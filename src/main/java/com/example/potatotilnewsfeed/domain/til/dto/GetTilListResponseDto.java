package com.example.potatotilnewsfeed.domain.til.dto;

import com.example.potatotilnewsfeed.domain.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class GetTilListResponseDto {

    private String nickname;
    private List<GetTilResponseDto> responseDtoList;

    public GetTilListResponseDto(List<GetTilResponseDto> responseDtoList, User user) {
        nickname = user.getNickname();
        this.responseDtoList = responseDtoList;
    }
}
