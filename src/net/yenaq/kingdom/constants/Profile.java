package net.yenaq.kingdom.constants;

import com.sun.org.apache.xerces.internal.xs.StringList;
import net.yenaq.kingdom.Core;
import net.yenaq.kingdom.utils.ChatUtil;
import net.yenaq.kingdom.utils.TabList;
import net.yenaq.kingdom.utils.scoreboard.CustomScoreboard;
import net.yenaq.kingdom.utils.scoreboard.ScoreboardManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Yannick on 17-Apr-17.
 */
public class Profile {

    private Kingdom kingdom;
    private Player player;
    private Rank rank;
    private boolean isMuted;
    private ConfigurationSection section;


    public Profile(Player player) {
        this.player = player;
        this.section = Core.getInstance().PlayerDataConfiguration.getConfigurationSection("Players." + player.getUniqueId());
        this.kingdom = new Kingdom(section.getString("Kingdom"));
        this.rank = new Rank(section.getString("Rank"));
        this.isMuted = section.getBoolean("Muted");
    }

    public void sendMessage(String message) {
        player.sendMessage(ChatUtil.format(message));
    }

    public Kingdom getKingdom() {
        return kingdom;
    }

    public void setKingdom(Kingdom kingdom) {
        section.set("Kingdom", kingdom.getName());
        CustomScoreboard scoreboard = ScoreboardManager.getInstance().scoreboards.get(player.getUniqueId());
        for(Map.Entry<UUID, CustomScoreboard> entry : ScoreboardManager.getInstance().scoreboards.entrySet()) {
            scoreboard.setPrefix(Bukkit.getPlayer(entry.getKey()), ScoreboardManager.getInstance().handler.getPrefix(Bukkit.getPlayer(entry.getKey())));
            entry.getValue().setPrefix(player, ScoreboardManager.getInstance().handler.getPrefix(player));
        }
        Core.getInstance().saveCustomConfig(Core.getInstance().PlayerDataFile, Core.getInstance().PlayerDataConfiguration);
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        section.set("Rank", rank.getName());
        Core.getInstance().saveCustomConfig(Core.getInstance().PlayerDataFile, Core.getInstance().PlayerDataConfiguration);
    }


    public boolean isMuted() {
        return isMuted;
    }

    public void setMuted(boolean muted) {
        section.set("Muted", muted);
        Core.getInstance().saveCustomConfig(Core.getInstance().PlayerDataFile, Core.getInstance().PlayerDataConfiguration);
    }

    public String getName() {
        return player.getName();
    }
}
