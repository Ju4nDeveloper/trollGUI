package me.juan.troll.troll.impl;

import com.cryptomorin.xseries.XMaterial;
import com.google.common.collect.Lists;
import me.juan.troll.troll.ITroll;
import me.juan.troll.troll.TrollMetadata;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class VacioTroll implements ITroll {
    @Override
    public TrollMetadata getMetadata(String player) {
        return new TrollMetadata("&7&lVacio Troll", XMaterial.BEDROCK.parseMaterial(), 0, Lists.newArrayList(
                "",
                "&7Click to troll " + player + " for teleport to -1"
        ));
    }

    @Override
    public void troll(Player player) {
        Location location = player.getLocation();
        location.setY(-1);
        player.teleport(location);
    }
}
