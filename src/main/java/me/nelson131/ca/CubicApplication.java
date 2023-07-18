package me.nelson131.ca;

import lombok.SneakyThrows;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;

import static me.nelson131.ca.utils.Config.getCFG;

public class CubicApplication extends JavaPlugin {

    public static Plugin plugin;
    private static JDA jda;

    public FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();

        try {
            jda = JDABuilder.createDefault(getCFG("bot-token"))
                    .setActivity(Activity.watching(getCFG("activity-watching")))
                    .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                    .build();
        } catch (LoginException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onDisable() {

    }

}
