package com.example.potatotilnewsfeed.domain.til.repository;

import com.example.potatotilnewsfeed.domain.til.entity.Til;
import com.example.potatotilnewsfeed.domain.user.entity.User;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TilRepository extends JpaRepository<Til, Long> {

    List<Til> findAllByUser(User user);



    Page<Til> findAll(Pageable pageable);

//    // 1. PageRequest 객체 생성
//    Pageable pageRequest = PageRequest.of(0, 10); // 페이지 번호 : 0, 페이지당 크기 : 10
//    // 2. Repository에 Pageable 전달
//    Page<Til> tils = tilRepository.findByEmail(10, pageRequest);
//
//    // 3. 결과 사용
//    Page<TilResponseDto> tilDtos = page.map(m -> new TilResponseDto(m.getMessage(),m.getData()));
//
//    int totalPages = tilDtos.getTotalPages(); // 총 페이지 수 얻기
//    int pageNumber = tilDtos.getNumber(); // 현재 페이지 번호 얻기
//    List<TilResponseDto> tilList = tilDtos.getContent(); // 조회된 데이터 얻기
}
