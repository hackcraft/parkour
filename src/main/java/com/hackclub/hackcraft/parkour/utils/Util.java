package com.hackclub.hackcraft.parkour.utils;

import org.bukkit.Location;

public class Util {
    public static String locationToChat(Location loc) {
        return String.format("%.2f, %.2f, %.2f in " + loc.getWorld().getName(), loc.getX(), loc.getY(), loc.getZ());
    }
}
