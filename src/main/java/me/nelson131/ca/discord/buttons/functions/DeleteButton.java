package me.nelson131.ca.discord.buttons.functions;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.sql.SQLException;

import static me.nelson131.ca.database.MySQL.getUserId;
import static me.nelson131.ca.database.MySQL.removeData;

public class DeleteButton extends ListenerAdapter {

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event){
        String button = event.getButton().getId();

        if(button.equals("delete")){
            Message message = event.getInteraction().getMessage();
            Long messageId = message.getIdLong();

            try {
                Long userId = getUserId(messageId);
                removeData(userId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            message.delete().queue();
        }
    }
}
