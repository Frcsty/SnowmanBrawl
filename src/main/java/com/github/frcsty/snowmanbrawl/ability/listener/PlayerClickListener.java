package com.github.frcsty.snowmanbrawl.ability.listener;

import com.github.frcsty.snowmanbrawl.BrawlPlugin;
import com.github.frcsty.snowmanbrawl.ability.cache.AbilityCache;
import com.github.frcsty.snowmanbrawl.ability.type.AbilityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public final class PlayerClickListener implements Listener {

    private final AbilityCache cache = new AbilityCache();

    public PlayerClickListener(final BrawlPlugin plugin) {
        runAbilityTimer(plugin);
    }

    @EventHandler
    public void onPlayerClick(final PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        final UUID identifier = player.getUniqueId();

        final AbilityType type = AbilityType.getNullableType(event.getAction(), player.isSneaking());
        if (type == null)
            return;

        if (this.cache.isAbilityOnCooldown(identifier, type))
            return;

        type.executeAbilityAction(player);
        this.cache.setAbilityTypeCooldown(identifier, type);
    }

    private void runAbilityTimer(final BrawlPlugin plugin) {
        new BukkitRunnable() {
            @Override
            public void run() {
                cache.updateHolders();
            }
        }.runTaskTimerAsynchronously(plugin, 20, 20);
    }

}
