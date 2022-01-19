package com.uni.diss_project.services;

import com.uni.diss_project.exceptions.AuthenticationException;
import com.uni.diss_project.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@AllArgsConstructor
@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    private UserRepository userRepository;

    @Override
    public boolean authorize(String username, String password) {

        if(!userRepository.existsByUsername(username)){

            log.warn("User with username: " + username + "does not exist");
            throw new AuthenticationException("Incorrect username or password");
        }

        if(!userRepository.findByUsername(username).getPassword().equals(password)){

            log.warn("Incorrect password for User with username: " + username);
            throw new AuthenticationException("Incorrect username or password");
        }

        return true;
    }
}
