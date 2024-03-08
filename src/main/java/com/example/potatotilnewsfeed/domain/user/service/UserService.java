package com.example.potatotilnewsfeed.domain.user.service;

import com.example.potatotilnewsfeed.domain.user.dto.SignupRequestDto;
import com.example.potatotilnewsfeed.domain.user.dto.UserRequestDto;
import com.example.potatotilnewsfeed.domain.user.dto.UserResponseDto;
import com.example.potatotilnewsfeed.global.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {


    void signup(SignupRequestDto requestDto);

    void logout(String accessToken);

    @Transactional
    UserResponseDto updateUser(@AuthenticationPrincipal UserDetailsImpl userDetails,
        UserRequestDto userRequestDto);

    @Transactional
    UserResponseDto updatePassword(UserDetailsImpl userDetails, UserRequestDto userRequestDto);

    void deleteUser(UserDetailsImpl userDetails, UserRequestDto userRequestDto);

}
