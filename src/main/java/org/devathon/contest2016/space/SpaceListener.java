package org.devathon.contest2016.space;

import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.devathon.contest2016.DevathonPlugin;

/**
 * Copyright © 2016 Matthew E Development - All Rights Reserved
 * You may NOT use, distribute and modify this code.
 * <p>
 * Created by Matthew E on 11/5/2016 at 2:47 PM.
 */
public class SpaceListener implements Listener {

    @EventHandler
    public void enterDarkDimension(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if (e.getFrom() != e.getTo()) {
            if (!player.getWorld().getName().startsWith("Dimension_")) {
                if (e.getTo().getBlock().getY() >= 150) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100000, 1));
                    if (e.getTo().getBlock().getY() >= 200) {
                        DevathonPlugin.getInstance().getDimension("Dark").enter(player);
                        player.removePotionEffect(PotionEffectType.BLINDNESS);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onSwing(PlayerInteractEvent e) {
        if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (e.getPlayer().getItemInHand().getEnchantments().containsKey(Enchantment.DURABILITY)) {
                e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 60, 0));
                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_ANVIL_HIT, 0.5F, 0.5F);

            }
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        if (player.getItemInHand().getEnchantments().containsKey(Enchantment.DURABILITY)) {
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 1.0F);
            e.getBlock().getDrops().forEach(itemStack -> itemStack.setAmount(itemStack.getAmount() * 2));
        }
    }
}