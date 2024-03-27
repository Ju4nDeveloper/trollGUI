package org.plugin.listener;

import com.cryptomorin.xseries.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.plugin.TrollGUI;
import org.plugin.utils.MessageUtils;

public class InventoryListener implements Listener {
    private static TrollGUI plugin;

    public InventoryListener(TrollGUI trollGUI) {
        plugin = trollGUI;
    }

    public static void createInventoryPlayer(Player player) {
        // ya se xq, tienes las mismas clases? oooo, creo q si, la de inventory
        FileConfiguration messages = plugin.getMessages();
        org.bukkit.inventory.Inventory inv = Bukkit.createInventory(null, 54, MessageUtils.color(messages.getString("Messages.playerInventoryName")));
        int i = 0;
        for (Player player1 : Bukkit.getOnlinePlayers()) {
            ItemStack cabeza = XMaterial.PLAYER_HEAD.parseItem();
            SkullMeta meta = (SkullMeta) cabeza.getItemMeta();
            meta.setOwner(player.getName());
            cabeza.setItemMeta(meta);
            inv.setItem(i, cabeza);
            i++;

            if (i == 54) {
                break;
            }
        }
        player.openInventory(inv);
    }
}
