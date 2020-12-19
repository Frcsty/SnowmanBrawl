package com.github.frcsty.snowmanbrawl.ability.type.implementations;

import com.github.frcsty.snowmanbrawl.ability.type.AbilityAction;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;

public final class BoulderTossAction implements AbilityAction {

    @Override
    public void executeAction(final Player player) {
        final Location location = player.getLocation();
        final Snowball snowball = (Snowball) location.getWorld().spawnEntity(
                location, EntityType.SNOWBALL
        );

        snowball.setShooter(player);
        snowball.setVelocity(location.getDirection().multiply(2));
    }

}
