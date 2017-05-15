package net.yenaq.kingdom.constants;

import net.yenaq.kingdom.utils.ChatUtil;

/**
 * Created by Yannick on 17-Apr-17.
 */
public enum Ranks {

    SPELER(""),
    KONING(ChatUtil.format("&7[&6Koning&7] &f")),
    HERTOG(ChatUtil.format("&7[&2Hertog&7] &f")),
    GENERAAL(ChatUtil.format("&7[&cGeneraal&7] "));

    private String prefix;

    Ranks(String prefix) {
        this.prefix = prefix;
    }

    public static String getPrefix(Ranks rank) {
        return rank.prefix;
    }
}
