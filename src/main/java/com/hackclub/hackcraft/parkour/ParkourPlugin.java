package com.hackclub.hackcraft.parkour;

import org.bukkit.plugin.java.JavaPlugin;

public class ParkourPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Parkour activated!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Parkour deactivated!");
    }
}
