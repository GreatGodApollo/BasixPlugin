package me.brettbender.basix.commands;

import me.brettbender.basix.managers.SettingsManager;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import static me.brettbender.basix.helpers.permissionCheck.checkPermission;


public class WarpCommand extends BasixCommand {

    WarpCommand() {
        super("warp", false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        settingsManager.reloadData();
        FileConfiguration data = settingsManager.getData();
        Player player = (Player) sender;
        switch(args[0].toLowerCase()) {
            case("set"): {
                if (checkPermission(sender, "warp.set", true, errorColor)) {
                    if (args[1].equalsIgnoreCase("set") || args[1].equalsIgnoreCase("del")) {
                        sender.sendMessage(errorColor + "You can't set a warp the same as a sub command!");
                        break;
                    } else if (args.length == 2) {
                        String warpname = args[1].replaceAll("[^a-zA-Z ]", "").toLowerCase();
                        data.set("warps." + warpname + ".world", player.getLocation().getWorld().getName());
                        data.set("warps." + warpname + ".x", player.getLocation().getX());
                        data.set("warps." + warpname + ".y", player.getLocation().getY());
                        data.set("warps." + warpname + ".z", player.getLocation().getZ());
                        data.set("warps." + warpname + ".yaw", player.getLocation().getYaw());
                        data.set("warps." + warpname + ".pitch", player.getLocation().getPitch());
                        settingsManager.saveData();
                        sender.sendMessage(successColor + "Set warp " + warpname + ".");
                        break;
                    } else {
                        sender.sendMessage(errorColor + "Please supply a warp name!");
                        break;
                    }
                }
            } case("del"): {
                if (checkPermission(sender, "warp.del", true, errorColor)) {

                     if (args.length == 2) {
                        String warpname = args[1].replaceAll("[^a-zA-Z ]", "").toLowerCase();
                        if (data.getConfigurationSection("warps." + warpname) == null) {
                            sender.sendMessage(errorColor + "Warp " + warpname + " does not exist!");
                            break;
                        }
                        data.set("warps." + warpname, null);
                        settingsManager.saveData();
                        sender.sendMessage(successColor + "Removed warp " + warpname + ".");
                        break;
                    }
                    break;
                }
            } default: {
                String warpname = args[0].replaceAll("[^a-zA-Z ]", "").toLowerCase();
                if (checkPermission(sender, "warp", true, errorColor)) {
                    if (warpname.equals("")) {
                        sender.sendMessage(errorColor + "Please provide a warp name!");
                        break;
                    }
                    if (checkPermission(sender, "warp." + warpname, false, errorColor) || checkPermission(sender, "warp.all", false, errorColor)) {
                        if (data.getConfigurationSection("warps." + warpname) == null) {
                            sender.sendMessage(errorColor + "Warp " + warpname + " does not exist!");
                        }
                        World w = plugin.getServer().getWorld(data.getString("warps." + warpname + ".world"));
                        double x = data.getDouble("warps." + warpname + ".x");
                        double y = data.getDouble("warps." + warpname + ".y");
                        double z = data.getDouble("warps." + warpname + ".z");
                        float yaw = (float) data.getDouble("warps." + warpname + ".yaw", 0);
                        float pitch = (float) data.getDouble("warps." + warpname + ".pitch", 0);
                        player.teleport(new Location(w, x, y, z, yaw, pitch));
                        sender.sendMessage(successColor + "Warped to " + warpname + ".");
                    } else {
                        sender.sendMessage(errorColor + "You do not have permission to warp to " + warpname + "!");
                    }
                }
            }
        }
    }
}
