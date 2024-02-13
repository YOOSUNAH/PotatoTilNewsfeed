package com.example.potatotilnewsfeed.domain.user.controller;

import com.example.potatotilnewsfeed.domain.user.dto.SignupRequestDto;
import com.example.potatotilnewsfeed.domain.user.dto.UserRequestDto;
import com.example.potatotilnewsfeed.domain.user.dto.UserResponseDto;
import com.example.potatotilnewsfeed.domain.user.service.UserService;
import com.example.potatotilnewsfeed.global.dto.ExceptionDto;
import com.example.potatotilnewsfeed.global.dto.ResponseDto;
import com.example.potatotilnewsfeed.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    public static final String SIGN_UP_API = "회원 가입 API";
    public static final String SIGN_UP_SUCCESS = "회원 가입 성공";
    public static final String LOG_OUT_API = "로그아웃 API";
    public static final String LOG_OUT_SUCCESS = "로그아웃 성공";

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

    @PatchMapping("/v1/users/logout")
    public ResponseEntity<ResponseDto<Void>> logout(
        @RequestHeader(value = "Authorization") String accessToken){
        log.info(LOG_OUT_API);

        userService.logout(accessToken);

        return ResponseEntity.ok()
            .body(ResponseDto.<Void>builder()
                .message(LOG_OUT_SUCCESS)
                .build());
    }



    @GetMapping("/v1/users")
    public ResponseEntity<ResponseDto<UserResponseDto>> getUser(
        @AuthenticationPrincipal UserDetailsImpl userDetails) {

            UserResponseDto userResponseDto = userService.getUser(userDetails);
            return ResponseEntity.ok()
                .body(ResponseDto.<UserResponseDto>builder()
                    .message("프로필 조회 성공")
                    .data(userResponseDto)
                    .build());
    }

    @PutMapping("/v1/users")
    public ResponseEntity<ResponseDto<UserResponseDto>> updateUser(
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @RequestBody UserRequestDto userRequestDto) {
            UserResponseDto userResponseDto = userService.updateUser(userDetails,
                userRequestDto);
            return ResponseEntity.ok()
                .body(ResponseDto.<UserResponseDto>builder()
                    .message("프로필 수정 성공")
                    .data(userResponseDto)
                    .build());
    }

    @PutMapping("/v1/users/password")
    public ResponseEntity<ResponseDto<UserResponseDto>> updatePassword(
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @RequestBody UserRequestDto userRequestDto) {

            UserResponseDto userResponseDto = userService.updatePassword(userDetails,
                userRequestDto);
            return ResponseEntity.ok()
                .body(ResponseDto.<UserResponseDto>builder()
                    .message("프로필 비밀번호 수정 성공")
                    .data(userResponseDto)
                    .build());
    }

    @DeleteMapping("/v1/users")
    public void deleteUser(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody UserRequestDto userRequestDto) {
        userService.deleteUser(userDetails, userRequestDto);
    }

    @ExceptionHandler({IllegalArgumentException.class, BadCredentialsException.class})
    public ResponseEntity<ExceptionDto> handleException(Exception e){
        return ResponseEntity.badRequest()
                .body(ExceptionDto.builder()
                    .statusCode(400)
                    .state(HttpStatus.BAD_REQUEST)
                    .message(e.getMessage())
                    .build());
    }


}

