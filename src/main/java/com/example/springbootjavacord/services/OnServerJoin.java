package com.example.springbootjavacord.services;

import org.javacord.api.entity.channel.ServerChannel;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.event.server.ServerJoinEvent;
import org.javacord.api.listener.server.ServerJoinListener;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;

@Service
public class OnServerJoin implements ServerJoinListener {

    static final String MESSAGE =
            """
                    Your example bot has now joined the server
                     """;

    @Override
    public void onServerJoin(ServerJoinEvent event) {
        Optional<ServerTextChannel> serverChannel = event.getServer().getSystemChannel();
        if (serverChannel.isPresent()) {
            serverChannel.get().sendMessage(String.format(MESSAGE));
        } else {
            for (ServerChannel channel : event.getServer().getChannels()
            ) {
                if (channel.asTextChannel().isPresent()) {
                    TextChannel textChannel = channel.asTextChannel().get();
                    if (channel.getName().contains("chat") || channel.getName().contains("general") && textChannel.canYouWrite()) {
                        channel.asTextChannel().ifPresent(e -> e.sendMessage(String.format(MESSAGE)));
                        return;
                    }
                }
            }
            Logger.getLogger(OnServerJoin.class.toString()).log(SEVERE,"Couldn't find any channel to send the message");
        }
    }
}
