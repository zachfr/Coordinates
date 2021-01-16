package me.zachary.coordinates.commands;

import me.zachary.coordinates.Coordinates;
import me.zachary.coordinates.listeners.JoinListener;
import me.zachary.zachcore.commands.Command;
import me.zachary.zachcore.commands.CommandResult;
import me.zachary.zachcore.utils.ChatUtils;
import me.zachary.zachcore.utils.xseries.messages.ActionBar;
import org.bukkit.entity.Player;

public class MainCommand extends Command {
    private Coordinates coordinates;
    public MainCommand(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String getCommand() {
        return "coords";
    }

    @Override
    public CommandResult onPlayerExecute(Player player, String[] strings) {
        if(player.hasPermission("coordinates.toggle")){
            if(strings.length != 0){
                if(strings[0].equalsIgnoreCase("on")){
                    JoinListener.activate.put(player, true);
                    player.sendMessage(ChatUtils.color(coordinates.getConfig().getString("Enable_Message")));
                }else if(strings[0].equalsIgnoreCase("off")){
                    JoinListener.activate.put(player, false);
                    ActionBar.clearActionBar(player);
                    player.sendMessage(ChatUtils.color(coordinates.getConfig().getString("Disable_Message")));
                }
            }else {
                player.sendMessage(ChatUtils.color(coordinates.getConfig().getString("No_Argument")));
            }
        }else{
            player.sendMessage(ChatUtils.color(coordinates.getConfig().getString("No_Permission")));
        }
        return CommandResult.COMPLETED;
    }

    @Override
    public CommandResult onConsoleExecute(boolean b, String[] strings) {
        return CommandResult.COMPLETED;
    }
}
