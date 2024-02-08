package com.example.potatotilnewsfeed.domain.user.service;

import com.example.potatotilnewsfeed.domain.user.dto.SignupRequestDto;
import com.example.potatotilnewsfeed.domain.user.dto.UserRequestDto;
import com.example.potatotilnewsfeed.domain.user.dto.UserResponseDto;
import com.example.potatotilnewsfeed.domain.user.entity.User;
import com.example.potatotilnewsfeed.domain.user.repository.UserRepository;
import com.example.potatotilnewsfeed.global.security.UserDetailsImpl;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(SignupRequestDto requestDto) {
        String nickname = requestDto.getNickname();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String email = requestDto.getEmail();

        validateUserDuplicate(userRepository.findByNickname(nickname));

        User user = new User(nickname, password, email);
        userRepository.save(user);
    }

    private void validateUserDuplicate(Optional<User> checkUsername) {
        if (checkUsername.isPresent()) {
            throw new DuplicateKeyException("중복된 사용자가 존재합니다.");
        }
    }

    public UserResponseDto getProfile(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return new UserResponseDto((userDetails).getUser());
    }

    @Transactional
    public UserResponseDto updateProfile(@AuthenticationPrincipal UserDetailsImpl userDetails, UserRequestDto userRequestDto) {
        // 토큰으로 id 가져오기
        Long userId = userDetails.getUser().getUserId();
        // DB에 접근
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("선택한 유저가 존재하지 않습니다."));
        // 변경
        user.setNickname(userRequestDto.getNickname());
        user.setIntroduce(userRequestDto.getIntroduce());
        return new UserResponseDto(user);
    }
}
