package com.uni.diss_project.services;

import com.uni.diss_project.models.Message;

import java.util.List;

public interface MessagesService {

    public Message send(Message message);
    public List<Message> getAll(String username);
    public List<Message> getAllFrom(String username, String sender);

}
