package org.devathon.contest2016.space.dimension;


import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
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
        player.teleport(world.getSpawnLocation());
        player.getEquipment().setHelmet(new ItemStack(Material.JACK_O_LANTERN));
        player.playSound(player.getLocation(), Sound.ENTITY_ENDERDRAGON_DEATH, 1.0F, 1.0F);
        TitleAPI.getInstance().sendActionMessage(player, "&aYou now enter The Dark Dimension");
    }

    @Override
    public void leave(Player player) {
        player.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
        player.getEquipment().setHelmet(new ItemStack(Material.AIR));
        TitleAPI.getInstance().sendActionMessage(player, "&aYou have left The Dark Dimension");
    }
}
