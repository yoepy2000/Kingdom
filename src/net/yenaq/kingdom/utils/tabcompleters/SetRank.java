package net.yenaq.kingdom.utils.tabcompleters;

import net.yenaq.kingdom.Core;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by Yannick on 15-May-17.
 */
public class SetRank implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("setrank") && args.length == 2) {
            Set<String> names = Core.getInstance().KingdomConfiguration.getConfigurationSection("Ranks").getKeys(false);
            List<String> list = new ArrayList<>();
            if (!args[1].equalsIgnoreCase("")) {
                for (String s : names) {
                    if (s.toLowerCase().startsWith(args[1].toLowerCase())) {
                        list.add(s);
                    }
                }
            }
            else {
                list.addAll(names);
            }
            Collections.sort(list);
            return list;
        }

        return null;
    }

}
