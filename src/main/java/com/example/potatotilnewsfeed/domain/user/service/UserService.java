package com.example.potatotilnewsfeed.domain.user.service;

import com.example.potatotilnewsfeed.domain.user.dto.LoginRequestDto;
import com.example.potatotilnewsfeed.domain.user.entity.User;
import com.example.potatotilnewsfeed.domain.user.repository.UserRepository;
import com.example.potatotilnewsfeed.domain.user.dto.SignupRequestDto;
import com.example.potatotilnewsfeed.global.jwt.JwtUtil;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

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

    public String login(LoginRequestDto requestDto) {
        String userName = requestDto.getNickname();
        String password = requestDto.getPassword();

        User user = userBy(userName);
        validatePassword(user, password);

        return jwtUtil.createToken(user.getNickname());
    }

    private void validatePassword(User user, String password) {
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("패스워드를 잘못 입력하였습니다.");
        }
    }

    public User userBy(String nickname) {
        return userRepository.findByNickname(nickname).orElseThrow(
            () -> new NoSuchElementException("사용자를 찾을 수 없습니다.")
        );
    }
}
