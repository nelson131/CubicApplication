package me.nelson131.ca.utils;

import static me.nelson131.ca.CubicApplication.plugin;

public class Config {

    public static String getCFG(String key){
        return plugin.getConfig().getString(key);
    }
}
