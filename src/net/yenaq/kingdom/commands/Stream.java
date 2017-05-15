package net.yenaq.kingdom.commands;

import net.yenaq.kingdom.Core;
import net.yenaq.kingdom.constants.Profile;
import net.yenaq.kingdom.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Yannick on 07-May-17.
 */
public class Stream implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 0) {
            if (Core.getInstance().streaming.contains(sender)) {
                Core.getInstance().stream = false;
                Core.getInstance().streaming.remove(sender);
                sender.sendMessage(ChatUtil.format("&7Streammodus &cuitgezet&7."));
                return true;
            }
            if (sender.isOp() || (sender instanceof Player && (new Profile((Player) sender).getRank().getName().equalsIgnoreCase("Koning") || new Profile((Player) sender).getRank().getName().equalsIgnoreCase("Hertog")))) {
                if (Core.getInstance().stream) {
                    sender.sendMessage(ChatUtil.format("&7Op het moment staat de streammodus &aaan&7. Weet je zeker dat je deze wilt uitzetten? Typ dan &abinnen 20 seconden &7nog een keer &a/stream&7."));
                    Core.getInstance().streaming.add(sender);
                    Core.getInstance().getServer().getScheduler().scheduleSyncDelayedTask(Core.getInstance(), () -> Core.getInstance().streaming.remove(sender), (long) 20 * 20);
                    return true;
                } else {
                    Core.getInstance().stream = true;
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        all.sendMessage("");
                        all.sendMessage(ChatUtil.format("&aStreammodus staat nu aan! Heb je niks met deze stream te maken, log dan nu uit!"));
                        all.sendMessage("");
                        all.playSound(all.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 1);
                    }
                    return true;
                }
            } else {
                sender.sendMessage(ChatUtil.format("&c&lERROR &7Je hebt geen permissie om dit commando te gebruiken."));
            }

            return false;
        } else {
            return true;
        }
    }

}
