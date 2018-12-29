package me.brettbender.basix.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import static me.brettbender.basix.helpers.ColoredStringParser.parseColoredString;
import static me.brettbender.basix.helpers.permissionCheck.checkPermission;

public class MOTDCommand extends BasixCommand {

    private boolean isEnabled;

    MOTDCommand() {
        super("motd", true);
        this.isEnabled = plugin.getConfig().getConfigurationSection("motd").
                getBoolean("enabled", true);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        plugin.reloadConfig();
        FileConfiguration config = plugin.getConfig();
        ConfigurationSection motdConfigSection = config.getConfigurationSection("motd");
        if (args.length == 0 && isEnabled) {
            sender.sendMessage(parseColoredString(motdConfigSection.getString("msg", "&cConfiguration value not found!")));
            return;
        } if (args.length >= 1) {
            switch (args[0]) {
                case("enable"): {
                    if (checkPermission(sender, "motd.enable", true, errorColor)) {
                        if (isEnabled) {
                            sender.sendMessage(errorColor + "MOTD is already enabled!");
                            break;
                        } else {
                            isEnabled = true;
                            motdConfigSection.set("enabled", true);
                            plugin.saveConfig();
                            sender.sendMessage(successColor + "MOTD Enabled.");
                            break;
                        }
                    } else {
                        break;
                    }
                } case("disable"): {
                    if (checkPermission(sender, "motd.disable", true, errorColor)) {
                        if (isEnabled) {
                            isEnabled = false;
                            motdConfigSection.set("enabled", false);
                            plugin.saveConfig();
                            sender.sendMessage(successColor + "MOTD Disabled.");
                            break;
                        } else {
                            sender.sendMessage(errorColor + "MOTD is already disabled!");
                            break;
                        }
                    } else {
                        break;
                    }
                } case("set"): {
                    if (checkPermission(sender, "motd.set", true, errorColor)) {
                        if (args.length >= 2) {
                            String[] subArgs = new String[args.length - 1];
                            for (int i = 0; i <= args.length - 2; i ++) {
                                subArgs[i] = args[i + 1];
                            }
                            String newMsg = String.join(" ", subArgs);
                            motdConfigSection.set("msg", newMsg);
                            plugin.saveConfig();
                            sender.sendMessage(successColor + "Updated MOTD.");
                            break;
                        } else {
                            sender.sendMessage(errorColor + "Please provide a new message!");
                        }
                    }
                } default: {
                    sender.sendMessage(errorColor + "Unknown option " + args[0]);
                }
            }
        } else {
            sender.sendMessage(errorColor + "This command is disabled.");
        }
    }
}
