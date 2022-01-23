package com.uni.diss_project.services;

import com.uni.diss_project.models.Message;

import java.util.List;

public interface MessagesService {

    Message send(Message message);
    List<Message> getAll(String username);
    List<Message> getAllFrom(String username, String sender);
    List<Message> getMessagesSentTo(String username, String recipient);
    List<Message> getAllSentMessages(String username);
    List<Message> getConversation(String username, String sender);

}
