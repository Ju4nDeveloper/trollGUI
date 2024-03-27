package me.juan.troll;

import me.juan.troll.listener.ExitListener;
import me.juan.troll.utils.Troll;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import me.juan.troll.commands.Command;
import me.juan.troll.listener.InventoryListener;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class TrollGUI extends JavaPlugin {
    private PluginDescriptionFile pdfFile = this.getDescription();
    private String version = pdfFile.getVersion();
    private FileConfiguration messages = null;
    private File messagesFile = null;
    private ArrayList<Troll> trolleos;
    public void onEnable() {
        trolleos = new ArrayList<Troll>();
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
        pluginManager.registerEvents(new ExitListener(this), this);
    }

    public void agregarTrolleo(Troll troll){
        trolleos.add(troll);
    }
    public boolean jugadorEstarTrolleando(String player){
        for (Troll trolleo : trolleos) {
            if (trolleo.getJugador().equalsIgnoreCase(player)) {
                return true;
            }
        }
        return false;
    }

    public Troll getTroll(String player){
        for (Troll trolleo : trolleos) {
            if (trolleo.getJugador().equalsIgnoreCase(player)) {
                return trolleo;
            }
        }
        return null;
    }

    public void removerTrolleo(String player){
        for (int i=0;i<trolleos.size();i++){
            if (trolleos.get(i).getJugador().equalsIgnoreCase(player)){
                trolleos.remove(i);
            }
        }
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
