package net.yenaq.kingdom.initializers;


import net.yenaq.kingdom.Core;
import net.yenaq.kingdom.listeners.*;
import net.yenaq.kingdom.utils.TabList;
import net.yenaq.kingdom.utils.scoreboard.ScoreboardManager;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class Initializer {

    public Initializer () {
        RegisterListeners();
    }

    public void RegisterListeners() {
        Listener[] listeners = {new PlayerJoin(), new PlayerLeave(), new PlayerChat(), new TabList(), new ScoreboardManager(), new PlayerRespawn(), new EntityDamage(), new PlayerDeath()};
        for (Listener listener : listeners)
            Bukkit.getServer().getPluginManager().registerEvents(listener, Core.getInstance());
    }

}
