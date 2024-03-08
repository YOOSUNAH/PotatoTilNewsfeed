package com.example.potatotilnewsfeed.domain.user.aop.controller;

import com.example.potatotilnewsfeed.domain.user.dto.CheckEmailRequestDto;
import com.example.potatotilnewsfeed.domain.user.dto.CheckEmailResponseDto;
import com.example.potatotilnewsfeed.domain.user.dto.EmailRequestDto;
import com.example.potatotilnewsfeed.domain.user.dto.EmailResponseDto;
import com.example.potatotilnewsfeed.domain.user.service.EmailService;
import com.example.potatotilnewsfeed.global.dto.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmailController {

    private static final String EMAIL_SUCCESS = "이메일 인증번호 발송 성공";
    private static final String CHECK_EMAIL_SUCCESS = "이메일 인증번호 확인 성공";

    private final EmailService emailService;

    @PostMapping("v1/users/sendEmail")
    public ResponseEntity<ResponseDto<EmailResponseDto>> sendEmail(@RequestBody @Valid EmailRequestDto emailDto) {

        return ResponseEntity.ok()
            .body(ResponseDto.<EmailResponseDto>builder()
                .message(EMAIL_SUCCESS)
                .data(emailService.joinEmail(emailDto))
                .build());
    }

    @PostMapping("v1/users/checkEmail/{id}")
    public ResponseEntity<ResponseDto<CheckEmailResponseDto>> checkEmail(@PathVariable Long id, @RequestBody @Valid CheckEmailRequestDto checkEmailDto) {

        return ResponseEntity.ok()
            .body(ResponseDto.<CheckEmailResponseDto>builder()
                .message(CHECK_EMAIL_SUCCESS)
                .data(emailService.checkEmail(checkEmailDto, id))
                .build());
    }

}
