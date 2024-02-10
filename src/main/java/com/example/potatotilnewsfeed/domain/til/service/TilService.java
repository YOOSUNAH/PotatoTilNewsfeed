package com.example.potatotilnewsfeed.domain.til.service;

import com.example.potatotilnewsfeed.domain.til.dto.GetTilListResponseDto;
import com.example.potatotilnewsfeed.domain.til.dto.GetTilResponseDto;
import com.example.potatotilnewsfeed.domain.til.dto.TilDto;
import com.example.potatotilnewsfeed.domain.til.dto.TilLikeResponseDto;
import com.example.potatotilnewsfeed.domain.til.dto.TilUpdateRequestDto;
import com.example.potatotilnewsfeed.domain.til.entity.Til;
import com.example.potatotilnewsfeed.domain.til.entity.TilLike;
import com.example.potatotilnewsfeed.domain.til.exception.CannotUpdateTilException;
import com.example.potatotilnewsfeed.domain.til.exception.TilNotFoundException;
import com.example.potatotilnewsfeed.domain.til.repository.TilLikeRepository;
import com.example.potatotilnewsfeed.domain.til.repository.TilRepository;
import com.example.potatotilnewsfeed.domain.user.entity.User;
import com.example.potatotilnewsfeed.domain.user.repository.UserRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TilService {

    private final TilRepository tilRepository;
    private final UserRepository userRepository;
    private final TilLikeRepository tilLikeRepository;

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
            .orElseThrow(() -> new TilNotFoundException("Til을 찾을 수 없습니다."));

        // 현재 날짜와 글이 작성된 날짜를 LocalDate로 변환
        LocalDate today = LocalDate.now();
        LocalDate createdAt = til.getCreatedAt().toLocalDate();

        // 날짜 비교
        if (!createdAt.equals(today)) {
            throw new CannotUpdateTilException("수정할 수 없는 날짜입니다.");
        }

        til.setTitle(requestDto.getTitle());
        til.setContent(requestDto.getContent());
        return tilRepository.save(til);
    }

    public void deleteTil(Long tilId) {
        tilRepository.deleteById(tilId);
    }

    public List<GetTilListResponseDto> getAllTil() {
        List<User> userList = userRepository.findAll();

        List<GetTilListResponseDto> getTilListResponseDtoList = new ArrayList<>();

        for (User user : userList) {
            getTilListResponseDtoList.add(getAllBy(user));
        }

        return getTilListResponseDtoList;
    }

    private GetTilListResponseDto getAllBy(User user) {
        return new GetTilListResponseDto(
            tilRepository.findAllByUser(user).stream().map(til -> new GetTilResponseDto(til, user))
                .toList(), user);
    }

    public GetTilResponseDto getTil(Long tilId) {
        Til til = tilRepository.findById(tilId).orElseThrow(
            () -> new NoSuchElementException(tilId + " ID를 가진 TIL을 찾을 수 없습니다.")
        );

        return new GetTilResponseDto(til, til.getUser());
    }

    public GetTilListResponseDto getTil(User user) {
        List<GetTilResponseDto> responseDtoList = tilRepository.findAllByUser(user).stream()
            .map(til -> new GetTilResponseDto(til, user)).toList();

        return new GetTilListResponseDto(responseDtoList, user);
    }

    @Transactional
    public TilLikeResponseDto likeTil(Long tilId, User user) {
        Til til = validateTil(tilId);
        
        if(tilLikeRepository.findByTilAndUser(til, user).isPresent()) {
            throw new DuplicateKeyException("이미 좋아요한 TIL 입니다.");
        }

        TilLike tilLike = new TilLike(user, til);

        tilLikeRepository.save(tilLike);

        return new TilLikeResponseDto(user.getUserId(), tilId);
    }

    public GetTilListResponseDto getLikeTil(User user) {
        List<TilLike> tilLikeList = tilLikeRepository.findAllByUser(user).orElseThrow(
            () -> new NoSuchElementException(user.getUserId() + " ID가 좋아요한 TIL을 찾을 수 없습니다.")
        );

        List<Til> tilList = new ArrayList<>();

        for (TilLike tilLike : tilLikeList) {
            Optional<Til> til = tilRepository.findById(tilLike.getTil().getId());
            til.ifPresent(tilList::add);
        }

        List<GetTilResponseDto> responseDtoList = tilList.stream()
            .map(til -> new GetTilResponseDto(til, user)).toList();

        return new GetTilListResponseDto(responseDtoList, user);
    }

    // 해당 Til Id 유효성 검증
    private Til validateTil(Long tilId) {
        return tilRepository.findById(tilId).orElseThrow(
            () -> new NoSuchElementException(tilId + " ID를 가진 TIL을 찾을 수 없습니다.")
        );
    }

    @Transactional
    public void deleteLikeTil(Long tilId, User user) {
        Til til = validateTil(tilId);

        TilLike tilLike = tilLikeRepository.findByTilAndUser(til, user).orElseThrow(
            () -> new NoSuchElementException("해당 TIL을 좋아요하지 않았습니다.")
        );

        tilLikeRepository.delete(tilLike);
    }
}
