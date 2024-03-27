package me.juan.troll.commands;

import me.juan.troll.TrollGUI;
import me.juan.troll.listener.InventoryListener;
import me.juan.troll.utils.MessageUtils;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor {
    private final TrollGUI plugin;

    public Command(TrollGUI plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(MessageUtils.color("&e[" + plugin.getName() + "] &cNo puedes ejecutar comandos desde la consola"));
            return false;
        }

        Player player = (Player) sender;
        if (player.isOp() || player.hasPermission("trollgui.use")) {
            InventoryListener.createInventoryPlayer(player);
        } else {
            player.sendMessage(MessageUtils.color("&e[" + plugin.getName() + "] &cNo tienes permiso para usar este comando"));
        }
        return true;
    }
}
