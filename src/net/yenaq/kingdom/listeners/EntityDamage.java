package net.yenaq.kingdom.listeners;

import net.yenaq.kingdom.Core;
import net.yenaq.kingdom.constants.Profile;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by Yannick on 28-Apr-17.
 */
public class EntityDamage implements Listener {

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            Player target = (Player) e.getEntity();
            if (e.getDamager() instanceof Player) {
                if (!isEnemy(target, (Player) e.getDamager())) {
                    e.setCancelled(true);
                    e.setDamage(0.0);
                    return;
                }
            }
            if (e.getDamager() instanceof Arrow) {
                Arrow arrow = (Arrow) e.getDamager();
                if (arrow.getShooter() instanceof Player) {
                    if (!isEnemy(target, (Player) arrow.getShooter())) {
                        e.setCancelled(true);
                        e.setDamage(0.0);
                        return;
                    }
                }
            }
            if (e.getDamager() instanceof FishHook) {
                FishHook fishHook = (FishHook) e.getDamager();
                if (fishHook.getShooter() instanceof Player) {
                    if (!isEnemy(target, (Player) fishHook.getShooter())) {
                        e.setCancelled(true);
                        e.setDamage(0.0);
                        return;
                    }
                }
            }
            if (e.getDamager() instanceof Egg) {
                Egg egg = (Egg) e.getDamager();
                if (egg.getShooter() instanceof Player) {
                    if (!isEnemy(target, (Player) egg.getShooter())) {
                        e.setCancelled(true);
                        e.setDamage(0.0);
                        return;
                    }
                }
            }
            if (e.getDamager() instanceof Snowball) {
                Snowball snowball = (Snowball) e.getDamager();
                if (snowball.getShooter() instanceof Player) {
                    if (!isEnemy(target, (Player) snowball.getShooter())) {
                        e.setCancelled(true);
                        e.setDamage(0.0);
                        return;
                    }
                }
            }
        }
    }

    private boolean isEnemy(Player targett, Player damagerr) {
        Profile target = new Profile(targett);
        Profile damager = new Profile(damagerr);

        return !(target.getKingdom().getName().equalsIgnoreCase(damager.getKingdom().getName()) || target.getKingdom().isAlly(damager.getKingdom()));
    }
}
