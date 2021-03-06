package com.uni.diss_project.controllers;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ApiError {

    private int status;

    private String error;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> messages;

    public ApiError(int status, String message) {
        this.status = status;
        this.error = message;
    }
}