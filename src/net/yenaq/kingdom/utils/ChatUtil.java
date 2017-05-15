package net.yenaq.kingdom.utils;

import org.bukkit.ChatColor;

/**
 * Created by Yannick on 17-Apr-17.
 */
public class ChatUtil {

    public static String format(String input) {
        try {
            return ChatColor.translateAlternateColorCodes('&', input);
        } catch(Exception e) {
            return null;
        }
    }


}
