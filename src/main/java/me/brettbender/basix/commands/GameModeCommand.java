package me.brettbender.basix.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import static me.brettbender.basix.helpers.permissionCheck.checkPermission;

public class GameModeCommand extends BasixCommand {

    private final ChatColor errorColor;
    private final ChatColor successColor;

    public GameModeCommand() {
        super("gamemode", true);
        this.successColor = BasixCommand.successColor;
        this.errorColor = BasixCommand.errorColor;
    }

    public void execute(final CommandSender sender, final String[] args) {
        if (checkPermission(sender, "gamemode", true, errorColor)) {
            Player target;
            if (args.length == 0) {
                sender.sendMessage(errorColor + "Please provide a gamemode to change to!");
                return;
            } else if (args.length == 1) {
                target = Bukkit.getPlayer(sender.getName());
                if (sender instanceof ConsoleCommandSender) {
                    sender.sendMessage(errorColor + "You must use a player name in this context");
                    return;
                }
            } else if (args.length == 2) {
                target = Bukkit.getPlayer(args[1]);
                if (target == null) {
                    sender.sendMessage(errorColor + "Player " + args[1] + " not found!");
                    return;
                }
            } else {
                sender.sendMessage("Too many arguments!");
                return;
            }
            String toGm = args[0];
            Player senderplayer = Bukkit.getPlayer(sender.getName());
            if (toGm.startsWith("a") && checkPermission(sender, "gamemode.adventure", true, errorColor)) {
                if (target.getGameMode() != GameMode.ADVENTURE) {
                    target.setGameMode(GameMode.ADVENTURE);
                    if (senderplayer != target) {
                        sender.sendMessage(successColor + "Set " + target.getName() + "'s gamemode to adventure.");
                    }
                    target.sendMessage(successColor + "You are now in adventure mode.");
                } else {
                    if (senderplayer == target) {
                        target.sendMessage(errorColor + "You are already in adventure mode!");
                    } else {
                        sender.sendMessage(errorColor + target.getName() + " is already in adventure mode!");
                    }
                }
            } else if (toGm.startsWith("c") && checkPermission(sender, "gamemode.adventure", true, errorColor)) {
                if (target.getGameMode() != GameMode.CREATIVE) {
                    target.setGameMode(GameMode.CREATIVE);
                    if (senderplayer != target) {
                        sender.sendMessage(successColor + "Set " + target.getName() + "'s gamemode to creative.");
                    }
                    target.sendMessage(successColor + "You are now in creative mode.");
                } else {
                    if (senderplayer == target) {
                        target.sendMessage(errorColor + "You are already in creative mode!");
                    } else {
                        sender.sendMessage(errorColor + target.getName() + " is already in creative mode!");
                    }
                }
            } else if (toGm.startsWith("sp") && checkPermission(sender, "gamemode.spectator", true, errorColor)) {
                if (target.getGameMode() != GameMode.SPECTATOR) {
                    target.setGameMode(GameMode.SPECTATOR);
                    if (senderplayer != target) {
                        sender.sendMessage(successColor + "Set " + target.getName() + "'s gamemode to spectator.");
                    }
                    target.sendMessage(successColor + "You are now in spectator mode.");
                } else {
                    if (senderplayer == target) {
                        target.sendMessage(errorColor + "You are already in spectator mode!");
                    } else {
                        sender.sendMessage(errorColor + target.getName() + " is already in spectator mode!");
                    }
                }
            } else if (toGm.startsWith("s") && checkPermission(sender, "gamemode.survival", true, errorColor)) {
                if (target.getGameMode() != GameMode.SURVIVAL) {
                    target.setGameMode(GameMode.SURVIVAL);
                    if (senderplayer != target) {
                        sender.sendMessage(successColor + "Set " + target.getName() + "'s gamemode to survival.");
                    }
                    target.sendMessage(successColor + "You are now in survival mode.");
                } else {
                    if (senderplayer == target) {
                        target.sendMessage(errorColor + "You are already in survival mode!");
                    } else {
                        sender.sendMessage(errorColor + target.getName() + " is already in survival mode!");
                    }
                }
            } else {
                sender.sendMessage("Unknown gamemode " + args[0]);
            }
        }

        return;
    }
}
