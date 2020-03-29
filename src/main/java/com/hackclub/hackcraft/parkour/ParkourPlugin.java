package com.hackclub.hackcraft.parkour;

import com.hackclub.hackcraft.parkour.commands.ParkourAdminCommand;
import com.hackclub.hackcraft.parkour.listeners.ComputerMapListener;
import com.hackclub.hackcraft.parkour.objects.ParkourMap;
import com.hackclub.hackcraft.parkour.utils.ParkourUtil;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

public class ParkourPlugin extends JavaPlugin {

    public ParkourUtil parkourUtil;

    @Override
    public void onEnable() {
        getLogger().info("Parkour activated!");

        // register serialization
        ConfigurationSerialization.registerClass(ParkourMap.class);

        // register commands
        this.getCommand("parkour-admin").setExecutor(new ParkourAdminCommand(this));

        getServer().getPluginManager().registerEvents(new ComputerMapListener(), this);

        // initialize custom objects
        parkourUtil = new ParkourUtil(this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Parkour deactivated!");
    }
}
