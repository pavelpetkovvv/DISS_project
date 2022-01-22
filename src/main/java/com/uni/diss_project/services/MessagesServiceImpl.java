package com.uni.diss_project.services;

import com.uni.diss_project.models.Message;
import com.uni.diss_project.repositories.MessageRepository;
import com.uni.diss_project.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MessagesServiceImpl implements MessagesService{

    private MessageRepository messageRepository;
    private UserRepository userRepository;

    @Override
    public Message send(Message message) {
        if(!userRepository.existsByUsername(message.getRecipient())) {
            throw new EntityNotFoundException("Recipient does not exist");
        }
        return messageRepository.saveAndFlush(message);
    }

    @Override
    public List<Message> getAll(String recipient) {

        List<Message> messages = messageRepository.findAllByRecipient(recipient);
        List<Message> copy = new ArrayList<>();

        for (Message m: messages) {
            Message newMessage = new Message(m);
            copy.add(newMessage);
        }

        messages.forEach(m -> m.setSeen(true));
        messageRepository.saveAll(messages);
        return copy;
    }

    @Override
    public List<Message> getAllFrom(String recipient, String sender) {
        List<Message> messages =  messageRepository.findAllByRecipientAndSender(recipient, sender);
        List<Message> copy = new ArrayList<>(messages);

        for (Message m: messages) {
            Message newMessage = new Message(m);
            copy.add(newMessage);
        }

        messages.forEach(m -> m.setSeen(true));
        messageRepository.saveAll(messages);
        return copy;
    }

    @Override
    public List<Message> getMessagesSentTo(String username, String recipient) {
        return messageRepository.findAllByRecipientAndSender(recipient, username);
    }

    @Override
    public List<Message> getAllSentMessages(String username) {
        return messageRepository.findAllBySender(username);
    }
}
