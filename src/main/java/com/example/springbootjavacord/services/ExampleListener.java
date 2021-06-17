package com.example.springbootjavacord.services;

import com.example.springbootjavacord.model.ExampleModel;
import com.example.springbootjavacord.repository.ExampleRepository;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExampleListener implements MessageCreateListener {
    @Autowired
    ExampleRepository exampleRepository;

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if (event.getMessageContent().equalsIgnoreCase("!ping")) {
            event.getChannel().sendMessage("Pong!");
        } else if (event.getMessageContent().toLowerCase().contains("!save")) {
            exampleRepository.save(new ExampleModel(event.getMessageContent()));
        }
    }
}
