package me.juan.troll.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class ExitListener implements Listener {

    private me.juan.troll.TrollGUI plugin;
    public ExitListener(me.juan.troll.TrollGUI plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void alSalir(PlayerQuitEvent event){
        Player player = event.getPlayer();
        if (plugin.jugadorEstarTrolleando(player.getName())){
            plugin.removerTrolleo(player.getName());
        }
    }
}
