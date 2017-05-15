package net.yenaq.kingdom.listeners;

import net.yenaq.kingdom.constants.Profile;
import net.yenaq.kingdom.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by Yannick on 17-Apr-17.
 */
public class PlayerLeave implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        e.setQuitMessage(null);
        for (Player all : Bukkit.getOnlinePlayers()) {
            Profile players = new Profile(all);
            if (!players.isMuted()) {
                players.sendMessage("&8[&7-&8] " + new Profile(player).getKingdom().getColor() + player.getName());
            }
        }
    }

}
