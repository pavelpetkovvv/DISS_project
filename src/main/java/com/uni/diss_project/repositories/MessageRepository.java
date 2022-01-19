package com.uni.diss_project.repositories;

import com.uni.diss_project.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {

    List<Message> findAllByRecipient(String recipient);
    List<Message> findAllByRecipientAndSender(String recipient, String sender);
    List<Message> findAllBySender(String sender);
}
