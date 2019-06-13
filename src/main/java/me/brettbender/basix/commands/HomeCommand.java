package me.brettbender.basix.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import static me.brettbender.basix.helpers.permissionCheck.checkPermission;

public class HomeCommand extends BasixCommand {


    HomeCommand() {
        super("home", false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (checkPermission(sender, "home", true, errorColor)) {
            Player senderPlayer = (Player) sender;
            settingsManager.reloadData();
            FileConfiguration data = settingsManager.getData();

            if (args.length == 0) {
                if (checkPermission(sender, "home.tp", true, errorColor)) {
                    ConfigurationSection sect;
                    try {
                        sect = data.getConfigurationSection("home." + senderPlayer.getUniqueId() + ".default");
                    } catch(Exception e){
                        sect = null;
                    }
                    if (sect == null || sect.get("world") == null) {
                        sender.sendMessage(errorColor + "You do not have a home set!");
                    } else {
                        tp(senderPlayer, sect);
                        sender.sendMessage(successColor + "Teleported to your home.");
                    }
                }
            } else if (args.length == 1) {
                switch (args[0].toLowerCase()) {
                    case ("set"): {
                        if (checkPermission(sender, "home.set", true, errorColor)) {
                            ConfigurationSection sect = settingsManager.getData().createSection("home." + senderPlayer.getUniqueId() + ".default");
                            Location playerLocation = senderPlayer.getLocation();
                            sect.set("world", playerLocation.getWorld().getName());
                            sect.set("x", playerLocation.getX());
                            sect.set("y", playerLocation.getY());
                            sect.set("z", playerLocation.getZ());
                            sect.set("yaw", playerLocation.getYaw());
                            sect.set("pitch", playerLocation.getPitch());
                            settingsManager.saveData();
                            sender.sendMessage(successColor + "Set your home.");
                            break;
                        }
                        break;
                    }
                    default: {
                        if (checkPermission(sender, "home.tp.others", true, errorColor)) {
                            Player otherPlayer = Bukkit.getPlayer(args[0]);
                            if (otherPlayer != null) {
                                ConfigurationSection sect = data.getConfigurationSection("home." + otherPlayer.getUniqueId() + ".default");
                                if (sect != null) {
                                    tp(senderPlayer, sect);
                                    sender.sendMessage(successColor + "Teleported to " + args[0] + "'s home.");
                                } else {
                                    sender.sendMessage(errorColor + "That player doesn't have a home set!");
                                }
                            } else {
                                sender.sendMessage(errorColor + "The player needs to be online in order to teleport to their home.");
                            }
                            break;
                        }
                        break;
                    }
                }
            } else {
                sender.sendMessage(errorColor + "Too many arguments!");
            }
        }
    }

    private void tp(Player senderPlayer, ConfigurationSection sect) {
        final World world = Bukkit.getWorld(sect.getString("world"));
        final double x = sect.getDouble("x");
        final double y = sect.getDouble("y");
        final double z = sect.getDouble("z");
        final float yaw = (float) sect.getDouble("yaw");
        final float pitch = (float) sect.getDouble("pitch");
        Location newLocation = new Location(world, x, y, z, yaw, pitch);

        senderPlayer.teleport(newLocation);
    }
}
