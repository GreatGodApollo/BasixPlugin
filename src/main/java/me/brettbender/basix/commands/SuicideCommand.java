package me.brettbender.basix.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SuicideCommand extends BasixCommand {

    SuicideCommand() {
        super("suicide", "suicide", false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player senderPlayer = (Player) sender;
        senderPlayer.setHealth(0);
    }
}
