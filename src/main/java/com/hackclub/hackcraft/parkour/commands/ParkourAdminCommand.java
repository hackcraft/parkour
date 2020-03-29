package com.hackclub.hackcraft.parkour.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class ParkourAdminCommand implements CommandExecutor {

    private Plugin plugin;

    public ParkourAdminCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (!commandSender.isOp()) {
            commandSender.sendMessage(ChatColor.RED + "Apologies, you can't do that!");
            return true;
        }

        if (args.length < 2) {
            commandSender.sendMessage(ChatColor.YELLOW +_"You need more arguments!");
        }

        // admin wants to make new parkour
        if (args[0].equalsIgnoreCase("new")) {

        }

        // admin wants to add checkpoint
        if (args[0].equalsIgnoreCase("add_checkpoint")) {

        }

        // admin wants to remove checkpoint
        if (args[0].equalsIgnoreCase("remove_checkpoint")) {

        }


        return false;
    }
}
