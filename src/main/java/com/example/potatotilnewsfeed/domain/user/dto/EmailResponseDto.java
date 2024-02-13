package com.example.potatotilnewsfeed.domain.user.dto;

import com.example.potatotilnewsfeed.domain.user.entity.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailResponseDto {

    private Long authId;
    private int authNumber;

    public EmailResponseDto(Email email) {
        this.authId = email.getEmailId();
        this.authNumber = email.getNumber();
    }
}
