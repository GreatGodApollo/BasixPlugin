package me.brettbender.basix.handlers;

import me.brettbender.basix.Basix;
import me.brettbender.basix.commands.BasixCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandHandler {
    public static void registerCommands(JavaPlugin pl) {
        Bukkit.getLogger().info(Basix.logPrefix + "Registering commands.");
        BasixCommand.registerCommands(pl);
    }
}
