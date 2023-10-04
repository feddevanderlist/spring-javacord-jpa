package com.example.springbootjavacord.configuration;

import com.example.springbootjavacord.services.ExampleListener;
import com.example.springbootjavacord.services.ExampleSlashCommandListener;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.interaction.SlashCommandBuilder;
import org.javacord.api.interaction.SlashCommandOption;
import org.javacord.api.interaction.SlashCommandOptionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.time.LocalDateTime;
import java.util.Set;
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

    @Autowired
    private ExampleSlashCommandListener exampleSlashCommandListener;
    private final Logger LOGGER = Logger.getLogger(this.getClass().toString());

    @Autowired
    BuildProperties buildProperties;

    @Bean
    public DiscordApi discordApi() {
        String token = env.getProperty("TOKEN");
        DiscordApiBuilder builder = new DiscordApiBuilder().setToken(token);
        DiscordApi api = builder.setAllNonPrivilegedIntents().login().join();

        api.bulkOverwriteGlobalApplicationCommands(Set.of(
                new SlashCommandBuilder().setName("example").setDescription("exampleCommand").addOption(SlashCommandOption.create(SlashCommandOptionType.STRING, "message", "Your message", true))
        ));
        api.addListener(exampleListener);
        api.addListener(exampleSlashCommandListener);
        sendMessageToOwnerOnStartUp(api);
        return api;
    }

    private void sendMessageToOwnerOnStartUp(final DiscordApi api) {
        api.getOwner().ifPresent(o ->
                o.thenApply(user -> user.sendMessage(String.format("The bot with name %s and artifiact id %s with version %s and build time %s %n Has been started at %s",
                                buildProperties.getName(),
                                buildProperties.getArtifact(),
                                buildProperties.getVersion(),
                                buildProperties.getTime(),
                                LocalDateTime.now())))
                        .thenAccept(m -> LOGGER.log(Level.INFO, "The example has been successfully restarted"))
                        .exceptionally(e -> {
                                    LOGGER.log(Level.SEVERE, "Something went wrong sending a message to the owner: {0}", e.toString());
                                    return null;
                                }
                        )
        );
    }
}
