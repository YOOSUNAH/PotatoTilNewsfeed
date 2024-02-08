package com.example.potatotilnewsfeed.domain.user.controller;

import com.example.potatotilnewsfeed.domain.user.dto.SignupRequestDto;
import com.example.potatotilnewsfeed.domain.user.service.UserService;
import com.example.potatotilnewsfeed.global.dto.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    public static final String SIGN_UP_API = "회원 가입 API";
    public static final String SIGN_UP_SUCCESS = "회원 가입 성공";

    @PostMapping("/v1/users/signup")
    public ResponseEntity<ResponseDto<Void>> signup(
        @RequestBody @Valid SignupRequestDto requestDto) {
        log.info(SIGN_UP_API);

        userService.signup(requestDto);

        return ResponseEntity.ok()
            .body(ResponseDto.<Void>builder()
                .message(SIGN_UP_SUCCESS)
                .build());
    }

}
