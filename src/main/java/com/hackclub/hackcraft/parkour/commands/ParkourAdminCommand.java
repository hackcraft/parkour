package com.hackclub.hackcraft.parkour.commands;

import com.hackclub.hackcraft.parkour.ParkourPlugin;
import com.hackclub.hackcraft.parkour.objects.ParkourMap;
import com.hackclub.hackcraft.parkour.objects.ParkourPlayer;
import com.hackclub.hackcraft.parkour.utils.ParkourUtil;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
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

            sender.sendMessage("Map \"" + pm.get().getName() + "\": ");
            sender.sendMessage("Start at " + pm.get().getStart().toString());
            sender.sendMessage("End at " + pm.get().getEnd().toString());
            sender.sendMessage("Checkpoints: ");
            for (int i = 0; i < pm.get().getCheckpoints().size(); i++) {
                sender.sendMessage(i + ": " + pm.get().getCheckpoints().get(i).toString());
            }
            return true;
        }

        // admin wants to make new parkour
        if (args[0].equalsIgnoreCase("new")) {
            // args[1] is id, args[2] is name
            ParkourMap pm = new ParkourMap(args[1], args[2]);

            pm.setStart(sender.getLocation());
            pm.setEnd(sender.getLocation());


            if (plugin.parkourUtil.saveParkourMap(pm)) {
                sender.sendMessage(ChatColor.GREEN + "Parkour map successfully created!");
                return true;
            }

            sender.sendMessage(ChatColor.RED + "Sorry! It didn't save :(");
            return false;


        }

        // admin wants to add checkpoint
        if (args[0].equalsIgnoreCase("add_checkpoint")) {
            // #getLocation gives the block that the feet are in so you just get what's facing down
            Block standingIn = sender.getLocation().getBlock();
            if (standingIn.getType() != Material.STONE_PRESSURE_PLATE) {
                sender.sendMessage(ChatColor.YELLOW + "You must be standing on a stone pressure plate to set a checkpoint!" + sender.getLocation().getBlock().getType().toString());
                return true;
            }

            Optional<ParkourMap> pm = plugin.parkourUtil.getFromID(args[1]);

            if (!pm.isPresent()) {
                sender.sendMessage(ChatColor.RED + "That map doesn't exist!");
                return true;
            }

            ParkourMap pm2 = pm.get();

            if(pm2.addCheckpoint(standingIn.getLocation()) && plugin.parkourUtil.saveParkourMap(pm2)) {
              sender.sendMessage(ChatColor.GREEN + "Checkpoint added!");
              return true;
            }

            sender.sendMessage(ChatColor.RED + "An error occured whilst trying to add your checkpoint!");
            return false;
        }

        // admin wants to remove checkpoint
        if (args[0].equalsIgnoreCase("remove_checkpoint")) {
            return true;
        }


        return false;
    }
}
