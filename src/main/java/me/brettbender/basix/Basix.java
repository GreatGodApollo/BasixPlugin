package me.brettbender.basix;

import me.brettbender.basix.commands.GameModeCommand;
import me.brettbender.basix.handlers.CommandHandler;
import me.brettbender.basix.handlers.ConfigHandler;
import me.brettbender.basix.handlers.ListenerHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Basix extends JavaPlugin {

    public static String logPrefix = "[Basix] ";
    public static ChatColor errorColor = ChatColor.RED;
    public static ChatColor successColor = ChatColor.GREEN;
    public static JavaPlugin plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        CommandHandler.registerCommands(plugin);
        ConfigHandler.initialize(plugin);
        ListenerHandler.registerListeners(plugin);
        Bukkit.getLogger().info(logPrefix + "Plugin Loaded");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
