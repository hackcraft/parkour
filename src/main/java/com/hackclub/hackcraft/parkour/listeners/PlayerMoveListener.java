package com.hackclub.hackcraft.parkour.listeners;

import com.hackclub.hackcraft.parkour.ParkourPlugin;
import com.hackclub.hackcraft.parkour.events.CheckpointEvent;
import com.hackclub.hackcraft.parkour.objects.ParkourMap;
import com.hackclub.hackcraft.parkour.utils.ParkourUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    private ParkourPlugin pl;

    public PlayerMoveListener(ParkourPlugin pl) {
        this.pl = pl;
    }

    @EventHandler
    public void onPlayerMoveMoreThanOneBlock(PlayerMoveEvent e) {
        // for each checkpoint

        // i'm about 90% sure that this is a really awful way to do this but i am tired and it will work
        boolean found = false;
        Location blockLocation = e.getTo().getBlock().getLocation();
        boolean actualBlockChange = e.getFrom().getBlockX() != e.getTo().getBlockX() || e.getFrom().getBlockY() != e.getTo().getBlockY() || e.getFrom().getBlockZ() != e.getTo().getBlockZ();

        for (ParkourMap p : pl.parkourUtil.getParkourMaps()) {
            if (found)
                break;
            for (Location c : p.getCheckpoints()) {
                if (found)
                    break;
                // i know the checkpoint.getBlock().getLocation could be redundant, but i'm doing it so we are sure it's the exact location of the block vs the location that was put in (not sure if these are the same, will test later when cleaning up)
                if (c.getBlock().getLocation().equals(blockLocation) && actualBlockChange) {
                    pl.getLogger().info("checkpoint hit!");
                    CheckpointEvent ce = new CheckpointEvent(e.getPlayer(), p.getCheckpoints().lastIndexOf(c));
                    Bukkit.getServer().getPluginManager().callEvent(ce);
                    found = true;
                }
            }

            if (p.getStart().getBlock().equals(blockLocation) && actualBlockChange) {
                // TODO: add event for start
            }

            if (p.getEnd().getBlock().equals(blockLocation) && actualBlockChange) {
                // TODO: add event for end
            }
        }
    }

}
