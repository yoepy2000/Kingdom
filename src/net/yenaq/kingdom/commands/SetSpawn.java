package net.yenaq.kingdom.commands;

import net.yenaq.kingdom.constants.Profile;
import net.yenaq.kingdom.utils.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Yannick on 07-May-17.
 */
public class SetSpawn implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if ((sender instanceof Player)) {
            Profile p = new Profile((Player) sender);
            if (args.length == 0) {
                if (p.getKingdom().getName().equalsIgnoreCase("Guest")) {
                    p.sendMessage("&c&lERROR &7je kunt dit niet doen als je niet in een kingdom zit.");
                    return true;
                }
                if (sender.isOp() || p.getRank().getName().equalsIgnoreCase("Koning")) {
                    p.getKingdom().setSpawn(((Player) sender).getLocation());
                    p.sendMessage("&aDe spawn voor je kingdom is gezet!");
                    return true;
                } else {
                    p.sendMessage("&c&lERROR &7Je hebt geen permissie om dit commando te gebruiken.");
                }
            } else {
                return true;
            }

        } else {
            sender.sendMessage("Je moet een speler zijn om dit commando te kunnen gebruiken.");
            return true;
        }

        return false;
    }

}
