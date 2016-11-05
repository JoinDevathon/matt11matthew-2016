package org.devathon.contest2016.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.devathon.contest2016.DevathonPlugin;
import org.devathon.contest2016.armorstand.BlockStand;
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
                    case "setup":
                        BlockStand blockStand = new BlockStand(player.getLocation(), Material.IRON_BLOCK, false);
                        blockStand.spawn();
                        Minecart minecart = (Minecart) player.getWorld().spawnEntity(player.getLocation(), EntityType.MINECART);
                        minecart.setPassenger(blockStand.getArmorStand());
                        break;
                    case "list":
                        List<String> list = new ArrayList<>();
                        DevathonPlugin.getInstance().dimensionMap.values().forEach(dimension -> list.add(dimension.getName()));
                        player.sendMessage(ChatColor.GREEN + "List: \n" + ChatColor.GREEN + list.toString().replace("[", "").replace("]", "").replaceAll(",", ChatColor.RED + ", " + ChatColor.GREEN));
                        break;
                    case "tools":
                        ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE);
                        ItemMeta im = item.getItemMeta();
                        im.setDisplayName(ChatColor.AQUA + "Drill Tool");
                        im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                        item.setItemMeta(im);
                        item.addEnchantment(Enchantment.DURABILITY, 1);
                        player.getInventory().addItem(item);

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
