package me.brettbender.basix.managers;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class SuicideManager {

    private SuicideManager() { }

    static SuicideManager instance = new SuicideManager();

    public static SuicideManager getInstance() {
        return instance;
    }

    JavaPlugin p;
    ArrayList<Player> suicided;

    public void setup(JavaPlugin p) {
        suicided = new ArrayList<Player>();
    }

    public void addSuicided(Player player) {
        suicided.add(player);
    }

    public void removeSuicided(Player player) {
        suicided.remove(player);
    }

    public boolean getSuicided(Player player) {
        if (suicided.indexOf(player) > -1) {
            return true;
        } else {
            return false;
        }
    }

}
