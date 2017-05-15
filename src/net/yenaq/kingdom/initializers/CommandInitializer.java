package net.yenaq.kingdom.initializers;


import net.yenaq.kingdom.Core;
import net.yenaq.kingdom.commands.*;


public class CommandInitializer {

    public CommandInitializer() {
        Core.getInstance().getCommand("setrank").setExecutor(new SetRank());
        Core.getInstance().getCommand("setkingdom").setExecutor(new SetKingdom());
        Core.getInstance().getCommand("togglechat").setExecutor(new ToggleChat());
        Core.getInstance().getCommand("ally").setExecutor(new Ally());
        Core.getInstance().getCommand("removeally").setExecutor(new removeAlly());
        Core.getInstance().getCommand("setkingdomspawn").setExecutor(new SetSpawn());
        Core.getInstance().getCommand("stream").setExecutor(new Stream());
    }

}
