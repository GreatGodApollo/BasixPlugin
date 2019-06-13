/*
 * Basix Bukkit Plugin
 * Copyright (C) 2019 Brett Bender
 */

package me.brettbender.basix;

import me.brettbender.basix.handlers.CommandHandler;
import me.brettbender.basix.handlers.ListenerHandler;
import me.brettbender.basix.managers.SettingsManager;
import me.brettbender.basix.managers.SuicideManager;
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
        SettingsManager.getInstance().setup(plugin);
        SuicideManager.getInstance().setup(plugin);
        ListenerHandler.registerListeners(plugin);
        Bukkit.getLogger().info(logPrefix + "Plugin Loaded");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
