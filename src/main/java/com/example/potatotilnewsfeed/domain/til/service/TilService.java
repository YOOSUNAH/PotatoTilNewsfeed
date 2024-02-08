package com.example.potatotilnewsfeed.domain.til.service;

import com.example.potatotilnewsfeed.domain.til.dto.TilDto;
import com.example.potatotilnewsfeed.domain.til.dto.TilUpdateRequestDto;
import com.example.potatotilnewsfeed.domain.til.entity.Til;
import com.example.potatotilnewsfeed.domain.til.repository.TilRepository;
import com.example.potatotilnewsfeed.domain.user.entity.User;
import com.example.potatotilnewsfeed.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TilService {

    private final TilRepository tilRepository;
    private final UserRepository userRepository;

    @Autowired
    public TilService(TilRepository tilRepository, UserRepository userRepository) {
        this.tilRepository = tilRepository;
        this.userRepository = userRepository;
    }

    public Til createTilPost(TilDto tilDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 현재 로그인한 사용자의 정보
        String nickname = authentication.getName();
        User user = userRepository.findByNickname(nickname)
            .orElseThrow(() -> new RuntimeException("User not found"));

        Til post = new Til(tilDto.getTitle(), tilDto.getContent(), user);
        return tilRepository.save(post);
    }

    public Til updateTilPost(Long id, TilUpdateRequestDto requestDto) {
        Til til = tilRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("TIL not found"));

        til.setTitle(requestDto.getTitle());
        til.setContent(requestDto.getContent());

        return tilRepository.save(til);
    }
}
