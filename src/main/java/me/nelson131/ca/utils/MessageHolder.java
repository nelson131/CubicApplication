package me.nelson131.ca.utils;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

import static me.nelson131.ca.utils.Config.getCFG;

public class MessageHolder {

    public static final Color main = new Color(45, 128, 15);
    public static final Color error = new Color(255, 0, 0);

    public static MessageEmbed applicationMessage(){
        EmbedBuilder ed = new EmbedBuilder();
        ed.setColor(main);
        ed.setTitle(getCFG("app-title"));
        ed.setDescription(getCFG("app-desc"));
        ed.addField(getCFG("app-field-title"), getCFG("app-field"), false);
        ed.setImage(getCFG("app-image"));
        return ed.build();
    }

    public static MessageEmbed directMessage(){
        EmbedBuilder ed = new EmbedBuilder();
        ed.setColor(main);
        ed.setTitle(getCFG("direct-title"));
        ed.setDescription(getCFG("direct-desc"));
        return ed.build();
    }

    public static MessageEmbed openRequestMessage(String nickname, String byWhere, String rulesAccepted, String plans, String reg, String mention){
        EmbedBuilder ed = new EmbedBuilder();
        ed.setColor(main);
        ed.setTitle(getCFG("req-title"));
        ed.addField(getCFG("req-nickname"), nickname, false);
        ed.addField(getCFG("req-byWhere"), byWhere, false);
        ed.addField(getCFG("req-rules"), rulesAccepted, false);
        ed.addField(getCFG("req-plans"), plans, false);
        ed.addField(getCFG("req-reg"), reg, false);
        ed.setFooter(getCFG("req-id") + " " + mention);
        return ed.build();
    }
}
