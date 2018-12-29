package me.brettbender.basix.handlers;

import me.brettbender.basix.listeners.PlayerJoinListener;
import org.bukkit.plugin.java.JavaPlugin;

public class ListenerHandler {

    public static void registerListeners(JavaPlugin plugin){
        plugin.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), plugin);
    }

}
