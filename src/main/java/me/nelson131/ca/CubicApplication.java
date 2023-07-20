package me.nelson131.ca;

import lombok.SneakyThrows;
import me.nelson131.ca.discord.ApplicationCommand;
import me.nelson131.ca.discord.buttons.AcceptButton;
import me.nelson131.ca.discord.buttons.CancelButton;
import me.nelson131.ca.discord.buttons.CreateButton;
import me.nelson131.ca.discord.modals.CancelModal;
import me.nelson131.ca.discord.modals.CreateModal;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;

import java.util.ArrayList;
import java.util.List;

import static me.nelson131.ca.utils.Config.getCFG;

public class CubicApplication extends JavaPlugin {

    public static Plugin plugin;
    private static JDA jda;

    public FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();

        jda = JDABuilder.createDefault(getCFG("bot-token"))
                .setActivity(Activity.watching(getCFG("activity-watching")))
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(new ApplicationCommand(), new CreateButton(), new CreateModal(), new AcceptButton(), new CancelButton(), new CancelModal())
                .build();

    }

    @Override
    public void onDisable() {

    }

}
