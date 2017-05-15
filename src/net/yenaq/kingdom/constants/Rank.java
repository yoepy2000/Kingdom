package net.yenaq.kingdom.constants;

import net.yenaq.kingdom.Core;
import net.yenaq.kingdom.utils.ChatUtil;
import org.bukkit.configuration.ConfigurationSection;

/**
 * Created by Yannick on 15-May-17.
 */
public class Rank {

    private ConfigurationSection section;
    private String prefix;
    private String name;

    public Rank(String name) {
        this.section = Core.getInstance().RanksConfiguration.getConfigurationSection("Ranks." + name);
        this.name = name;
        prefix = ChatUtil.format(section.getString("Prefix"));
    }

    public String getPrefix() {
        return prefix;
    }

    public String getName() {
        return name;
    }
}
