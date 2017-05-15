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

/**
 * Created by Yannick on 17-Apr-17.
 */
public class SetRank implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length != 2) {
            sender.sendMessage(ChatColor.RED + "Gebruik het commando op de volgende manier:");
            sender.sendMessage(ChatColor.RED + "/setrank [speler] [rank]");
            return true;
        } if (sender.isOp() || (sender instanceof Player && (new Profile((Player) sender).getRank().getName().equalsIgnoreCase("Koning")))) {
            Player target = Bukkit.getOfflinePlayer(args[0]).getPlayer();
            if(target == null) {
                sender.sendMessage(ChatColor.RED + "Deze speler bestaat niet!");
                return true;
            }
            Profile p = new Profile(target);
            if (sender.isOp() || (sender instanceof Player && (new Profile((Player) sender).getKingdom().getName().equalsIgnoreCase(p.getKingdom().getName())))) {
                ConfigurationSection section = Core.getInstance().KingdomConfiguration.getConfigurationSection("Kingdoms.");

                String s1 = args[1].substring(0, 1).toUpperCase();
                String name = s1 + args[1].toLowerCase().substring(1);

                if (section.contains(name)) {
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
                    sender.sendMessage(ChatColor.RED + "Deze rank bestaat niet!");
                    return true;
                }
            }
            else {
                sender.sendMessage(ChatColor.RED + "Je moet in hetzelfde kingdom zitten als de speler aan wie je de rank wilt geven!");
                return true;
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Je hebt geen permissie om dit commando te gebruiken.");
            return true;
        }

    }

}
