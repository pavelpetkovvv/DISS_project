package com.uni.diss_project.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class Message {

    @Id
    @GeneratedValue
    private UUID id;

    private String content;

    private String sender;

    private String recipient;

    private boolean seen = false;

    public Message(Message m) {

    }
}
