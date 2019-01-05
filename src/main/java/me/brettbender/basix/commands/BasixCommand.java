package me.brettbender.basix.commands;

import me.brettbender.basix.managers.SettingsManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import static me.brettbender.basix.helpers.permissionCheck.checkPermission;

public abstract class BasixCommand implements CommandExecutor {

    private final String commandName;
    private final boolean canConsoleUse;
    private final String permission;
    static ChatColor errorColor = ChatColor.RED;
    static ChatColor successColor = ChatColor.GREEN;
    static JavaPlugin plugin;
    static SettingsManager settingsManager;

    public abstract void execute(CommandSender sender, String[] args);

    BasixCommand(String commandName, boolean canConsoleUse) {
        this.canConsoleUse = canConsoleUse;
        this.commandName = commandName;
        this.permission = "";
        plugin.getCommand(commandName).setExecutor(this);
    }

    BasixCommand(String commandName, String permission, boolean canConsoleUse) {
        this.canConsoleUse = canConsoleUse;
        this.commandName = commandName;
        this.permission = permission;
        plugin.getCommand(commandName).setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (!cmd.getLabel().equalsIgnoreCase(commandName)) {
            return true;
        }
        if (!permission.isEmpty() && !checkPermission(sender, permission, true, errorColor)) {
            return true;
        }
        if (!canConsoleUse && !(sender instanceof Player)) {
            sender.sendMessage(errorColor + "Only players may run this command!");
            return true;
        }

        execute(sender, args);
        return true;
    }

    public static void registerCommands(JavaPlugin pl) {
        plugin = pl;

        settingsManager = SettingsManager.getInstance();

        // gamemode
        new GameModeCommand();

        // suicide
        new SuicideCommand();

        // fly
        new FlyCommand();

        // motd
        new MOTDCommand();

        // warp
        new WarpCommand();

        // home
        new HomeCommand();
    }


}
