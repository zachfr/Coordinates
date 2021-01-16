package me.zachary.coordinates;

import me.zachary.coordinates.commands.MainCommand;
import me.zachary.coordinates.listeners.JoinListener;
import me.zachary.zachcore.ZachCorePlugin;
import me.zachary.zachcore.utils.Metrics;

public final class Coordinates extends ZachCorePlugin {

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        int pluginId = 9479;
        Metrics metrics = new Metrics(this, pluginId);
        new MainCommand(this);
        new JoinListener(this);
        preEnable();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
