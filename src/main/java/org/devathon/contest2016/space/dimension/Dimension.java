package org.devathon.contest2016.space.dimension;

import org.bukkit.World;
import org.bukkit.entity.Player;

/**
 * Copyright © 2016 Matthew E Development - All Rights Reserved
 * You may NOT use, distribute and modify this code.
 * <p>
 * Created by Matthew E on 11/5/2016 at 1:13 PM.
 */
public interface Dimension {

    String getName();

    void generate();

    World getWorld();

    String getInfo();

    void enter(Player player);

    void leave(Player player);
}
