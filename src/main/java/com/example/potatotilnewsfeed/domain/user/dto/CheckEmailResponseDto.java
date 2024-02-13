package com.example.potatotilnewsfeed.domain.user.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CheckEmailResponseDto {
    private boolean isChecked;
    public CheckEmailResponseDto(boolean isChecked) {
        this.isChecked = isChecked;
    }
}
