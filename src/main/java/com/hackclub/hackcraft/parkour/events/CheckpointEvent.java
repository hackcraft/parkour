package com.hackclub.hackcraft.parkour.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CheckpointEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private int checkpointIndex;

    public CheckpointEvent(Player player, int checkpointIndex) {
        this.player = player;
        this.checkpointIndex = checkpointIndex;
    }

    public int getCheckpointIndex() {
        return checkpointIndex;
    }

    public Player getPlayer() {
        return player;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }


}
