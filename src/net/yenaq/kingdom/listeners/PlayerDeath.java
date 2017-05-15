package net.yenaq.kingdom.listeners;

import net.yenaq.kingdom.constants.Profile;
import net.yenaq.kingdom.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import static org.bukkit.event.entity.EntityDamageEvent.*;

/**
 * Created by Yannick on 07-May-17.
 */
public class PlayerDeath implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Profile player = new Profile(e.getEntity());

        if (e.getEntity().getLastDamageCause().getCause() == DamageCause.ENTITY_ATTACK) {
            if (e.getEntity().getKiller() instanceof Player) {
                Profile killer = new Profile(e.getEntity().getKiller());
                e.setDeathMessage(ChatUtil.format(player.getKingdom().getPrefix() + "&7 " + player.getName() + " &7is verwond door " + killer.getKingdom().getPrefix() + "&7 " + killer.getName()));
                return;
            }
        }
        if (e.getEntity().getLastDamageCause().getCause() == DamageCause.PROJECTILE) {
            if (e.getEntity().getKiller() instanceof Player) {
                Profile killer = new Profile(e.getEntity().getKiller());
                e.setDeathMessage(ChatUtil.format(player.getKingdom().getPrefix() + "&7 " + player.getName() + " &7is verwond door " + killer.getKingdom().getPrefix() + "&7 " + killer.getName()));
                return;
            }
        }
        e.setDeathMessage(null);


    }

}
