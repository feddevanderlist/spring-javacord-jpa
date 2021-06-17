package com.example.springbootjavacord.configuration;

import com.example.springbootjavacord.services.ExampleListener;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
public class DiscordApiConfiguration {
    @Autowired
    private Environment env;
    /* To add your listener use the code below.
     * REMEMBER TO SET AN @SERVICE OR COMPONENT IN THE LISTENER you made */
    @Autowired
    private ExampleListener exampleListener;

    @Bean
    public DiscordApi discordApi() {
        String token = env.getProperty("TOKEN");
        Logger.getLogger("SchedulerApplication").log(Level.INFO, token);
        DiscordApiBuilder builder = new DiscordApiBuilder().setToken(token);
        DiscordApi api = builder.setAllNonPrivilegedIntents().login().join();
        api.addListener(exampleListener);
        return api;
    }
}
