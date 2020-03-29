package com.hackclub.hackcraft.parkour.objects;

import org.bukkit.Location;

import java.util.ArrayList;

public class ParkourMap {

    private String name;
    private Location start;
    private Location end;
    private ArrayList<Location> checkpoints;

    public ParkourMap(String name) {
        this.name = name;
        checkpoints = new ArrayList<Location>();
    }

    public Location getStart() {
        return start;
    }

    public Location getEnd() {
        return end;
    }

    public boolean addCheckpoint(Location toAdd) {
        return checkpoints.add(toAdd);
    }

    public void setStart(Location start) {
        this.start = start;
    }

    public void setEnd(Location end) {
        this.end = end;
    }
}
