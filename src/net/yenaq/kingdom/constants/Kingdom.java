package net.yenaq.kingdom.constants;

import com.sun.org.apache.xerces.internal.xs.StringList;
import net.yenaq.kingdom.Core;
import net.yenaq.kingdom.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Yannick on 16-Apr-17.
 */
public class Kingdom {

    private ChatColor color;
    private String name;
    private Location spawn;
    private String prefix;
    private ConfigurationSection section;
    private List<String> allys;


    public Kingdom(String name) {
        section = Core.getInstance().KingdomConfiguration.getConfigurationSection("Kingdoms." + name);
        this.name = name;

        String[] values = section.getString("Spawn").split("/");
        World world = Bukkit.getWorld(values[0]);
        double x = Double.parseDouble(values[1]) + 0.5D;
        double y = Double.parseDouble(values[2]) + 0.5D;
        double z = Double.parseDouble(values[3]) + 0.5D;

        this.spawn = new Location(world, x, y, z);
        this.prefix = section.getString("Prefix");
        this.color = ChatColor.valueOf(section.getString("Color").toUpperCase());
        this.allys = section.getStringList("Ally");
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return ChatUtil.format(prefix);
    }

    public ChatColor getColor() {
        return color;
    }

    public Location getSpawn() {
        return spawn;
    }

    public void addAlly(Kingdom target) {
        List<String> list = section.getStringList("Ally");
        if (!list.contains(target.getName())) {
            list.add(target.getName());
        }
        section.set("Ally", list);
        Core.getInstance().saveCustomConfig(Core.getInstance().KingdomConfigurationFile, Core.getInstance().KingdomConfiguration);
    }

    public boolean isAlly(Kingdom target) {
        List<String> list = section.getStringList("Ally");
        return list.contains(target.getName());
    }

    public void removeAlly(Kingdom target) {
        List<String> list = section.getStringList("Ally");
        if (list.contains(target.getName())) {
            list.remove(target.getName());
        }
        section.set("Ally", list);
        Core.getInstance().saveCustomConfig(Core.getInstance().KingdomConfigurationFile, Core.getInstance().KingdomConfiguration);
    }

    public void setSpawn(Location location) {
        String loc = location.getWorld().getName() + "/" + location.getBlockX() + "/" + location.getBlockY() + "/" + location.getBlockZ();
        section.set("Spawn", loc);
        Core.getInstance().saveCustomConfig(Core.getInstance().KingdomConfigurationFile, Core.getInstance().KingdomConfiguration);
    }

    public List getAllys() {
        return allys;
    }
}
