package me.brettbender.basix.helpers;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.Optional;

public class permissionCheck {


    /**
     * @param sender Sender of the command
     * @param permToCheck Permission to check
     * @param eColor Error color
     * @param sMessage Send error message
     * @return Ability of user to run the command
     */
    public static boolean checkPermission(CommandSender sender, String permToCheck, Boolean sMessage, ChatColor eColor) {
        ChatColor errorColor = eColor;
        if (sender.hasPermission("basix." + permToCheck)) {
            return true;
        } else {
            if (sMessage) {
                sender.sendMessage(errorColor + "You are lacking permissions to use this command");
            }
            return false;
        }
    }
}
