package com.example.potatotilnewsfeed.domain.til.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TilResponseDto {

    private String message;
    private List<TilData> data;

    public TilResponseDto(String message, List<TilData> data) {
        this.message = message;
        this.data = data;
    }
}
