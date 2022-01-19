package com.uni.diss_project.controllers;

import com.uni.diss_project.models.MessengerUser;
import com.uni.diss_project.services.AuthorizationService;
import com.uni.diss_project.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.uni.diss_project.constants.MessengerConstants.USERS_API;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(USERS_API)
public class UserController {

    private UserService userService;
    private AuthorizationService authorizationService;

    @PostMapping
    public MessengerUser register(@RequestBody MessengerUser user){

        return userService.register(user);
    }

    @DeleteMapping
    public void delete(@RequestParam String username, @RequestParam String password){

        authorizationService.authorize(username, password);
        userService.delete(username);
    }
}

