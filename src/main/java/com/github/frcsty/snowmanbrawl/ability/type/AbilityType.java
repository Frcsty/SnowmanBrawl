package com.github.frcsty.snowmanbrawl.ability.type;

import com.github.frcsty.snowmanbrawl.ability.type.implementations.BoulderTossAction;
import com.github.frcsty.snowmanbrawl.ability.type.implementations.DeepFreezeAction;
import com.github.frcsty.snowmanbrawl.ability.type.implementations.SnowballTossAction;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;

public enum AbilityType {

    SNOWBALL_TOSS(Action.LEFT_CLICK_AIR, false, 1, new SnowballTossAction()),
    BOULDER_TOSS(Action.RIGHT_CLICK_AIR, false, 10, new BoulderTossAction()),
    DEEP_FREEZE(Action.RIGHT_CLICK_AIR, true, 30, new DeepFreezeAction());

    private final Action abilityAction;
    private final boolean crouchRequired;
    private final long abilityCooldown;

    private final AbilityAction actionAbility;

    AbilityType(final Action action, final boolean crouch, final long cooldown, final AbilityAction actionAbility) {
        this.abilityAction = action;
        this.crouchRequired = crouch;
        this.abilityCooldown = cooldown;

        this.actionAbility = actionAbility;
    }

    public Action getAbilityAction() {
        return this.abilityAction;
    }

    public boolean isCrouchRequired() {
        return this.crouchRequired;
    }

    public long getAbilityCooldown() {
        return this.abilityCooldown;
    }

    public void executeAbilityAction(final Player player) {
        this.actionAbility.executeAction(player);
    }

    public static AbilityType getNullableType(final Action action, final boolean sneaking) {
        AbilityType type = null;

        for (final AbilityType abilityType : values()) {
            if (action == abilityType.getAbilityAction() && sneaking == abilityType.isCrouchRequired()) {
                type = abilityType;
                break;
            }
        }

        return type;
    }

}
