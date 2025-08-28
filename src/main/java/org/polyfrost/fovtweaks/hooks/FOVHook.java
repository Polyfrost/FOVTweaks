package org.polyfrost.fovtweaks.hooks;

import dev.deftu.omnicore.client.OmniClientPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import org.polyfrost.fovtweaks.FOVTweaks;

public class FOVHook {
    public static float getFov() {
        float base = 1.0f;
        EntityPlayer player = OmniClientPlayer.getInstance();

        if (player == null) {
            return base;
        }

        if (FOVTweaks.config.useFovModifiers) {
            base *= getFovModifier(player);
        }
        if (FOVTweaks.config.removeWaterFov) {
            base *= getWaterFovModifier(player);
        }
        return base;
    }

    private static float getFovModifier(EntityPlayer player) {
        float modifier = 1.0f;

        PotionEffect speed = player.getActivePotionEffect(Potion.moveSpeed);
        PotionEffect slowness = player.getActivePotionEffect(Potion.moveSlowdown);

        if (player.capabilities.isFlying) modifier += 0.1f * FOVTweaks.config.flyingModifier;

        if (player.isSprinting()) modifier += 0.15f * FOVTweaks.config.sprintingModifier;

        if (speed != null) modifier += 0.1f * (speed.getAmplifier() + 1f) * FOVTweaks.config.speedModifier;

        if (slowness != null) modifier += -0.075f * (slowness.getAmplifier() + 1f) * FOVTweaks.config.slownessModifier;

        if (player.capabilities.getWalkSpeed() == 0f || Float.isNaN(modifier) || Float.isInfinite(modifier))
            modifier = 1.0F;

        if (player.isUsingItem() && player.getItemInUse().getItem() == Items.bow) {
            int useDuration = player.getItemInUseDuration();
            float duration = useDuration / 20f;
            float f1 = duration > 1f ? 1f : duration * duration;

            modifier *= 1f - f1 * 0.15F * FOVTweaks.config.bowModifier;
        }

        return modifier;
    }

    private static float getWaterFovModifier(EntityPlayer player) {
        return player.isInWater() ? 70f / 60f : 1.0f;
    }
}
