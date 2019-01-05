package me.brettbender.basix.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class HomeCommand extends BasixCommand {


    HomeCommand() {
        super("home", false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player senderPlayer = (Player) sender;
        settingsManager.reloadData();
        FileConfiguration data = settingsManager.getData();

        if (args.length == 0) {
            ConfigurationSection sect = data.getConfigurationSection("home." + senderPlayer.getUniqueId() + ".default");
            if (sect.get("world") == null) {
                sender.sendMessage(errorColor + "You do not have a home set!");
            } else {
                final World world = Bukkit.getWorld(sect.getString("world"));
                final double x = sect.getDouble("x");
                final double y = sect.getDouble("y");
                final double z = sect.getDouble("z");
                final float yaw = (float) sect.getDouble("yaw");
                final float pitch = (float) sect.getDouble("pitch");
                Location newLocation = new Location(world, x, y, z, yaw, pitch);

                senderPlayer.teleport(newLocation);
                sender.sendMessage(successColor + "Teleported to your home.");
            }
        } else if (args.length == 1) {
            switch(args[0].toLowerCase()) {
                case("set"): {
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
                } default: {
                    Player otherPlayer = Bukkit.getPlayer(args[0]);
                    if (otherPlayer != null) {
                        ConfigurationSection sect = data.getConfigurationSection("home." + otherPlayer.getUniqueId() + ".default");
                        if (sect !=  null) {
                            final World world = Bukkit.getWorld(sect.getString("world"));
                            final double x = sect.getDouble("x");
                            final double y = sect.getDouble("y");
                            final double z = sect.getDouble("z");
                            final float yaw = (float) sect.getDouble("yaw");
                            final float pitch = (float) sect.getDouble("pitch");
                            Location newLocation = new Location(world, x, y, z, yaw, pitch);

                            senderPlayer.teleport(newLocation);
                            sender.sendMessage(successColor + "Teleported to " + args[0] + "'s home.");
                        } else {
                            sender.sendMessage(errorColor + "That player doesn't have a home set!");
                        }
                    } else {
                        sender.sendMessage(errorColor + "The player needs to be online in order to teleport to their home.");
                    }
                    break;
                }
            }
        } else {
            sender.sendMessage(errorColor + "Too many arguments!");
        }

    }
}
