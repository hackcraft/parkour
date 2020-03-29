package com.hackclub.hackcraft.parkour.utils;

import com.hackclub.hackcraft.parkour.objects.ParkourMap;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class ParkourUtil {

    private Plugin plugin;
    private FileConfiguration parkourFile;

    public ParkourUtil(Plugin pl) {
        plugin = pl;
        parkourFile = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "parkour.yml"));y
    }

    public void printParkours() {
        System.out.println(parkourFile.getConfigurationSection("parkours").getValues(true));
    }

}
