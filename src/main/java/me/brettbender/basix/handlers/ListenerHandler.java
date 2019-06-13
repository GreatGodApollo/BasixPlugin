/*
 * Basix Bukkit Plugin
 * Copyright (C) 2019 Brett Bender
 */

package me.brettbender.basix.handlers;

import me.brettbender.basix.listeners.PlayerDeathListener;
import me.brettbender.basix.listeners.PlayerJoinListener;
import org.bukkit.plugin.java.JavaPlugin;

public class ListenerHandler {

    public static void registerListeners(JavaPlugin plugin){
        plugin.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new PlayerDeathListener(), plugin);
    }

}
