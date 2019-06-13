/*
 * Basix Bukkit Plugin
 * Copyright (C) 2019 Brett Bender
 */

package me.brettbender.basix.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand extends BasixCommand {

    FlyCommand() {
        super("fly", "fly", false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player senderPlayer = (Player) sender;
        boolean opposite = !senderPlayer.getAllowFlight();
        String msg = opposite ? "Flight mode enabled" : "Flight mode disabled";
        senderPlayer.setAllowFlight(opposite);
        sender.sendMessage(BasixCommand.successColor + msg);
    }
}
