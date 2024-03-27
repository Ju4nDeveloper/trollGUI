package me.juan.troll.troll.impl;

import com.cryptomorin.xseries.XMaterial;
import com.google.common.collect.Lists;
import me.juan.troll.troll.ITroll;
import me.juan.troll.troll.TrollMetadata;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class CreeperTroll implements ITroll {
    @Override
    public TrollMetadata getMetadata(String player) {
        return new TrollMetadata("&a&lCreeper Troll", XMaterial.CREEPER_HEAD.parseMaterial(), 4, Lists.newArrayList(
                "",
                "&7Click to troll " + player + " with creepers"
        ));
    }

    @Override
    public void troll(Player player) {
        Location location = player.getLocation();
        double newC = location.getX()+1;
        location.setX(newC);
        player.getWorld().spawnEntity(location, EntityType.CREEPER);
        location = player.getLocation();
        newC = location.getX()-1;
        location.setX(newC);
        player.getWorld().spawnEntity(location, EntityType.CREEPER);
        location = player.getLocation();
        newC = location.getZ()+1;
        location.setZ(newC);
        player.getWorld().spawnEntity(location, EntityType.CREEPER);
        location = player.getLocation();
        newC = location.getZ()-1;
        location.setZ(newC);
        player.getWorld().spawnEntity(location, EntityType.CREEPER);

    }
}
