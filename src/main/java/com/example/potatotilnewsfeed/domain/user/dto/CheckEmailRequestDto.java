package com.example.potatotilnewsfeed.domain.user.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CheckEmailRequestDto {

    @NotEmpty(message = "인증번호를 입력해 주세요")
    private String number;

}
