package net.yenaq.kingdom.commands;

import net.yenaq.kingdom.constants.Profile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Yannick on 23-Apr-17.
 */
public class ToggleChat implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Profile p = new Profile((Player) sender);

            if (p.isMuted()) {
                p.setMuted(false);
                p.sendMessage("&7Je ontvangt nu &awel &7weer chatberichten.");
            } else {
                p.setMuted(true);
                p.sendMessage("&7Je ontvangt nu &cgeen &7chatberichten meer.");
            }
        }



        return false;
    }

}
