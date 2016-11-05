package org.devathon.contest2016.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.devathon.contest2016.DevathonPlugin;
import org.devathon.contest2016.space.dimension.Dimension;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright © 2016 Matthew E Development - All Rights Reserved
 * You may NOT use, distribute and modify this code.
 * <p>
 * Created by Matthew E on 11/5/2016 at 2:24 PM.
 */
public class CommandDimension implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                switch (args[0].toLowerCase()) {
                    case "list":
                        List<String> list = new ArrayList<>();
                        DevathonPlugin.getInstance().dimensionMap.values().forEach(dimension -> list.add(dimension.getName()));
                        player.sendMessage(ChatColor.GREEN + "List: \n" + ChatColor.GREEN + list.toString().replace("[", "").replace("]", "").replaceAll(",", ChatColor.RED + ", " + ChatColor.GREEN));
                        break;
                    case "home":
                        if (!player.getWorld().getName().startsWith("Dimension")) {
                            return true;
                        }
                        DevathonPlugin.getInstance().getDimension(player.getWorld().getName().split("_")[1]).leave(player);
                        break;
                    case "teleport":
                        if (args.length == 2) {
                            String dimensionName = args[1];
                            Dimension dimension = DevathonPlugin.getInstance().getDimension(dimensionName);
                            if (dimension == null) {
                                player.sendMessage("That is not a dimension!");
                                return true;
                            }
                            dimension.enter(player);
                            return true;
                        }
                        break;
                }
            }
        } catch (Exception e) {
            return true;
        }
        return true;
    }
}
