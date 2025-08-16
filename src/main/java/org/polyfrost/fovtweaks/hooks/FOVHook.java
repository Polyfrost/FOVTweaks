package org.polyfrost.fovtweaks.hooks;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import org.polyfrost.fovtweaks.FOVTweaks;

public class FOVHook {
    public static float getFovModifier() {
        float modifier = 1.0f;

        EntityPlayer entity = Minecraft.getMinecraft().thePlayer;
        PotionEffect speed = entity.getActivePotionEffect(Potion.moveSpeed);
        PotionEffect slowness = entity.getActivePotionEffect(Potion.moveSlowdown);

        if (entity.capabilities.isFlying) modifier += 0.1f * FOVTweaks.config.flyingModifier;

        if (entity.isSprinting()) modifier += 0.15f * FOVTweaks.config.sprintingModifier;

        if (speed != null) modifier += 0.1f * (speed.getAmplifier() + 1f) * FOVTweaks.config.speedModifier;

        if (slowness != null) modifier += -0.075f * (slowness.getAmplifier() + 1f) * FOVTweaks.config.slownessModifier;

        if (entity.capabilities.getWalkSpeed() == 0f || Float.isNaN(modifier) || Float.isInfinite(modifier)) modifier = 1.0F;

        if (entity.isUsingItem() && entity.getItemInUse().getItem() == Items.bow) {
            int useDuration = entity.getItemInUseDuration();
            float duration = useDuration / 20f;
            float f1 = duration > 1f ? 1f : duration * duration;

            modifier *= 1f - f1 * 0.15F * FOVTweaks.config.bowModifier;
        }

        return modifier;
    }
}
