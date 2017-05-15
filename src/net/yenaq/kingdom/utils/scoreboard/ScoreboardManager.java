package net.yenaq.kingdom.utils.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Yannick on 27-Apr-17.
 */
public class ScoreboardManager implements Listener {

    private static ScoreboardManager scoreboardManager;
    public ScoreboardHandler handler = new KingdomScoreboard();
    public Map<UUID, CustomScoreboard> scoreboards = new HashMap<>();

    public ScoreboardManager() {
        scoreboardManager = this;
    }

    public static ScoreboardManager getInstance() {
        return scoreboardManager;
    }

    public void setScoreboard(Player player) {
        CustomScoreboard customScoreboard = new CustomScoreboard(player);
        scoreboards.put(player.getUniqueId(), customScoreboard);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        setScoreboard(player);
        CustomScoreboard scoreboard = scoreboards.get(player.getUniqueId());
        for(Map.Entry<UUID, CustomScoreboard> entry : scoreboards.entrySet()) {
            scoreboard.setPrefix(Bukkit.getPlayer(entry.getKey()), handler.getPrefix(Bukkit.getPlayer(entry.getKey())));
            entry.getValue().setPrefix(player, handler.getPrefix(player));
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        if(scoreboards.containsKey(event.getPlayer().getUniqueId())) {
            scoreboards.remove(event.getPlayer().getUniqueId());
        }
    }

}
