package org.devathon.contest2016.armorstand;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;


/**
 * Copyright © 2016 Matthew E Development - All Rights Reserved
 * You may NOT use, distribute and modify this code.
 * <p>
 * Created by Matthew E on 11/5/2016 at 3:41 PM.
 */
public class BlockStand {

    private ArmorStand armorStand;
    private Location location;
    private Material material;

    public BlockStand(Location location, Material material, boolean center) {
        this.location = location;
        if (center) {
            location.setY(location.getY() - 1.3);
            location.setDirection(new Vector().normalize());
        }
        this.material = material;
    }

    public Location getLocation() {
        return location;
    }

    public ArmorStand getArmorStand() {
        return armorStand;
    }

    public Material getMaterial() {
        return material;
    }

    public void spawn() {
        armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setCollidable(false);
        armorStand.setVisible(false);
        armorStand.setSilent(true);

        armorStand.setInvulnerable(true);
        armorStand.setHelmet(new ItemStack(material));
        armorStand.setGravity(false);
    }

    public void up() {
        armorStand.getLocation().setY(armorStand.getLocation().getY() + 1);
    }
}
