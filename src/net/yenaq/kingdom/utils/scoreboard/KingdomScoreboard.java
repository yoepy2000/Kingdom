package net.yenaq.kingdom.utils.scoreboard;

import net.yenaq.kingdom.constants.Profile;
import net.yenaq.kingdom.utils.ChatUtil;
import org.bukkit.entity.Player;

/**
 * Created by Yannick on 27-Apr-17.
 */
public class KingdomScoreboard implements ScoreboardHandler {

    @Override
    public String getPrefix(Player player) {
        Profile profile = new Profile(player);
        return profile.getKingdom().getColor() + "";
    }

}


