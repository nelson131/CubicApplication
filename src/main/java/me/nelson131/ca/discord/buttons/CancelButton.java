package me.nelson131.ca.discord.buttons;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import static me.nelson131.ca.utils.ModalHolder.cancelModal;

public class CancelButton extends ListenerAdapter {

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event){
        String button = event.getButton().getId();
        if(button.equals("cancel")){
            event.replyModal(cancelModal()).queue();
        }
    }
}
