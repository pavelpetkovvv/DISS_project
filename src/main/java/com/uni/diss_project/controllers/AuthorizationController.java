package com.uni.diss_project.controllers;

import com.uni.diss_project.services.AuthorizationService;
import com.uni.diss_project.utils.JsonUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.uni.diss_project.constants.MessengerConstants.AUTHORIZATION_API;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(AUTHORIZATION_API)
public class AuthorizationController {

    private AuthorizationService authorizationService;

    @GetMapping
    public ResponseEntity<?> authorize(@RequestParam String username, @RequestParam String password) {
        authorizationService.authorize(username, password);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        ApiMessage apiMessage = new ApiMessage(HttpStatus.OK.value(), "Authorization successful");

        return new ResponseEntity<>(JsonUtils.convertObjectToString(apiMessage), httpHeaders, HttpStatus.OK);
    }

}
