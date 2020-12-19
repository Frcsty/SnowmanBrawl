package com.github.frcsty.snowmanbrawl.ability.cache;

import com.github.frcsty.snowmanbrawl.ability.type.AbilityType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class AbilityCache {

    private final Map<UUID, AbilityHolder> abilities = new HashMap<>();

    private AbilityHolder getOrInstantiate(final UUID identifier) {
        return this.abilities.getOrDefault(identifier, new AbilityHolder());
    }

    public void updateHolders() {
        this.abilities.keySet().forEach(this::updateHolderForIdentifier);
    }

    private void updateHolderForIdentifier(final UUID identifier) {
        final AbilityHolder holder = getOrInstantiate(identifier);

        holder.cooldown.forEach((type, cooldown) ->
                holder.decrementTypeCooldown(type)
        );

        this.abilities.put(identifier, holder);
    }

    public boolean isAbilityOnCooldown(final UUID identifier, final AbilityType type) {
        final AbilityHolder holder = getOrInstantiate(identifier);

        return holder.isOnCooldown(type);
    }

    public void setAbilityTypeCooldown(final UUID identifier, final AbilityType type) {
        final AbilityHolder holder = getOrInstantiate(identifier);

        holder.setTypeCooldown(type);
        this.abilities.put(identifier, holder);
    }

    private class AbilityHolder {

        private final Map<AbilityType, Long> cooldown = new HashMap<>();

        private void decrementTypeCooldown(final AbilityType type) {
            final long cooldown = this.cooldown.get(type);

            if (cooldown <= 0) {
                this.cooldown.remove(type);
                return;
            }

            this.cooldown.put(type, cooldown - 1);
        }

        private void setTypeCooldown(final AbilityType type) {
            this.cooldown.put(type, type.getAbilityCooldown());
        }

        private boolean isOnCooldown(final AbilityType type) {
            return this.cooldown.get(type) != null;
        }

    }

}
