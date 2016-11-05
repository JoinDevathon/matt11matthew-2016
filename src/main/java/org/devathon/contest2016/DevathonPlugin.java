package org.devathon.contest2016;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.devathon.contest2016.space.dimension.DarkDimension;

public class DevathonPlugin extends JavaPlugin implements Listener {

    private static DevathonPlugin instance;
    private DarkDimension darkDimension;

    public static DevathonPlugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        this.instance = this;
        getServer().getPluginManager().registerEvents(this, this);
        createDimensions();
    }
 
    private void createDimensions() {
        this.darkDimension = new DarkDimension();
    }

    @Override
    public void onDisable() {
    }

    @EventHandler
    public void onChat(PlayerChatEvent e) {
        Player player = e.getPlayer();
        switch (e.getMessage().toLowerCase()) {
            case "dark":
                darkDimension.enter(player);
                break;
            case "home":
                darkDimension.leave(player);
                break;
        }
    }
}

