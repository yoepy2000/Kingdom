package net.yenaq.kingdom.commands;

import net.yenaq.kingdom.Core;
import net.yenaq.kingdom.constants.Kingdom;
import net.yenaq.kingdom.constants.Profile;
import net.yenaq.kingdom.constants.Ranks;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by Yannick on 29-Apr-17.
 */
public class removeAlly implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Sorry, je moet een speler zijn om dit commando te kunnen uitvoeren.");
            return true;
        }
        Profile p = new Profile((Player) sender);
        if (((!p.getRank().toString().equalsIgnoreCase(Ranks.KONING.toString())) || p.getKingdom().getName().equalsIgnoreCase("Guest")) && !sender.isOp()) {
            p.sendMessage("&c&lERROR &7Je hebt geen permissie om dit commando te gebruiken.");
            return true;
        }
        if (args.length != 1) {
            p.sendMessage("&7Gebruik het commando op de volgende manier:");
            p.sendMessage("&c/removeally [kingdom]");
            return true;
        }

        Kingdom kingdom = p.getKingdom();
        String s1 = args[0].substring(0, 1).toUpperCase();
        String name = s1 + args[0].toLowerCase().substring(1);
        List<String> list = Core.getInstance().KingdomConfiguration.getStringList("Kingdoms." + p.getKingdom().getName() + ".Ally");

        if (!list.contains(name)) {
            p.sendMessage("&c&lERROR &7Je hebt geen alliantie met dit kingdom.");
            return true;
        }

        Kingdom target = new Kingdom(name);

        kingdom.removeAlly(target);
        target.removeAlly(kingdom);

        p.sendMessage("&7Je hebt je alliantie met &a" + name + " &7verbroken!");
        for (Player all : Bukkit.getOnlinePlayers()) {
            Profile players = new Profile(all);
            if (players.getKingdom().getName().equalsIgnoreCase(target.getName())) {
                players.sendMessage("");
                players.sendMessage("&7De koning van &c" + kingdom.getName() + " &7heeft jullie alliantie verbroken!");
                players.sendMessage("");
            }
        }

        return false;
    }

}
