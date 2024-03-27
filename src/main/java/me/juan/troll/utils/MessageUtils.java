package org.plugin.utils;

import org.bukkit.ChatColor;
import org.plugin.TrollGUI;

public class MessageUtils {
    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
