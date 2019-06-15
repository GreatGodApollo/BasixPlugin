/*
 * Basix Bukkit Plugin
 * Copyright (C) 2019 Brett Bender
 */

package me.brettbender.basix.commands;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.potion.PotionEffect;

public class HealCommand extends BasixCommand {

    HealCommand() {
        super("heal", "heal", false);
    }


    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        Double maxHealth = player.getMaxHealth();
        Double currentHealth = player.getHealth();
        if (currentHealth == 0) {
            sender.sendMessage(errorColor + "Dead people can't get health!");
            return;
        }
        Double healthDifference = maxHealth - currentHealth;
        final EntityRegainHealthEvent erhe = new EntityRegainHealthEvent(player, healthDifference, EntityRegainHealthEvent.RegainReason.CUSTOM);
        double newHealth = currentHealth + erhe.getAmount();
        if (newHealth > maxHealth) {
            newHealth = maxHealth;
        }
        player.setHealth(newHealth);
        player.setFoodLevel(20);
        player.setFireTicks(0);
        sender.sendMessage(successColor + "You have been healed!");
        for (PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }

    }
}
