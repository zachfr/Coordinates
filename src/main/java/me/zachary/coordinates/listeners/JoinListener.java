package me.zachary.coordinates.listeners;

import javafx.scene.paint.Color;
import me.zachary.coordinates.Coordinates;
import me.zachary.zachcore.utils.ChatUtils;
import me.zachary.zachcore.utils.xseries.messages.ActionBar;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JoinListener implements Listener {
    private Coordinates coordinates;
    public static Map<Player, Boolean> activate = new HashMap<Player, Boolean>();

    public JoinListener(Coordinates coordinates) {
        this.coordinates = coordinates;
        Bukkit.getPluginManager().registerEvents(this, coordinates);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if(coordinates.getConfig().getBoolean("ActionBar_Enable_OnJoin")){
            activate.put(player, true);
        }else{
            activate.put(player, false);
        }
        Bukkit.getScheduler().scheduleSyncRepeatingTask(coordinates, () -> {
            if(activate.get(player)){
                ActionBar.sendActionBar(player, ChatUtils.color(coordinates.getConfig().getString("ActionBar_Message")).replace("%X%", String.valueOf(((int) player.getLocation().getX()))).replace("%Y%", String.valueOf((int) player.getLocation().getY())).replace("%Z%", String.valueOf((int) player.getLocation().getZ())).replace("%Facing%", player.getFacing().name()));
            }
        }, 5L, 1L);
    }
}
