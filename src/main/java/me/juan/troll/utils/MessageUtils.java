package me.juan.troll.utils;

import org.bukkit.ChatColor;

public class MessageUtils {
    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
