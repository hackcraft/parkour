package com.hackclub.hackcraft.parkour.commands;

import com.hackclub.hackcraft.parkour.ParkourPlugin;
import com.hackclub.hackcraft.parkour.objects.ParkourMap;
import com.hackclub.hackcraft.parkour.objects.ParkourPlayer;
import com.hackclub.hackcraft.parkour.utils.ParkourUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Optional;

public class ParkourAdminCommand implements CommandExecutor {

    private ParkourPlugin plugin;

    public ParkourAdminCommand(ParkourPlugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (!commandSender.isOp()) {
            commandSender.sendMessage(ChatColor.RED + "Apologies, you can't do that!");
            return true;
        }

        if (args.length < 2) {
            commandSender.sendMessage(ChatColor.YELLOW + "You need more arguments!");
            return true;
        }

        if (args[0].equalsIgnoreCase("details")) {
            Optional<ParkourMap> pm = plugin.parkourUtil.getFromID("cpu");

            if (!pm.isPresent()) {
                commandSender.sendMessage(ChatColor.RED + "That map doesn't exist!");
                return true;
            }

            commandSender.sendMessage("Map \"" + pm.get().getName() + "\": ");
            commandSender.sendMessage("Start at " + pm.get().getStart().toString());
            commandSender.sendMessage("End at " + pm.get().getEnd().toString());

            return true;
        }

        // admin wants to make new parkour
        if (args[0].equalsIgnoreCase("new")) {
            // args[1] is id, args[2] is name
            ParkourMap pm = new ParkourMap(args[1], args[2]);

            pm.setStart(((Player) commandSender).getLocation());
            pm.setEnd(((Player) commandSender).getLocation());


            if (plugin.parkourUtil.saveParkourMap(pm)) {
                commandSender.sendMessage(ChatColor.GREEN + "Parkour map successfully created!");
                return true;
            }

            commandSender.sendMessage(ChatColor.RED + "Sorry! It didn't save :(");
            return false;


        }

        // admin wants to add checkpoint
        if (args[0].equalsIgnoreCase("add_checkpoint")) {
            return true;
        }

        // admin wants to remove checkpoint
        if (args[0].equalsIgnoreCase("remove_checkpoint")) {
            return true;
        }


        return false;
    }
}
