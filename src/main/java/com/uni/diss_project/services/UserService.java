package com.uni.diss_project.services;

import com.uni.diss_project.models.MessengerUser;

public interface UserService {

    public MessengerUser register(MessengerUser user);

    public void delete(String username);
}
