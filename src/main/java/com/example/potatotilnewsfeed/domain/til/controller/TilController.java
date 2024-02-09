package com.example.potatotilnewsfeed.domain.til.controller;

import com.example.potatotilnewsfeed.domain.til.dto.TilData;
import com.example.potatotilnewsfeed.domain.til.dto.TilDto;
import com.example.potatotilnewsfeed.domain.til.dto.TilResponseDto;
import com.example.potatotilnewsfeed.domain.til.dto.TilUpdateRequestDto;
import com.example.potatotilnewsfeed.domain.til.entity.Til;
import com.example.potatotilnewsfeed.domain.til.service.TilService;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TilController {

    private final TilService tilService;

    @Autowired
    public TilController(TilService tilService) {
        this.tilService = tilService;
    }

    @PostMapping("/v1/tils")
    public ResponseEntity<TilResponseDto> createTil(
        @RequestBody TilDto tilDto, HttpServletResponse response) {
        Til createdTil = tilService.createTilPost(tilDto);

        // Location 헤더 설정
        URI location = URI.create(String.format("/v1/tils/%d", createdTil.getId()));
        response.setHeader("Location", location.toString());

        TilData tilData = new TilData(
            createdTil.getId(),
            createdTil.getTitle(),
            createdTil.getContent(),
            createdTil.getUser().getNickname(),
            createdTil.getCreatedAt()
        );
        TilResponseDto responseDto = new TilResponseDto("TIL이 등록되었습니다.", List.of(tilData));

        return ResponseEntity.created(location).body(responseDto);
    }

    @PatchMapping("/v1/tils/{tilId}")
    public ResponseEntity<TilResponseDto> patchTil(
        @PathVariable("tilId") Long tilId,
        @RequestBody TilUpdateRequestDto requestDto) {

        Til updatedTil = tilService.updateTilPost(tilId, requestDto);
        TilData tilData = new TilData(
            updatedTil.getId(),
            updatedTil.getTitle(),
            updatedTil.getContent(),
            updatedTil.getUser().getNickname(),
            updatedTil.getCreatedAt()
        );

        TilResponseDto responseDto = new TilResponseDto("TIL이 수정되었습니다.", List.of(tilData));
        return ResponseEntity.ok().body(responseDto);
    }

    @DeleteMapping("v1/tils/{tilId}")
    public ResponseEntity<Void> deleteTil(@PathVariable Long tilId) {
        tilService.deleteTil(tilId);
        return ResponseEntity.noContent().build();
    }
}
