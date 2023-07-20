package me.nelson131.ca.utils;

import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;

import static me.nelson131.ca.CubicApplication.plugin;
import static me.nelson131.ca.utils.Config.getCFG;

public class ModalHolder {

    public static Modal createModal(){
        TextInput nickname = TextInput.create("nickname", getCFG("create-nickname"), TextInputStyle.SHORT)
                .setPlaceholder(getCFG("create-nickname-pl"))
                .setRequired(true)
                .build();

        TextInput byWhere = TextInput.create("byWhere", getCFG("create-where"), TextInputStyle.SHORT)
                .setPlaceholder(getCFG("create-where-pl"))
                .setRequired(true)
                .build();

        TextInput rulesAccepted = TextInput.create("rulesAccepted", getCFG("create-rules"), TextInputStyle.SHORT)
                .setPlaceholder(getCFG("create-rules-pl"))
                .setRequired(true)
                .build();

        TextInput plans = TextInput.create("plans", getCFG("create-plans"), TextInputStyle.PARAGRAPH)
                .setPlaceholder(getCFG("create-plans-pl"))
                .setMinLength(10)
                .setMaxLength(100)
                .build();

        TextInput registration = TextInput.create("reg", getCFG("create-reg"), TextInputStyle.SHORT)
                .setPlaceholder(getCFG("create-reg-pl"))
                .setRequired(true)
                .build();

        return Modal.create("create", getCFG("create-title"))
                .addActionRows(ActionRow.of(nickname), ActionRow.of(byWhere), ActionRow.of(rulesAccepted), ActionRow.of(plans), ActionRow.of(registration))
                .build();
    }

    public static Modal cancelModal(){
        TextInput reason = TextInput.create("reason", getCFG("cancel-reason"), TextInputStyle.PARAGRAPH)
                .setPlaceholder(getCFG("cancel-reason-pl"))
                .setRequired(true)
                .build();

        return Modal.create("cancel", getCFG("cancel-title"))
                .addActionRow(reason)
                .build();
    }
}
