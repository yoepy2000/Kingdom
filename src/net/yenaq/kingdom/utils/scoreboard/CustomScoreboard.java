package net.yenaq.kingdom.utils.scoreboard;

import javafx.scene.control.Tab;
import net.yenaq.kingdom.constants.Profile;
import net.yenaq.kingdom.utils.TabList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

/**
 * Created by Yannick on 27-Apr-17.
 */
public class CustomScoreboard {

    private Player player;

    private Scoreboard scoreboard;

    public CustomScoreboard(Player player) {
        this.player = player;
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        player.setScoreboard(scoreboard);
    }

    public Team getTeamByPlayer(Player player) {
        Profile profile = new Profile(player);
        return scoreboard.getTeam(TabList.getKingdoms().get(TabList.getKingdomByName(profile.getKingdom().getName())) + player.getName());
    }

    public void setPrefix(Player player, String prefix) {
        Profile profile = new Profile(player);
        if(getTeamByPlayer(player) == null)
            scoreboard.registerNewTeam(TabList.getKingdoms().get(TabList.getKingdomByName(profile.getKingdom().getName())) + player.getName());
        Team team = getTeamByPlayer(player);
        if(!team.hasEntry(player.getName()))
            team.addEntry(player.getName());
        team.setPrefix(prefix);
    }
}
