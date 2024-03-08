package com.example.potatotilnewsfeed.global;


import com.example.potatotilnewsfeed.global.exception.ExceptionDto;
import java.util.NoSuchElementException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler({IllegalArgumentException.class, BadCredentialsException.class})
    public ResponseEntity<ExceptionDto> badRequestExceptionHandler(IllegalArgumentException ex) {
        return createResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<ExceptionDto> duplicateExceptionHandler(DuplicateKeyException ex){
        return createResponse(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ExceptionDto> NoSuchElementExceptionHandler(NoSuchElementException ex){
        return createResponse(HttpStatus.NO_CONTENT, ex.getMessage());
    }

    private ResponseEntity<ExceptionDto> createResponse(HttpStatus httpStatus, String message) {
        return ResponseEntity.status(httpStatus.value())
            .body(ExceptionDto.builder().statusCode(httpStatus.value()).state(httpStatus).message(message).build());
    }
}