/*
 * Basix Bukkit Plugin
 * Copyright (C) 2019 Brett Bender
 */

package me.brettbender.basix.listeners;

import me.brettbender.basix.managers.SuicideManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        SuicideManager suicideManager = SuicideManager.getInstance();

        if (suicideManager.getSuicided(event.getEntity())) {
            event.setDeathMessage(null);
            suicideManager.removeSuicided(event.getEntity());
        }

    }

}
