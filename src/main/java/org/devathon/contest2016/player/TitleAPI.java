package org.devathon.contest2016.player;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

/**
 * Copyright © 2016 Matthew E Development - All Rights Reserved
 * You may NOT use, distribute and modify this code.
 * <p>
 * Created by Matthew E on 11/5/2016 at 1:48 PM.
 */
public class TitleAPI {

    private static TitleAPI instance;

    public static TitleAPI getInstance() {
        if (instance == null) {
            instance = new TitleAPI();
        }
        return instance;
    }

    public void sendActionMessage(Player player, String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.translateAlternateColorCodes('&', message)));
    }

    public void sendTitle(Player player, String message, String sub) {
        player.sendTitle(ChatColor.translateAlternateColorCodes('&', message), ChatColor.translateAlternateColorCodes('&', sub));
    }
}
