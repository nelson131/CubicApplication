package me.nelson131.ca;

import me.nelson131.ca.discord.ApplicationCommand;
import me.nelson131.ca.discord.buttons.functions.AcceptButton;
import me.nelson131.ca.discord.buttons.functions.CancelButton;
import me.nelson131.ca.discord.buttons.CreateButton;
import me.nelson131.ca.discord.buttons.functions.DeleteButton;
import me.nelson131.ca.discord.modals.CancelModal;
import me.nelson131.ca.discord.modals.CreateModal;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

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
                .addEventListeners(new ApplicationCommand(), new CreateButton(), new CreateModal(), new AcceptButton(), new CancelButton(), new CancelModal(), new DeleteButton())
                .build();

    }

    @Override
    public void onDisable() {

    }

}
