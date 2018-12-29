package me.brettbender.basix.handlers;

import me.brettbender.basix.Basix;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigHandler {
    public static void initialize(JavaPlugin plugin) {
        Bukkit.getLogger().info(Basix.logPrefix + "Setting up config.");
        plugin.saveDefaultConfig();
    }
}
