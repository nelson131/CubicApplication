package me.nelson131.ca.discord.buttons.functions;

import me.nelson131.ca.CubicApplication;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;

import java.sql.SQLException;

import static me.nelson131.ca.database.MySQL.*;
import static me.nelson131.ca.utils.Config.getCFG;
import static me.nelson131.ca.utils.MessageHolder.archiveMessage;
import static me.nelson131.ca.utils.MessageHolder.privateMessage;
import static me.nelson131.ca.utils.Roles.addRole;

public class AcceptButton extends ListenerAdapter {

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        Guild guild = event.getGuild();
        JDA jda = guild.getJDA();
        String button = event.getButton().getId();

        if (button.equals("accept")) {
            Long messageId = event.getInteraction().getMessageIdLong();

            try {
                String nickname = getNickname(messageId);
                Long userId = getUserId(messageId);

                guild.retrieveMemberById(userId).queue(member -> {
                    addRole(member, guild);
                });

                jda.retrieveUserById(userId).queue(user -> {
                    sendPrivateMessage(user);
                });

                Bukkit.getScheduler().runTask(CubicApplication.plugin, () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "easywhitelist add " + nickname));
                sendArchive(guild, nickname, userId);
                removeData(userId);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            event.getInteraction().getMessage().delete().queue();
        }
    }

    private static void sendPrivateMessage(User user){
        user.openPrivateChannel().queue((privateChannel -> {
            privateChannel.sendMessageEmbeds(privateMessage()).queue();
        }));
    }

    private static void sendArchive(Guild guild, String nickname, Long id){
        TextChannel textChannel = guild.getTextChannelById(getCFG("archive-channel-id"));
        textChannel.sendMessageEmbeds(archiveMessage(nickname, id)).queue();
    }

}

