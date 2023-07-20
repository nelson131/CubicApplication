package me.nelson131.ca.utils;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public static MessageEmbed privateMessage(){
        EmbedBuilder ed = new EmbedBuilder();
        ed.setColor(main);
        ed.setTitle(getCFG("private-title"));
        ed.setDescription(getCFG("private-desc"));
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

    public static MessageEmbed archiveMessage(String nickname, Long id){
        EmbedBuilder ed = new EmbedBuilder();
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime time = LocalDateTime.now();
        ed.setColor(main);
        ed.setTitle(getCFG("arc-title"));
        ed.addField(getCFG("arc-nickname-title"), nickname, false);
        ed.addField(getCFG("arc-id-title"), String.valueOf(id), false);
        ed.addField(getCFG("arc-date"), date.format(time), false);
        return ed.build();
    }

    public static MessageEmbed alreadyRoleMessage(){
        EmbedBuilder ed = new EmbedBuilder();
        ed.setColor(error);
        ed.setTitle(getCFG("already-role"));
        return ed.build();
    }
}
