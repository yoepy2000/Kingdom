package net.yenaq.kingdom.listeners;

import net.yenaq.kingdom.Core;
import net.yenaq.kingdom.constants.Kingdom;
import net.yenaq.kingdom.constants.Profile;
import net.yenaq.kingdom.constants.Ranks;
import net.yenaq.kingdom.utils.ChatUtil;
import net.yenaq.kingdom.utils.TabList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Team;

/**
 * Created by Yannick on 16-Apr-17.
 */
public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        FileConfiguration config = Core.getInstance().PlayerDataConfiguration;
        if (!config.contains("Players." + player.getUniqueId())) {
            config.set("Players." + player.getUniqueId() + ".Kingdom", "Guest");
            config.set("Players." + player.getUniqueId() + ".Rank", Ranks.SPELER.toString());
            config.set("Players." + player.getUniqueId() + ".Muted", false);
            player.sendMessage(ChatUtil.format("&aWelkom op de kingdom, " + player.getName() + "! Omdat je voor het eerst joint ben je een guest, wat betekent dat je nog niet in een kingdom zit. Wacht even tot je koning je in je kingdom zet."));
        }

        config.set("Players." + player.getUniqueId() + ".Name", player.getName());
        e.setJoinMessage(null);
        for (Player all : Bukkit.getOnlinePlayers()) {
            Profile players = new Profile(all);
            if (!players.isMuted()) {
                players.sendMessage("&8[&7+&8] " + new Profile(player).getKingdom().getColor() + player.getName());
            }
        }
        if (Core.getInstance().stream) {
            Core.getInstance().getServer().getScheduler().scheduleSyncDelayedTask(Core.getInstance(), () -> {
                player.sendMessage("");
                player.sendMessage(ChatUtil.format("&AStreammodus staat op het moment aan! Heb je niks met deze stream te maken, log dan nu uit!"));
                player.sendMessage("");
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 1);
            }, 10L);

        }



        Core.getInstance().saveCustomConfig(Core.getInstance().PlayerDataFile, config);
    }

}
