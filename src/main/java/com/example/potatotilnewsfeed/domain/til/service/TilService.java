package com.example.potatotilnewsfeed.domain.til.service;

import com.example.potatotilnewsfeed.domain.til.dto.TilDto;
import com.example.potatotilnewsfeed.domain.til.dto.TilUpdateRequestDto;
import com.example.potatotilnewsfeed.domain.til.entity.Til;
import com.example.potatotilnewsfeed.domain.til.repository.TilRepository;
import com.example.potatotilnewsfeed.domain.user.entity.User;
import com.example.potatotilnewsfeed.domain.user.repository.UserRepository;
import java.time.LocalDate;
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
            .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        Til post = new Til(tilDto.getTitle(), tilDto.getContent(), user);
        return tilRepository.save(post);
    }

    public Til updateTilPost(Long tilId, TilUpdateRequestDto requestDto) {
        Til til = tilRepository.findById(tilId)
            .orElseThrow(() -> new RuntimeException("Til을 찾을 수 없습니다."));

        // 현재 날짜와 글이 작성된 날짜를 LocalDate로 변환
        LocalDate today = LocalDate.now();
        LocalDate createdAt = til.getCreatedAt().toLocalDate();

        // 날짜 비교
        if (!createdAt.equals(today)) {
            throw new RuntimeException("수정할 수 없는 날짜입니다.");
        }

        til.setTitle(requestDto.getTitle());
        til.setContent(requestDto.getContent());
        return tilRepository.save(til);
    }
}
