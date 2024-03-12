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
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
            List<GetTilResponseDto> getTilResponseDtoList = new ArrayList<>();
            List<Til> tilList = tilRepository.findAllByUser(user);
            int likes = 0;

            for (Til til : tilList) {
                likes = getLikes(til);

                getTilResponseDtoList.add(new GetTilResponseDto(til, user, likes));
            }

            getTilListResponseDtoList.add(new GetTilListResponseDto(getTilResponseDtoList, user));
        }

        return getTilListResponseDtoList;
    }

    public GetTilResponseDto getTil(Long tilId) {
        Til til = tilRepository.findById(tilId).orElseThrow(
            () -> new NoSuchElementException(tilId + " ID를 가진 TIL을 찾을 수 없습니다.")
        );

        int likes = getLikes(til);

        return new GetTilResponseDto(til, til.getUser(), likes);
    }

    public GetTilListResponseDto getTil(User user) {
        List<GetTilResponseDto> responseDtoList = new ArrayList<>();
        List<Til> tilList = tilRepository.findAllByUser(user);
        int likes = 0;

        for (Til til : tilList) {
            likes = getLikes(til);

            responseDtoList.add(new GetTilResponseDto(til, user, likes));
        }

        return new GetTilListResponseDto(responseDtoList, user);
    }

    private int getLikes(Til til) {
        List<TilLike> tilLikeList = tilLikeRepository.findAllByTil(til);

        if (tilLikeList != null && !tilLikeList.isEmpty()) {
            return tilLikeList.size();
        } else {
            return 0;
        }
    }

    @Transactional
    public TilLikeResponseDto likeTil(Long tilId, User user) {
        Til til = validateTil(tilId);

        if (tilLikeRepository.findByTilAndUser(til, user).isPresent()) {
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

        List<GetTilResponseDto> responseDtoList = new ArrayList<>();

        for (Til til : tilList) {
            int likes = getLikes(til);

            responseDtoList.add(new GetTilResponseDto(til, user, likes));
        }

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

    public Page<GetTilListResponseDto> getAllTilPages(int pageNo, String criteria, String sort) {
        Pageable pageable =
            (sort.equals("ASC"))
                ? PageRequest.of(pageNo, 5, Sort.by(Sort.Direction.ASC, criteria))
                : PageRequest.of(pageNo, 5, Sort.by(Direction.DESC, criteria));

        Page<Til> tilPage = tilRepository.findAll(pageable);

        List<GetTilListResponseDto> getTilListResponseDtoList = new ArrayList<>();

        for (Til til : tilPage.getContent()) {
            User user = til.getUser();
            int likes = getLikes(til);
            GetTilResponseDto getTilResponseDto = new GetTilResponseDto(til, user, likes);

            List<GetTilResponseDto> getTilResponseDtoList = new ArrayList<>();
            getTilResponseDtoList.add(getTilResponseDto);

            GetTilListResponseDto getTilListResponseDto = new GetTilListResponseDto(
                getTilResponseDtoList, user);
            getTilListResponseDtoList.add(getTilListResponseDto);
        }

        return new PageImpl<>(getTilListResponseDtoList, pageable, tilPage.getTotalElements());
    }

    public List<GetTilResponseDto> getAllTilPage(int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size);

        return tilLikeRepository.getTilListWithPage(pageRequest.getOffset(),
                pageRequest.getPageSize())
            .stream()
            .map(m -> {
                int likes = getLikes(m);
                return GetTilResponseDto.builder()
                    .til(m)
                    .user(m.getUser())
                    .likes(likes) // 수정된 부분
                    .build();
            })
            .collect(Collectors.toList());
    }


    public List<GetTilResponseDto> getAllTilPageAndSortCreateAtDesc(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return tilLikeRepository.getAllTilPageAndSortCreateAtDesc(pageRequest.getOffset(),
                pageRequest.getPageSize())
            .stream()
            .map(m -> {
                int likes = getLikes(m);
                return GetTilResponseDto.builder()
                    .til(m)
                    .user(m.getUser())
                    .likes(likes) // 수정된 부분
                    .build();
            })
            .collect(Collectors.toList());
    }
}