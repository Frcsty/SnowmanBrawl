package com.github.frcsty.snowmanbrawl;

import com.github.frcsty.snowmanbrawl.ability.listener.PlayerClickListener;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public final class BrawlPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        registerListeners(
                new PlayerClickListener(this)
        );
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerListeners(final Listener... listeners) {
        Arrays.stream(listeners).forEach(listener ->
                getServer().getPluginManager().registerEvents(listener, this)
        );
    }

}
