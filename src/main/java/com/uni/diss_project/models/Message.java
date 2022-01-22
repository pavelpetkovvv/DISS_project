package com.uni.diss_project.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue
    private UUID id;

    private String content;

    private String sender;

    private String recipient;

    private boolean seen = false;

    public Message(String content, String sender, String recipient, boolean seen) {
        this.content = content;
        this.sender = sender;
        this.recipient = recipient;
        this.seen = seen;
    }

    public Message(Message m) {
        this.id = m.getId();
        this.content = m.getContent();
        this.sender = m.getSender();
        this.recipient = m.getRecipient();
        this.seen = m.isSeen();
    }
}
