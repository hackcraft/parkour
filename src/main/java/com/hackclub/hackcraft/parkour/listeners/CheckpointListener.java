package com.hackclub.hackcraft.parkour.listeners;

import com.hackclub.hackcraft.parkour.ParkourPlugin;
import com.hackclub.hackcraft.parkour.events.CheckpointEvent;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CheckpointListener implements Listener {

    private ParkourPlugin pl;

    public CheckpointListener(ParkourPlugin pl) {
        this.pl = pl;
    }

    @EventHandler
    public void onCheckpoint(CheckpointEvent e) {
        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0F, 8.0F);
    }
}
