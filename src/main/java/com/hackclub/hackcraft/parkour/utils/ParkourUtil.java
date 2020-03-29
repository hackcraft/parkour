package com.hackclub.hackcraft.parkour.utils;

import com.hackclub.hackcraft.parkour.objects.ParkourMap;
import org.bukkit.configuration.MemoryConfiguration;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ParkourUtil {

    private Plugin plugin;
    private FileConfiguration parkourFile;

    File file;


    public ParkourUtil(Plugin pl) {
        plugin = pl;
        file = new File(plugin.getDataFolder(), "parkour.yml");
        parkourFile = YamlConfiguration.loadConfiguration(file);
    }

    public boolean saveParkourMap(ParkourMap pm) {
        parkourFile.set("parkours." + pm.getId(), pm);
        try {
            parkourFile.save(file);
            return true;
        } catch (IOException e) {
            plugin.getLogger().severe("Error whilst try to save ParkourMap:");
            plugin.getLogger().severe(e.getMessage());
            return false;
        }
    }

    public Optional<ParkourMap> getFromID(String id) {
        Optional<ParkourMap> pm = getParkourMaps().stream().filter(m -> m.getId().equals(id)).findFirst();

        return pm;
    }

    public ArrayList<ParkourMap> getParkourMaps() {
        ArrayList<ParkourMap> parkours = new ArrayList<>();

        ((MemorySection) parkourFile.get("parkours")).getValues(true).forEach((k, v) -> parkours.add((ParkourMap) v));

        return parkours;
    }

}
