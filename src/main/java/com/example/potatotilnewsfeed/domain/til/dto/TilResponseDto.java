package com.example.potatotilnewsfeed.domain.til.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TilResponseDto {

    private String message;
    private List<TilData> data;

    public TilResponseDto(String message, List<TilData> data) {
        this.message = message;
        this.data = data;
    }

    public static class TilData {

        private Long tilId;
        private String title;
        private String content;
        private String nickname;
        private LocalDateTime createdAt;

        @JsonCreator
        public TilData(@JsonProperty("tilId") Long tilId,
            @JsonProperty("title") String title,
            @JsonProperty("content") String content,
            @JsonProperty("nickname") String nickname,
            @JsonProperty("createdAt") LocalDateTime createdAt) {
            this.tilId = tilId;
            this.title = title;
            this.content = content;
            this.nickname = nickname;
            this.createdAt = createdAt;
        }

    }

}
