package me.nelson131.ca.discord.buttons;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import static me.nelson131.ca.utils.ModalHolder.createModal;

public class CreateButton extends ListenerAdapter {

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event){
        String buttonId = event.getButton().getId();
        if(buttonId.equals("app")){
            event.replyModal(createModal()).queue();
        }
    }
}
