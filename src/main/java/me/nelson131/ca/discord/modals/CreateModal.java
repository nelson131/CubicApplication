package me.nelson131.ca.discord.modals;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.sql.SQLException;

import static me.nelson131.ca.database.MySQL.addData;
import static me.nelson131.ca.utils.Config.getCFG;
import static me.nelson131.ca.utils.MessageHolder.directMessage;
import static me.nelson131.ca.utils.MessageHolder.openRequestMessage;

public class CreateModal extends ListenerAdapter {

    @Override
    public void onModalInteraction(ModalInteractionEvent event) {

        if (event.getModalId().equals("create")) {
            Guild guild = event.getGuild();

            String nickname = event.getValue("nickname").getAsString();
            String byWhere = event.getValue("byWhere").getAsString();
            String rulesAccepted = event.getValue("rulesAccepted").getAsString();
            String plans = event.getValue("plans").getAsString();
            String reg = event.getValue("reg").getAsString();

            Long userId = event.getInteraction().getUser().getIdLong();
            String userMention = event.getUser().getAsMention();

            TextChannel textChannel = guild.getTextChannelById(getCFG("staff-channel-id"));

            Button accept = Button.success("accept", getCFG("button-accept"));
            Button cancel = Button.primary("cancel", getCFG("button-cancel"));
            Button delete = Button.danger("delete", getCFG("button-delete"));

            textChannel.sendMessageEmbeds(openRequestMessage(nickname, byWhere, rulesAccepted, plans, reg, userMention))
                    .addActionRow(accept)
                    .addActionRow(cancel)
                    .addActionRow(delete)
                    .queue(message -> {
                        try {
                            addData(userId, message.getIdLong(), nickname);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    });

            event.replyEmbeds(directMessage()).setEphemeral(true).queue();

        }
    }

}
