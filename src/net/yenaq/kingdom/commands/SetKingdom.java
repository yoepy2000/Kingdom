package net.yenaq.kingdom.commands;

import net.yenaq.kingdom.Core;
import net.yenaq.kingdom.constants.Kingdom;
import net.yenaq.kingdom.constants.Profile;
import net.yenaq.kingdom.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Set;

/**
 * Created by Yannick on 19-Apr-17.
 */
public class SetKingdom implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length != 2) {
            sender.sendMessage(ChatUtil.format("&7Gebruik het commando op de volgende manier:"));
            sender.sendMessage(ChatUtil.format("&c/setkingdom [speler] [kingdom]"));
            return true;
        } if (sender.isOp() || (sender instanceof Player && (new Profile((Player) sender).getRank().getName().equalsIgnoreCase("Koning")))) {
            Player target = Bukkit.getOfflinePlayer(args[0]).getPlayer();
            if(target == null) {
                sender.sendMessage(ChatUtil.format("&c&lERROR &7die speler bestaat niet!"));
                return true;
            }
            Profile p = new Profile(target);
            ConfigurationSection section = Core.getInstance().KingdomConfiguration.getConfigurationSection("Kingdoms.");
            Set<String> kingdoms = section.getKeys(false);

            String name = "";
            for (String s : kingdoms) {
                if (args[1].equalsIgnoreCase(s)) {
                    name = s;
                    break;
                }
            }

            if (name.equalsIgnoreCase("Guest")) {
                sender.sendMessage(ChatUtil.format("&c&lERROR &7Je kunt geen mensen in het kingdom guest zetten!"));
                return true;
            }

            if (kingdoms.contains(name)) {
                Kingdom kingdom = new Kingdom(name);
                p.setKingdom(kingdom);
                if (p.getName().equalsIgnoreCase(sender.getName())) {
                    p.sendMessage("&7Je hebt je eigen kingdom verzet naar &a" + kingdom.getName() + "&7.");
                    return true;
                }
                sender.sendMessage(ChatUtil.format("&7Het kingdom van &a" + target.getName() + " &7is verzet naar &a" + kingdom.getName() + "&7."));
                p.sendMessage("&7Je kingdom is verzet naar &a" + kingdom.getName() + " &7door &a" + sender.getName() + "&7.");
                return true;
            }
            else {
                sender.sendMessage(ChatUtil.format("&c&lERROR &7Dit kingdom bestaat niet!"));
                return true;
            }

        } else {
            sender.sendMessage(ChatUtil.format("&c&lERROR &7Je hebt geen permissie om dit commando te gebruiken."));
            return true;
        }
    }

}
