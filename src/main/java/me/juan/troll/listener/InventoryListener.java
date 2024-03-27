package me.juan.troll.listener;

import com.cryptomorin.xseries.XMaterial;
import com.google.common.collect.Lists;
import me.juan.troll.TrollGUI;
import me.juan.troll.troll.ITroll;
import me.juan.troll.troll.TrollMetadata;
import me.juan.troll.troll.impl.CreeperTroll;
import me.juan.troll.troll.impl.SpiderTroll;
import me.juan.troll.troll.impl.VacioTroll;
import me.juan.troll.utils.MessageUtils;
import me.juan.troll.utils.Troll;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;

public class InventoryListener implements Listener {
    private final List<ITroll> trolls;
    private static TrollGUI plugin;

    public InventoryListener(TrollGUI trollGUI) {
        this.trolls = Lists.newArrayList(new CreeperTroll(), new SpiderTroll(), new VacioTroll());
        plugin = trollGUI;
    }

    public static void createInventoryPlayer(Player player) {
        FileConfiguration messages = plugin.getMessages();
        org.bukkit.inventory.Inventory inv = Bukkit.createInventory(null, 54, MessageUtils.color(messages.getString("Messages.playerInventoryName")));
        int i = 0;
        for (Player player1 : Bukkit.getOnlinePlayers()) {
            if (player.getName().equals(player.getName())){
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

        }
        player.openInventory(inv);
    }
    @EventHandler
    public void clikearInventarioJugadores(InventoryClickEvent event){
        FileConfiguration messages = plugin.getMessages();
        String titulo = MessageUtils.color(messages.getString("Messages.playerInventoryName"));
        String tituloM = ChatColor.stripColor(titulo);
        if (!ChatColor.stripColor(event.getView().getTitle()).equals(tituloM)){
            return;
        }else {
            if (event.getCurrentItem() == null){
                event.setCancelled(true);
                return;
            }
            if (event.getCurrentItem().getType() == Material.AIR || event.getSlotType() == null){
                event.setCancelled(true);
            }else {
                Player player = (Player) event.getWhoClicked();
                event.setCancelled(true);
                if (event.getInventory().equals(player.getOpenInventory().getTopInventory())){
                    SkullMeta meta = (SkullMeta) event.getCurrentItem().getItemMeta();
                    String name = meta.getOwner();

                    crearInventarioTroleos(player,name);
                }
                return;
            }
        }

    }

    public void crearInventarioTroleos(Player player, String jugadorTrolleado){
        FileConfiguration messages = plugin.getMessages();
        org.bukkit.inventory.Inventory inv = Bukkit.createInventory(null, 27, MessageUtils.color(messages.getString("Messages.trollsInventoryName").replace("%player%", jugadorTrolleado)));

        for (int i = 0; i < trolls.size(); i++) {
            TrollMetadata metadata = trolls.get(i).getMetadata(jugadorTrolleado);
            ItemStack item = new ItemStack(metadata.getMaterial(),1, (short) metadata.getData());
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(MessageUtils.color(metadata.getName()));
            List<String> lore = metadata.getLore();
            for (int c=0;c<lore.size();c++){
                lore.set(c, MessageUtils.color(lore.get(c)));
            }
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.setItem(i, item);
        }


        Troll troll = new Troll(jugadorTrolleado, player.getName());
        if (!plugin.jugadorEstarTrolleando(player.getName())){
            plugin.agregarTrolleo(troll);
        }

        player.openInventory(inv);
    }
    @EventHandler
    public void ClickearInventarioTrolleos(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        FileConfiguration messages = plugin.getMessages();

        String titulo = MessageUtils.color(messages.getString("Messages.trollsInventoryName").replace("%player%", ""));
        String tituloM = ChatColor.stripColor(titulo);
        if (!ChatColor.stripColor(event.getView().getTitle()).startsWith(tituloM))
            return;

        if (event.getCurrentItem() == null){
            event.setCancelled(true);
            return;
        }


        if (event.getCurrentItem().getType() == Material.AIR || event.getSlotType() == null){
            event.setCancelled(true);
        }else {
            event.setCancelled(true);

            Troll troll = plugin.getTroll(player.getName());
            Player jugadorTrolleado = Bukkit.getPlayer(troll.getJugadorTrolleado());

            if (jugadorTrolleado == null) {
                player.closeInventory();
                return;
            }

            if (trolls.isEmpty() || event.getSlot() >= trolls.size()) return;

            ITroll iTroll = trolls.get(event.getSlot());

            iTroll.troll(jugadorTrolleado);
            player.closeInventory();

        }
    }
    @EventHandler
    public void cerrarInventario(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();

        FileConfiguration messages = plugin.getMessages();

        String titulo = MessageUtils.color(messages.getString("Messages.trollsInventoryName").replace("%player%", ""));
        String tituloM = ChatColor.stripColor(titulo);
        if (!ChatColor.stripColor(event.getView().getTitle()).startsWith(tituloM))
            return;

        if (plugin.jugadorEstarTrolleando(player.getName())){
            plugin.removerTrolleo(player.getName());
        }

    }
}
