package com.uni.diss_project.controllers;

import com.uni.diss_project.dto.MessageDTO;
import com.uni.diss_project.models.Message;
import com.uni.diss_project.services.AuthorizationService;
import com.uni.diss_project.services.MessagesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

import static com.uni.diss_project.constants.MessengerConstants.MESSAGES_API;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(MESSAGES_API)
public class MessageController {

    private MessagesService messagesService;
    private AuthorizationService authorizationService;

    @PostMapping(consumes = "application/json")
    public Message send(@RequestParam String username, @RequestParam String password, @RequestBody MessageDTO messageDTO) {

        authorizationService.authorize(username, password);

        Message message = new Message();
        message.setSender(username);
        message.setSent(new Date(System.currentTimeMillis()));
        BeanUtils.copyProperties(messageDTO, message);
        return messagesService.send(message);
    }

    @GetMapping("/received")
    public List<Message> getAll(@RequestParam String username, @RequestParam String password) {

        authorizationService.authorize(username, password);
        return messagesService.getAll(username);
    }

    @GetMapping("/received/{sender}")
    public List<Message> getAllFrom(@RequestParam String username, @RequestParam String password, @PathVariable String sender) {

        authorizationService.authorize(username, password);
        return messagesService.getAllFrom(username, sender);
    }

    @GetMapping("/sent")
    public List<Message> getAllSentMessage(@RequestParam String username, @RequestParam String password) {

        authorizationService.authorize(username, password);
        return messagesService.getAllSentMessages(username);
    }

    @GetMapping("/sent/{recipient}")
    public List<Message> getAllMessageSentTo(@RequestParam String username, @RequestParam String password, @PathVariable String recipient) {

        authorizationService.authorize(username, password);
        return messagesService.getMessagesSentTo(username, recipient);
    }

    @GetMapping("/conversation/{sender}")
    public List<Message> getConversation(@RequestParam String username, @RequestParam String password, @PathVariable String sender) {

        authorizationService.authorize(username, password);
        return messagesService.getConversation(username, sender);
    }

    @GetMapping("/contacts")
    public List<String> getContacts(@RequestParam String username, @RequestParam String password) {

        authorizationService.authorize(username, password);
        return messagesService.getContacts(username);
    }
}
