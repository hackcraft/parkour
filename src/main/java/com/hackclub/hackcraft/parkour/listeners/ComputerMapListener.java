package com.hackclub.hackcraft.parkour.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

public class ComputerMapListener implements Listener {
    // all of the custom listeners for the computer map

    final Location WATER_STOP_1 = new Location(Bukkit.getWorld("world"), -194.0, 23, -144);
    final Location WATER_STOP_2 = new Location(Bukkit.getWorld("world"), -195.0, 23, -144);


    @EventHandler
    public void onWaterFlow(BlockFromToEvent event) {
        Location loc = event.getToBlock().getLocation();

        Bukkit.getServer().getLogger().info(event.getBlock().getType().toString());

        if (loc.distance(WATER_STOP_1) < 2 || loc.distance(WATER_STOP_2) < 2) {
            event.setCancelled(true);
        }
    }

}
