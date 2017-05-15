package net.yenaq.kingdom.listeners;

import net.yenaq.kingdom.constants.Profile;
import net.yenaq.kingdom.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Created by Yannick on 18-Apr-17.
 */
public class PlayerChat implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {

        Profile p = new Profile(e.getPlayer());
        if (p.getKingdom().getName().equalsIgnoreCase("Guest")) {
            e.setCancelled(true);
            p.sendMessage("&cOmdat je nog niet in een kingdom zit kan je nog niks in de chat typen!");
            return;
        }
        else if(p.isMuted()) {
            if (e.getMessage().startsWith("!")) {
                e.setCancelled(true);
                for (Player players : Bukkit.getOnlinePlayers()) {
                    //if (players.getLocation().distance(e.getPlayer().getLocation()) < 50) {
                        players.sendMessage(ChatUtil.format("&7[G] " + p.getKingdom().getPrefix() + " " + p.getRank().getPrefix() + "&f" + e.getPlayer().getName() + ": " + e.getMessage().substring(1)));
                    //}
                }
                return;
            }
            for (Player players : Bukkit.getOnlinePlayers()) {
                e.setCancelled(true);
                //if (players.getLocation().distance(e.getPlayer().getLocation()) < 50) {
                    players.sendMessage(ChatUtil.format("&7[G] " + p.getKingdom().getPrefix() + " " + p.getRank().getPrefix() + "&f"+ e.getPlayer().getName() + ": " + e.getMessage()));
                //}
            }
            return;
        }
        else if (e.getMessage().startsWith("!")) {
            for (Player players : Bukkit.getOnlinePlayers()) {
                //if (players.getLocation().distance(e.getPlayer().getLocation()) < 50) {
                    players.sendMessage(ChatUtil.format("&7[G] " + p.getKingdom().getPrefix() + " " + p.getRank().getPrefix() + "&f"+ e.getPlayer().getName() + ": " + e.getMessage().substring(1)));
                //}
            }
            e.setCancelled(true);
            return;
        } else {
            e.setCancelled(true);
            for (Player players : Bukkit.getOnlinePlayers()) {
                Profile player = new Profile(players);
                if (player.getKingdom().getName().equalsIgnoreCase(p.getKingdom().getName()) && !player.isMuted()) {
                    if (p.getRank().getName().equalsIgnoreCase("Koning") || p.getRank().getName().equalsIgnoreCase("Hertog")) {
                        player.sendMessage(p.getKingdom().getPrefix() + " " + p.getRank().getPrefix() + e.getPlayer().getName() + ": " + e.getMessage());
                    } else {
                        player.sendMessage(p.getKingdom().getPrefix() + " " + p.getRank().getPrefix() + e.getPlayer().getName() + "&7: " + e.getMessage());
                    }
                }
            }
            return;
        }
    }

}
