package net.yenaq.kingdom.listeners;

import net.yenaq.kingdom.Core;
import net.yenaq.kingdom.constants.Profile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

/**
 * Created by Yannick on 28-Apr-17.
 */
public class PlayerRespawn implements Listener {

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Profile p = new Profile(e.getPlayer());
        if (!p.getKingdom().getName().equalsIgnoreCase("Guest")) {
            Core.getInstance().getServer().getScheduler().scheduleSyncDelayedTask(Core.getInstance(), () -> e.getPlayer().teleport(p.getKingdom().getSpawn()), 2L);
        }
    }

}
