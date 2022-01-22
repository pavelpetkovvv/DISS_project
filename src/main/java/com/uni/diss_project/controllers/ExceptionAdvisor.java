package com.uni.diss_project.controllers;

import com.uni.diss_project.exceptions.AuthenticationException;
import com.uni.diss_project.exceptions.UserAlreadyExistsException;
import com.uni.diss_project.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvisor {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException exception){
        return getResponseEntity(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handleAuthenticationException(AuthenticationException exception){
        return getResponseEntity(exception, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> handleUserAlreadyExistsException(UserAlreadyExistsException exception){
        return getResponseEntity(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> handleMissingServletRequestParameterException(MissingServletRequestParameterException exception){
        return getResponseEntity(exception, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<?> getResponseEntity(Exception exception, HttpStatus status) {
        log.warn(exception.getMessage());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        ApiError apiError = new ApiError(status.value(), exception.getMessage());

        return new ResponseEntity<>(JsonUtils.convertObjectToString(apiError), httpHeaders, status);
    }
}
