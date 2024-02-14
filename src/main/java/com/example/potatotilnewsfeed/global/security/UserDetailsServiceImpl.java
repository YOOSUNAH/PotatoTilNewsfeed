package com.example.potatotilnewsfeed.global.security;

import com.example.potatotilnewsfeed.domain.user.entity.Token;
import com.example.potatotilnewsfeed.domain.user.entity.User;
import com.example.potatotilnewsfeed.domain.user.repository.TokenRepository;
import com.example.potatotilnewsfeed.domain.user.repository.UserRepository;
import io.jsonwebtoken.JwtException;
import javax.security.auth.login.LoginException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByNickname(username)
            .orElseThrow(() -> new UsernameNotFoundException("Not Found " + username));

        return new UserDetailsImpl(user);
    }

    @Transactional
    public void setLoginUserByToken(String tokenValue) {
        Token token = new Token(tokenValue, false);
        tokenRepository.save(token);
    }

    public void validateToken(String bearerToken) throws LoginException {
        Token token = tokenRepository.findById(bearerToken).orElseThrow(() -> new JwtException("유효한 토큰이 아닙니다."));
        if(token.getIsExpired()){
            throw new LoginException("이미 로그아웃 처리된 토큰입니다. 다시 로그인하세요.");
        }
    }
}
