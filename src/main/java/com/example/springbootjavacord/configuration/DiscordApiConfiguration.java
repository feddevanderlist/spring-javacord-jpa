package com.example.springbootjavacord.configuration;

import com.example.springbootjavacord.services.ExampleListener;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
@PropertySource("classpath:application.yaml")
public class DiscordApiConfiguration {
    @Autowired
    private Environment env;
    /* To add your listener use the code below.
     * REMEMBER TO SET AN @SERVICE OR COMPONENT IN THE LISTENER you made */
    @Autowired
    private ExampleListener exampleListener;

    private final Logger LOGGER = Logger.getLogger(this.getClass().toString());

    @Bean
    public DiscordApi discordApi() {
        String token = env.getProperty("TOKEN");
        DiscordApiBuilder builder = new DiscordApiBuilder().setToken(token);
        DiscordApi api = builder.setAllNonPrivilegedIntents().login().join();
        api.addListener(exampleListener);
        sendMessageToOwnerOnStartUp(api);
        return api;
    }

    private void sendMessageToOwnerOnStartUp(final DiscordApi api) {
        api.getOwner().ifPresent(o ->
                o.thenApply(user -> user.sendMessage("The example bot has been successfully restarted"))
                        .thenAccept(m -> LOGGER.log(Level.INFO, "The example has been successfully restarted"))
                        .exceptionally(e -> {
                                    LOGGER.log(Level.SEVERE, "Something went wrong sending a message to the owner: {0}", e.toString());
                                    return null;
                                }
                        )
        );
    }
}
