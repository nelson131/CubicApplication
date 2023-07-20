package me.nelson131.ca.discord.modals;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.sql.SQLException;

import static me.nelson131.ca.database.MySQL.getUserId;
import static me.nelson131.ca.database.MySQL.removeData;
import static me.nelson131.ca.utils.Config.getCFG;
import static me.nelson131.ca.utils.MessageHolder.privateMessage;
import static me.nelson131.ca.utils.MessageHolder.reasonMessage;

public class CancelModal extends ListenerAdapter {

    @Override
    public void onModalInteraction(ModalInteractionEvent event){
        Guild guild = event.getGuild();
        JDA jda = guild.getJDA();
        String modal = event.getModalId();

        if(modal.equals("cancel")){
            String reason = event.getValue("reason").getAsString();
            String author = event.getInteraction().getMember().getEffectiveName();
            long messageId = event.getInteraction().getMessage().getIdLong();

            try {
                long userId = getUserId(messageId);
                jda.retrieveUserById(userId).queue((user -> {
                    sendPrivateMessage(user, reason, author);
                }));

                removeData(userId);
                event.getInteraction().getMessage().delete().queue();
                event.reply(getCFG("cancel-message")).queue();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void sendPrivateMessage(User user, String reason, String author){
        user.openPrivateChannel().queue((privateChannel -> {
            privateChannel.sendMessageEmbeds(reasonMessage(reason, author)).queue();
        }));
    }
}
