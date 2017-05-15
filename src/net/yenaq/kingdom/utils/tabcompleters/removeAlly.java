package net.yenaq.kingdom.utils.tabcompleters;

import net.yenaq.kingdom.constants.Profile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Yannick on 15-May-17.
 */
public class removeAlly implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("removeally") && args.length == 1) {
            if (!(sender instanceof Player)) {
                return null;
            }

            Profile p = new Profile((Player) sender);
            List<String> allys = p.getKingdom().getAllys();
            List<String> list = new ArrayList<>();

            if (!args[0].equalsIgnoreCase("")) {
                for (String s : allys) {
                    if (!s.equalsIgnoreCase("guest") && p.getKingdom().getAllys().contains(s) && s.toLowerCase().startsWith(args[0].toLowerCase())) {
                        list.add(s);
                    }
                }
            }
            else {
                for (String s : allys) {
                    if (!s.equalsIgnoreCase("guest") && p.getKingdom().getAllys().contains(s)) {
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
