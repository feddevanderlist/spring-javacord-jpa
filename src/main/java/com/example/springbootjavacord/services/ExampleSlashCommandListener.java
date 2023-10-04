package com.example.springbootjavacord.services;

import com.example.springbootjavacord.model.ExampleModel;
import com.example.springbootjavacord.repository.ExampleRepository;
import org.javacord.api.entity.message.MessageFlag;
import org.javacord.api.event.interaction.SlashCommandCreateEvent;
import org.javacord.api.interaction.SlashCommandInteraction;
import org.javacord.api.interaction.callback.InteractionOriginalResponseUpdater;
import org.javacord.api.listener.interaction.SlashCommandCreateListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ExampleSlashCommandListener
        implements SlashCommandCreateListener {

    @Autowired
    ExampleRepository exampleRepository;

    @Override
    public void onSlashCommandCreate(final SlashCommandCreateEvent event) {
        final SlashCommandInteraction interaction = event.getSlashCommandInteraction();
        //optional check might be handy when you have more commands :)
        if (interaction.getCommandName().equals("example")) {
            CompletableFuture<InteractionOriginalResponseUpdater> cfInteractionUpdater = interaction.createImmediateResponder().setContent("this is a response to your command").setFlags(MessageFlag.EPHEMERAL).respond();

            exampleRepository.save(new ExampleModel(event.getSlashCommandInteraction().getArgumentStringRepresentationValueByName("message").orElseThrow(NullPointerException::new)));
            cfInteractionUpdater.thenAccept(interactionOriginalResponseUpdater -> interactionOriginalResponseUpdater.setContent("message update: your message has been saved tot he database").update());
        }
    }
}
