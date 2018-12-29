package me.brettbender.basix.listeners;

import me.brettbender.basix.Basix;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static me.brettbender.basix.helpers.ColoredStringParser.parseColoredString;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Basix.plugin.reloadConfig();
        FileConfiguration config = Basix.plugin.getConfig();
        ConfigurationSection motdConfig = config.getConfigurationSection("motd");
        boolean motdEnabled = motdConfig.getBoolean("enabled", true);
        if (motdEnabled) {
            String motdMsg = motdConfig.getString("msg", "&cConfiguration value not found!");
            motdMsg = parseColoredString(motdMsg);
            event.getPlayer().sendMessage(motdMsg);
        }

    }

}
