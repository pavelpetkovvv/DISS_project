package com.uni.diss_project.controllers;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class ApiMessage {

    private int status;

    private String message;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> messages;

    public ApiMessage(int status, String message) {
        this.status = status;
        this.message = message;
    }

}
