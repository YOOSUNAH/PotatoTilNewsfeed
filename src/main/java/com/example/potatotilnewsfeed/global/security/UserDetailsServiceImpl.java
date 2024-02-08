package com.example.potatotilnewsfeed.global.security;

import com.example.potatotilnewsfeed.domain.user.entity.Token;
import com.example.potatotilnewsfeed.domain.user.entity.User;
import com.example.potatotilnewsfeed.domain.user.repository.TokenRepository;
import com.example.potatotilnewsfeed.domain.user.repository.UserRepository;
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
        log.info("토큰추가!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        Token token = new Token(tokenValue, false);
        tokenRepository.save(token);
    }
}
