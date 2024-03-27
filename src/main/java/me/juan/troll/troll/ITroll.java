package me.juan.troll.troll;

import org.bukkit.entity.Player;

public interface ITroll {

    TrollMetadata getMetadata(String player);

    void troll(Player player);

}
