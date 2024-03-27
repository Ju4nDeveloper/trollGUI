package org.plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.plugin.commands.Command;
import org.plugin.listener.InventoryListener;
import org.plugin.utils.MessageUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class TrollGUI extends JavaPlugin {
    private PluginDescriptionFile pdfFile = this.getDescription();
    private String version = pdfFile.getVersion();
    private FileConfiguration messages = null;
    private File messagesFile = null;
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&e[&e&lTrollGUI&e] &fhas been enabled &8(version: " + version + ")"));
        registerCommands();
        registerConfig();
        registerEvents();
        registerMessages();
    }

    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&e[&e&lTrollGUI&e] &fhas been disabled &8(version: " + version + ")"));
    }

    public void registerCommands(){
        this.getCommand("troll").setExecutor(new Command(this));
    }

    public void registerEvents(){
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new InventoryListener(this), this);
    }
    public void registerConfig(){
        File config = new File(this.getDataFolder(),"config.yml");
        String rutaConfig = config.getPath();
        if(!config.exists()) {
            this.getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }

    public FileConfiguration getMessages(){
        if (messages == null){
            reloadMessages();
        }
        return messages;
    }
    public void reloadMessages(){
        if (messages == null){
            messagesFile = new File(getDataFolder(), "messages.yml");
        }
        messages = YamlConfiguration.loadConfiguration(messagesFile);
        Reader defConfigStream;
        defConfigStream = new InputStreamReader(this.getResource("messages.yml"), StandardCharsets.UTF_8);
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            messages.setDefaults(defConfig);
        }
    }

    public void saveMessages(){
        try {
            messages.save(messagesFile);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void registerMessages(){
        messagesFile = new File(this.getDataFolder(), "messages.yml");
        if (!messagesFile.exists()){
            this.getMessages().options().copyDefaults(true);
            saveMessages();
        }
    }
}
