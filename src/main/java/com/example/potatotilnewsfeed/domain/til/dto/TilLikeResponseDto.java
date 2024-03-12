package com.example.potatotilnewsfeed.domain.til.dto;

import lombok.Getter;

@Getter
public class TilLikeResponseDto {

    private Long userId;
    private Long tilId;

    public TilLikeResponseDto(Long userId, Long tilId) {
        this.userId = userId;
        this.tilId = tilId;
    }
}
