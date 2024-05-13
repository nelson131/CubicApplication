package me.nelson131.ca.discord.buttons;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import static me.nelson131.ca.utils.MessageHolder.alreadyRoleMessage;
import static me.nelson131.ca.utils.ModalHolder.createModal;
import static me.nelson131.ca.utils.Roles.getRole;

public class CreateButton extends ListenerAdapter {

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event){
        String buttonId = event.getButton().getId();
        Member member = event.getMember();
        Guild guild = event.getGuild();

        if(buttonId.equals("app")){
            if(getRole(member, guild)){
                event.replyEmbeds(alreadyRoleMessage()).setEphemeral(true).queue();
                return;
            }

            event.replyModal(createModal()).queue();
        }
    }
}
