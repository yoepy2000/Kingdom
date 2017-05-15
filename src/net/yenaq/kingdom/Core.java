package net.yenaq.kingdom;

import net.yenaq.kingdom.constants.Kingdom;
import net.yenaq.kingdom.initializers.CommandInitializer;
import net.yenaq.kingdom.initializers.Initializer;
import net.yenaq.kingdom.initializers.TabInitializer;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yannick on 16-Apr-17.
 */
public class Core extends JavaPlugin {

    public static Core getInstance(){
        return Core.getPlugin(Core.class);
    }

    public Map<Player, Kingdom> allys = new HashMap<>();

    public boolean stream;
    public ArrayList<CommandSender> streaming = new ArrayList<>();

    public YamlConfiguration PlayerDataConfiguration = new YamlConfiguration();
    public File PlayerDataFile = new File(getDataFolder(), "PlayerData.yml");

    public YamlConfiguration KingdomConfiguration = new YamlConfiguration();
    public File KingdomConfigurationFile = new File(getDataFolder(), "KingdomData.yml");

    public YamlConfiguration RanksConfiguration = new YamlConfiguration();
    public File RanksConfigurationFile = new File(getDataFolder(), "RanksData.yml");


    public void saveCustomConfig(File file, FileConfiguration config) {
        try {
            config.save(file);
        }
        catch(IOException e) {
            e.printStackTrace();
            System.out.println("An error occured while attempting to save file " + file);
        }
    }

    public void onEnable() {
        LoadConfig();
        new Initializer();
        new CommandInitializer();
        new TabInitializer();
        stream = false;

        getServer().getConsoleSender().sendMessage("§aKingdom plugin has succesfully been enabled!");
    }

    public void onDisable() {

        getServer().getConsoleSender().sendMessage("§cKingdom plugin has succesfully been disabled!");

    }

    private void LoadConfig() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }

        // load configuration for file KingdomData.yml
        if (!KingdomConfigurationFile.exists()) {
            saveResource("KingdomData.yml", false);
        }KingdomConfiguration = YamlConfiguration.loadConfiguration(KingdomConfigurationFile);

        // load configuration for file PlayerData.yml
        if (!PlayerDataFile.exists()) {
            saveResource("PlayerData.yml", false);
        }PlayerDataConfiguration = YamlConfiguration.loadConfiguration(PlayerDataFile);

        // load configuration for file RanksData.yml
        if (!RanksConfigurationFile.exists()) {
            saveResource("RanksData.yml", false);
        }RanksConfiguration = YamlConfiguration.loadConfiguration(RanksConfigurationFile);

        saveCustomConfig(PlayerDataFile, PlayerDataConfiguration);
        saveCustomConfig(KingdomConfigurationFile, KingdomConfiguration);
        saveCustomConfig(RanksConfigurationFile, RanksConfiguration);

    }



}
