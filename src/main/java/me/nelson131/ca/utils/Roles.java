package me.nelson131.ca.utils;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

import static me.nelson131.ca.utils.Config.getCFG;

public class Roles {

    public static void addRole(Member member, Guild guild){
        Role role = guild.getRoleById(getCFG("newbee-role-id"));
        guild.addRoleToMember(member, role).queue();
    }

    public static void removeRole(Member member, Guild guild){
        Role role = guild.getRoleById(getCFG("newbee-role-id"));
        guild.removeRoleFromMember(member, role).queue();
    }

    public static boolean getRole(Member member, Guild guild){
        Role role = guild.getRoleById(getCFG("newbee-role-id"));
        return member.getRoles().contains(role);
    }
}
