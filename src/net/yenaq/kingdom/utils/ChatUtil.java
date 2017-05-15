package net.yenaq.kingdom.utils;

import net.yenaq.kingdom.Core;
import org.bukkit.ChatColor;

/**
 * Created by Yannick on 17-Apr-17.
 */
public class ChatUtil {

    public static String format(String input) {
        try {
            return ChatColor.translateAlternateColorCodes('&', input);
        } catch(Exception e) {
            Core.getInstance().getServer().getConsoleSender().sendMessage("§cHet is niet gelukt om tekst naar kleur om te zetten. (Misschien is er iets fout gegaan in een prefix)");
            return input;
        }
    }


}
