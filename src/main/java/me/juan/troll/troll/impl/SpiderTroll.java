package me.juan.troll.troll.impl;

import com.cryptomorin.xseries.XMaterial;
import com.google.common.collect.Lists;
import me.juan.troll.troll.ITroll;
import me.juan.troll.troll.TrollMetadata;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class SpiderTroll implements ITroll {
    @Override
    public TrollMetadata getMetadata(String player) {
        return new TrollMetadata("&c&lSpider Troll", XMaterial.SPIDER_EYE.parseMaterial(), 0, Lists.newArrayList(
                "",
                "&7Click to troll " + player + " with Spiders"
        ));
    }

    @Override
    public void troll(Player player) {
        for (int i = 0; i < 2; i++){
            Location location = player.getLocation();
            double newC = location.getX()+1;
            location.setX(newC);
            player.getWorld().spawnEntity(location, EntityType.SPIDER);
            location = player.getLocation();
            newC = location.getX()-1;
            location.setX(newC);
            player.getWorld().spawnEntity(location, EntityType.SPIDER);
            location = player.getLocation();
            newC = location.getZ()+1;
            location.setZ(newC);
            player.getWorld().spawnEntity(location, EntityType.SPIDER);
            location = player.getLocation();
            newC = location.getZ()-1;
            location.setZ(newC);
            player.getWorld().spawnEntity(location, EntityType.SPIDER);
        }



    }
}
