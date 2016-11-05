package org.devathon.contest2016;

import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.devathon.contest2016.commands.CommandDimension;
import org.devathon.contest2016.player.TitleAPI;
import org.devathon.contest2016.space.SpaceListener;
import org.devathon.contest2016.space.dimension.DarkDimension;
import org.devathon.contest2016.space.dimension.Dimension;

import java.util.HashMap;
import java.util.logging.Level;

public class DevathonPlugin extends JavaPlugin  {

    private static DevathonPlugin instance;
    public HashMap<String, Dimension> dimensionMap;

    public static DevathonPlugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        this.instance = this;
        actionTask();
        getCommand("dimension").setExecutor(new CommandDimension());
        getServer().getPluginManager().registerEvents(new SpaceListener(), this);
        this.dimensionMap = Maps.newHashMap();
        dimensionMap.put("Dark", new DarkDimension());

        dimensionMap.values().forEach(dimension -> {
            getLogger().log(Level.INFO, dimension.getName() + " Loaded");
        });
    }

    @Override
    public void onDisable() {
    }

    public Dimension getDimension(String name) {
        return (dimensionMap.get(name) != null) ? dimensionMap.get(name) : null;
    }

    public void actionTask() {
        getServer().getScheduler().scheduleAsyncRepeatingTask(this, ()-> {
            Bukkit.getServer().getOnlinePlayers().forEach(player -> {
                String world = player.getWorld().getName();
                if (world.equals("world")) {
                    world = "Main";
                }
                if (world.startsWith("Dimension_")) {
                    world = world.split("Dimension_")[1] + " Dimension";
                }
                TitleAPI.getInstance().sendActionMessage(player, "&aCurrent World: &6" + world);
            });
        }, 5L, 5L);
    }
}

