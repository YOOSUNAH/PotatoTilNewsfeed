package com.example.potatotilnewsfeed.domain.til.controller;

import com.example.potatotilnewsfeed.domain.til.dto.TilDto;
import com.example.potatotilnewsfeed.domain.til.dto.TilResponseDto;
import com.example.potatotilnewsfeed.domain.til.entity.Til;
import com.example.potatotilnewsfeed.domain.til.service.TilService;
import com.example.potatotilnewsfeed.domain.user.entity.User;
import com.example.potatotilnewsfeed.global.dto.ResponseDto;
import com.example.potatotilnewsfeed.global.security.UserDetailsImpl;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/tils")
public class TilController {

    private final TilService tilService;

    @Autowired
    public TilController(TilService tilService) {
        this.tilService = tilService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto<Til>> createPost(@RequestBody TilDto tilDto) {
        Til post = tilService.createTilPost(tilDto);
        ResponseDto<Til> response = ResponseDto.<Til>builder()
            .data(post)
            .message("게시물 생성 성공")
            .build();
        return ResponseEntity.ok(response);
    }
}
