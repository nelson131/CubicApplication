package me.nelson131.ca.discord;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import static me.nelson131.ca.utils.Config.getCFG;
import static me.nelson131.ca.utils.MessageHolder.applicationMessage;

public class ApplicationCommand extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        String message = event.getMessage().getContentRaw();
        if(message.equals("!application")){
            Button button = Button.success("app", getCFG("button-app"));

            event.getChannel().sendMessageEmbeds(applicationMessage())
                    .addActionRow(button)
                    .queue();;
        }
    }
}
