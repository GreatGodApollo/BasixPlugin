/*
 * Basix Bukkit Plugin
 * Copyright (C) 2019 Brett Bender
 */

package me.brettbender.basix.commands;

import me.brettbender.basix.managers.SettingsManager;
import me.brettbender.basix.managers.SuicideManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import static me.brettbender.basix.helpers.ColoredStringParser.parseColoredString;

public class SuicideCommand extends BasixCommand {


    private String pattern = "(?i)\\[player]";

    SuicideCommand() {
        super("suicide", "suicide", false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        SettingsManager settingsManager = SettingsManager.getInstance();
        SuicideManager suicideManager = SuicideManager.getInstance();
        Player senderPlayer = (Player) sender;
        FileConfiguration config = settingsManager.getConfig();
        String configMsg = config.getConfigurationSection("suicide").getString("msg", "[player]&4 took the easy way out.");
        String suicideMsg;
        suicideMsg = parseColoredString(configMsg);
        suicideMsg = suicideMsg.replaceAll(pattern, senderPlayer.getDisplayName());
        suicideManager.addSuicided(senderPlayer);
        senderPlayer.setHealth(0);
        Bukkit.broadcastMessage(suicideMsg);
    }
}
