package org.devathon.contest2016.space.dimension;


import org.bukkit.*;
import org.bukkit.entity.Player;
import org.devathon.contest2016.DevathonPlugin;
import org.devathon.contest2016.player.TitleAPI;

/**
 * Created by Matthew E on 11/5/2016 at 1:14 PM.
 */
public class DarkDimension implements Dimension {

    public DarkDimension() {
        WorldCreator worldCreator = new WorldCreator("Dimension_" + getName());
        worldCreator.type(WorldType.FLAT);
        this.world = worldCreator.createWorld();
        this.generate();
    }

    private World world;

    @Override
    public String getName() {
        return "Dark";
    }

    @Override
    public void generate() {
        getWorld().setTime(15000);
        getWorld().setThunderDuration(1000000);
        getWorld().generateTree(getWorld().getSpawnLocation(), TreeType.TREE);
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public String getInfo() {
        return "The Dark Dimension";
    }

    @Override
    public void enter(Player player) {
        if (player.getWorld().getName().equals(getWorld().getName())) {
            return;
        }
        player.teleport(world.getSpawnLocation());
        TitleAPI.getInstance().sendTitle(player, "&6Entering - Dark Dimension", "&7&o" + getInfo());
        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_SCREAM, 1.0F, 1.0F);
        DevathonPlugin.getInstance().getServer().getScheduler().scheduleSyncRepeatingTask(DevathonPlugin.getInstance(), () -> {
            if (player.getWorld().getName().equals(getWorld().getName())) {
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_SCREAM, 1.0F, 1.0F);
            }
        }, 300L, 300L);
    }

    @Override
    public void leave(Player player) {
        player.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
        TitleAPI.getInstance().sendTitle(player, "&cLeft - Dark Dimension", "&7&oSpawn world");
    }
}
