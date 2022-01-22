package com.uni.diss_project.services;

import com.uni.diss_project.exceptions.UserAlreadyExistsException;
import com.uni.diss_project.models.MessengerUser;
import com.uni.diss_project.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Override
    public MessengerUser register(MessengerUser user) {
        if(userRepository.existsByUsername(user.getUsername())){
            throw new UserAlreadyExistsException("Username already exists, Please choose another username");
        }
        return userRepository.saveAndFlush(user);
    }

    @Override
    @Transactional
    public void delete(String username) {
        userRepository.deleteByUsername(username);
    }
}
