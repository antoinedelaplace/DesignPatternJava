package com.houvet;

import java.io.Serializable;
import java.util.ArrayList;

public class Commentable {
    private ArrayList<Message> messages = new ArrayList<>();

    public void comment(Message message) {
        this.messages.add(message);
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }
}
