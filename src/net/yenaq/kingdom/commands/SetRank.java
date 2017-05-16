package net.yenaq.kingdom.commands;

import net.yenaq.kingdom.Core;
import net.yenaq.kingdom.constants.Kingdom;
import net.yenaq.kingdom.constants.Profile;
import net.yenaq.kingdom.constants.Rank;
import net.yenaq.kingdom.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.Set;

/**
 * Created by Yannick on 17-Apr-17.
 */
public class SetRank implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length != 2) {
            sender.sendMessage(ChatUtil.format("&7Gebruik het commando op de volgende manier:"));
            sender.sendMessage(ChatUtil.format("&c/setrank [speler] [rank]"));
            return true;
        } if (sender.isOp() || (sender instanceof Player && (new Profile((Player) sender).getRank().getName().equalsIgnoreCase("Koning")))) {
            Player target = Bukkit.getOfflinePlayer(args[0]).getPlayer();
            if(target == null) {
                sender.sendMessage(ChatUtil.format("&c&lERROR &7Speler niet gevonden."));
                return true;
            }
            Profile p = new Profile(target);
            if (sender.isOp() || (sender instanceof Player && (new Profile((Player) sender).getKingdom().getName().equalsIgnoreCase(p.getKingdom().getName())))) {
                ConfigurationSection section = Core.getInstance().RanksConfiguration.getConfigurationSection("Ranks.");
                Set<String> ranks = section.getKeys(false);

                String name = "";
                for (String s : ranks) {
                    if (args[1].equalsIgnoreCase(s)) {
                        name = s;
                        break;
                    }
                }

                if (ranks.contains(name)) {
                    Rank rank = new Rank(name);
                    p.setRank(rank);
                    if (p.getName().equalsIgnoreCase(sender.getName())) {
                        p.sendMessage("&7Je hebt je eigen rank verzet naar &a" + rank.getName() + "&7.");
                        return true;
                    }
                    sender.sendMessage(ChatUtil.format("&7De rank van &a" + target.getName() + " &7is verzet naar &a" + rank.getName() + "&7."));
                    p.sendMessage("&7Je rank is verzet naar &a" + rank.getName() + " &7door &a" + sender.getName() + "&7.");
                    return true;
                }
                else {
                    sender.sendMessage(ChatUtil.format("&c&lERROR &7Die rank bestaat niet!"));
                    return true;
                }
            }
            else {
                sender.sendMessage(ChatUtil.format("&c&lERROR &7Je hebt geen permissie om de rank van deze speler aan te passen."));
                return true;
            }
        } else {
            sender.sendMessage(ChatUtil.format("&c&lERROR &7Je hebt geen permissie om dit commando te gebruiken."));
            return true;
        }

    }

}
