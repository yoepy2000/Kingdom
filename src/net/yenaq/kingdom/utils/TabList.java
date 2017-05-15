package net.yenaq.kingdom.utils;

import net.yenaq.kingdom.Core;
import net.yenaq.kingdom.constants.Kingdom;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yannick on 23-Apr-17.
 */
public class TabList implements Listener {

    private static Map<Kingdom, Integer> kingdoms = new HashMap<>();
    private static Scoreboard scoreboard;

    public TabList() {
        int position = 0;
        for(String kingdomss : Core.getInstance().KingdomConfiguration.getConfigurationSection("Kingdoms").getKeys(false)) {
            if (!kingdomss.equalsIgnoreCase("guest")) {
                Kingdom kingdom = new Kingdom(kingdomss);
                kingdoms.put(kingdom, position);
                position++;
            }
        }
        kingdoms.put(new Kingdom("Guest"), position);

        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        for(Map.Entry<Kingdom, Integer> entry : kingdoms.entrySet()) {
            Team team = scoreboard.registerNewTeam(entry.getValue() + entry.getKey().getName());
            team.setPrefix(entry.getKey().getColor().toString());
        }
    }

    public static Map<Kingdom, Integer> getKingdoms() {
        return kingdoms;
    }

    public static Scoreboard getScoreboard() {
        return scoreboard;
    }

    public static Kingdom getKingdomByName(String name) {
        for(Map.Entry<Kingdom, Integer> entry : kingdoms.entrySet()) {
            if(entry.getKey().getName().equalsIgnoreCase(name))
                return entry.getKey();
        }
        return null;
    }

}
