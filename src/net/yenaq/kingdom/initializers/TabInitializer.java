package net.yenaq.kingdom.initializers;

import net.yenaq.kingdom.Core;
import net.yenaq.kingdom.utils.tabcompleters.Ally;
import net.yenaq.kingdom.utils.tabcompleters.SetKingdom;
import net.yenaq.kingdom.utils.tabcompleters.SetRank;
import net.yenaq.kingdom.utils.tabcompleters.removeAlly;

/**
 * Created by Yannick on 15-May-17.
 */
public class TabInitializer {

    public TabInitializer() {
        Core.getInstance().getCommand("setkingdom").setTabCompleter(new SetKingdom());
        Core.getInstance().getCommand("ally").setTabCompleter(new Ally());
        Core.getInstance().getCommand("removeally").setTabCompleter(new removeAlly());
        Core.getInstance().getCommand("setrank").setTabCompleter(new SetRank());
    }

}
