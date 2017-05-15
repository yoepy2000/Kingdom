package net.yenaq.kingdom.commands;

import net.yenaq.kingdom.Core;
import net.yenaq.kingdom.constants.Kingdom;
import net.yenaq.kingdom.constants.Profile;
import net.yenaq.kingdom.constants.Rank;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by Yannick on 27-Apr-17.
 */
public class Ally implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Sorry, je moet een speler zijn om dit commando te kunnen uitvoeren.");
            return true;
        }
        Profile p = new Profile((Player) sender);
        if (((!p.getRank().getName().equals("Koning")) || p.getKingdom().getName().equalsIgnoreCase("Guest")) && !sender.isOp()) {
            p.sendMessage("&c&lERROR &7Je hebt geen permissie om dit commando te gebruiken.");
            return true;
        }
        if (args.length != 1) {
            p.sendMessage("&7Gebruik het commando op de volgende manier:");
            p.sendMessage("&c/ally [kingdom] &7of &c/ally accept");
            return true;
        }
        if (args[0].equalsIgnoreCase("accept")) {
            if (Core.getInstance().allys.containsKey(sender)) {

                p.getKingdom().addAlly(Core.getInstance().allys.get(sender));
                Core.getInstance().allys.get(sender).addAlly(p.getKingdom());

                p.sendMessage("&7Je hebt het alliantieverzoek van &a" + Core.getInstance().allys.get(sender).getName() + " &7geaccepteerd!");
                for (Player onlineplayers : Bukkit.getOnlinePlayers()) {
                    Profile players = new Profile(onlineplayers);
                    if (players.getKingdom().getName().equalsIgnoreCase(Core.getInstance().allys.get(sender).getName()) && players.getRank().getName().equalsIgnoreCase("Koning")) {
                        players.sendMessage("&7Je alliantieverzoek is geaccepteerd door de koning van &a" + p.getKingdom().getName() + "&7!");
                    }
                }
                Core.getInstance().allys.remove(sender);
                return true;
            }
            else {
                p.sendMessage("&c&lERROR &7Je hebt geen alliantieverzoeken!");
                return true;
            }
        }

        Kingdom kingdom = p.getKingdom();
        ConfigurationSection section = Core.getInstance().KingdomConfiguration.getConfigurationSection("Kingdoms.");
        String s1 = args[0].substring(0, 1).toUpperCase();
        String name = s1 + args[0].toLowerCase().substring(1);

        if (!section.contains(name)) {
            p.sendMessage("&c&lERROR &7Dit kingdom bestaat niet!");
            return true;
        }
        Kingdom target = new Kingdom(name);

        if (kingdom.isAlly(target)) {
            p.sendMessage("&c&lERROR &7Je hebt al een alliantie met dat kingdom!");
            return true;
        }

        ArrayList<String> ranks = new ArrayList<>();
        for (Player onlineplayers : Bukkit.getOnlinePlayers()) {
            Profile players = new Profile(onlineplayers);
            if (players.getKingdom().getName().equalsIgnoreCase(target.getName())) {
                ranks.add(players.getRank().getName());
            }
        }
        if (!ranks.contains("Koning")) {
            p.sendMessage("&c&lERROR &7De koning van " + target.getName() + " &7is niet online!");
            return true;
        }
        ranks.clear();

        for (Player onlineplayers : Bukkit.getOnlinePlayers()) {
            Profile players = new Profile(onlineplayers);

            if (players.getKingdom().getName().equalsIgnoreCase(target.getName()) && players.getRank().getName().equalsIgnoreCase("Koning")) {
                if (Core.getInstance().allys.containsKey(onlineplayers)) {
                    p.sendMessage("&c&lERROR &7deze koning wordt al door iemand uitgenodigd voor een alliantie. ");
                    return true;
                }
                Core.getInstance().allys.putIfAbsent(onlineplayers, kingdom);
                players.sendMessage("&a===================================");
                players.sendMessage("&7Je kingdom is uitgenodigd door de koning van &a" + p.getKingdom().getName() + " &7voor een alliantie!");
                players.sendMessage("&7Typ &a/ally accept &7om het alliantieverzoek te accepteren.");
                players.sendMessage("&a===================================");
                p.sendMessage("&7Je alliantieverzoek is aangekomen!");
            }

        }

        return false;

    }

}
