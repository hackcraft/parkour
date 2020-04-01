package com.hackclub.hackcraft.parkour.commands;

import com.hackclub.hackcraft.parkour.ParkourPlugin;
import com.hackclub.hackcraft.parkour.objects.ParkourMap;
import com.hackclub.hackcraft.parkour.utils.Util;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;

public class ParkourAdminCommand implements CommandExecutor {

    private ParkourPlugin plugin;

    public ParkourAdminCommand(ParkourPlugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        Player sender = (Player) commandSender;

        if (!sender.isOp()) {
            commandSender.sendMessage(ChatColor.RED + "Apologies, you can't do that!");
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage(ChatColor.YELLOW + "You need more arguments!");
            return true;
        }

        if (args[0].equalsIgnoreCase("details")) {
            Optional<ParkourMap> pm = plugin.parkourUtil.getFromID(args[1]);

            if (!pm.isPresent()) {
                sender.sendMessage(ChatColor.RED + "That map doesn't exist!");
                return true;
            }

            sender.sendMessage(ChatColor.AQUA + "Map \"" + ChatColor.GREEN + pm.get().getName() + ChatColor.AQUA + "\": ");
            sender.sendMessage("Start at " + ChatColor.BLUE + Util.locationToChat(pm.get().getStart()));
            sender.sendMessage("End at " + ChatColor.BLUE + Util.locationToChat(pm.get().getEnd()));
            sender.sendMessage("Checkpoints: ");
            for (int i = 0; i < pm.get().getCheckpoints().size(); i++) {
                Location l = pm.get().getCheckpoints().get(i);
                
                plugin.getLogger().info(String.valueOf(l.distance(sender.getLocation())));
                
                if (l.distance(sender.getLocation()) < 5) {
                    sender.sendMessage(ChatColor.GREEN + String.valueOf(i) + ": " + ChatColor.BLUE + Util.locationToChat(l) + ChatColor.GREEN + " (You are next to this one!)");
                    continue;
                }

                sender.sendMessage(ChatColor.GREEN + String.valueOf(i) + ": " + ChatColor.BLUE + Util.locationToChat(l));
            }
            return true;
        }

        // admin wants to make new parkour
        if (args[0].equalsIgnoreCase("new")) {
            // args[1] is id, args[2] is name
            ParkourMap pm = new ParkourMap(args[1], args[2]);

            pm.setStart(sender.getLocation().getBlock().getLocation());
            pm.setEnd(sender.getLocation().getBlock().getLocation());


            if (plugin.parkourUtil.saveParkourMap(pm)) {
                sender.sendMessage(ChatColor.GREEN + "Parkour map successfully created!");
                return true;
            }

            sender.sendMessage(ChatColor.RED + "Sorry! It didn't save :(");
            return false;


        }

        // admin wants to add checkpoint
        if (args[0].equalsIgnoreCase("adch")) {
            // #getLocation gives the block that the feet are in so you just get what's facing down
            Block standingIn = sender.getLocation().getBlock();

            Optional<ParkourMap> pm = plugin.parkourUtil.getFromID(args[1]);

            if (!pm.isPresent()) {
                sender.sendMessage(ChatColor.RED + "That map doesn't exist!");
                return true;
            }

            ParkourMap pm2 = pm.get();

            if (args.length == 3) {
                // we've specified a before
                pm2.addCheckpoint(standingIn.getLocation(), Integer.parseInt(args[2]));
                if (plugin.parkourUtil.saveParkourMap(pm2)) {
                    sender.sendMessage(ChatColor.GREEN + "Checkpoint added before index " + args[2] + "!");
                    return true;
                }
            }

            if(pm2.addCheckpoint(standingIn.getLocation()) && plugin.parkourUtil.saveParkourMap(pm2)) {
              sender.sendMessage(ChatColor.GREEN + "Checkpoint added!");
              return true;
            }

            sender.sendMessage(ChatColor.RED + "An error occured whilst trying to add your checkpoint!");
            return false;
        }

        // admin wants to remove checkpoint
        if (args[0].equalsIgnoreCase("rmch")) {
            Optional<ParkourMap> pm = plugin.parkourUtil.getFromID(args[1]);

            if (!pm.isPresent()) {
                sender.sendMessage(ChatColor.RED + "That map doesn't exist!");
                return true;
            }

            ParkourMap pm2 = pm.get();

            if (pm2.removeCheckpoint(Integer.parseInt(args[2])) && plugin.parkourUtil.saveParkourMap(pm2)) {
                sender.sendMessage(ChatColor.GREEN + "Checkpoint removed!");
                return true;
            } else {
                sender.sendMessage(ChatColor.RED + "Error while removing checkpoint!");
                return true;
            }
        }


        return false;
    }
}
