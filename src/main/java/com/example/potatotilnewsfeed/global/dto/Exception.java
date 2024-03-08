package com.example.potatotilnewsfeed.global.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

public class Exception {

    public static class DuplicatePasswordException extends RuntimeException {

        public DuplicatePasswordException(String message) {
        super(message);
        }


    }

    @Getter
    @Setter
    @Builder
    public static class ExceptionDto {

        private int statusCode;
        private HttpStatus state;
        private String message;

    }

    public static class NotFoundException extends RuntimeException {

        public NotFoundException(String message) {
            super(message);
        }
    }
}
