package com.uni.diss_project.services;

import com.uni.diss_project.models.Message;
import com.uni.diss_project.repositories.MessageRepository;
import com.uni.diss_project.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
        return messageRepository.findAllByRecipient(recipient);
    }

    @Override
    public List<Message> getAllFrom(String recipient, String sender) {
        return messageRepository.findAllByRecipientAndSender(recipient, sender);
    }
}
