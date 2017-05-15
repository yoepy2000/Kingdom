package net.yenaq.kingdom.utils.tabcompleters;

import net.yenaq.kingdom.Core;
import net.yenaq.kingdom.constants.Profile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by Yannick on 15-May-17.
 */
public class Ally implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("ally") && args.length == 1) {
            if (!(sender instanceof Player)) {
                return null;
            }
            Profile p = new Profile((Player) sender);
            Set<String> names = Core.getInstance().KingdomConfiguration.getConfigurationSection("Kingdoms").getKeys(false);
            List<String> list = new ArrayList<>();
            if (!args[0].equalsIgnoreCase("")) {
                for (String s : names) {
                    if (!s.equalsIgnoreCase("guest") && !p.getKingdom().getAllys().contains(s) && !p.getKingdom().getName().equalsIgnoreCase(s) && s.toLowerCase().startsWith(args[0].toLowerCase())) {
                        list.add(s);
                    }
                }
            }
            else {
                for (String s : names) {
                    if (!s.equalsIgnoreCase("guest") && !p.getKingdom().getAllys().contains(s) && !p.getKingdom().getName().equalsIgnoreCase(s)) {
                        list.add(s);
                    }
                }
            }
            Collections.sort(list);
            return list;
        }


        return null;
    }

}
