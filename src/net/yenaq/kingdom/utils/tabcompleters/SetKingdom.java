package net.yenaq.kingdom.utils.tabcompleters;

import net.yenaq.kingdom.Core;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by Yannick on 15-May-17.
 */
public class SetKingdom implements org.bukkit.command.TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("setkingdom") && args.length == 2) {
            Set<String> names = Core.getInstance().KingdomConfiguration.getConfigurationSection("Kingdoms").getKeys(false);
            List<String> list = new ArrayList<>();
            if (!args[1].equalsIgnoreCase("")) {
                for (String s : names) {
                    if (!s.equalsIgnoreCase("guest") && s.toLowerCase().startsWith(args[1].toLowerCase())) {
                        list.add(s);
                    }
                }
            }
            else {
                for (String s : names) {
                    if (!s.equalsIgnoreCase("guest")) {
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
