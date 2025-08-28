package org.polyfrost.fovtweaks.hooks

import dev.deftu.omnicore.client.OmniClientPlayer.getInstance
import net.minecraft.client.entity.EntityPlayerSP
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Items
import net.minecraft.potion.Potion
import org.polyfrost.fovtweaks.FOVTweaks

object FOVHook {

    private const val WATER_MULTIPLIER = 70f / 60f

    @JvmStatic
    fun getFov(): Float {
        var base = 1.0f
        val player: EntityPlayerSP = getInstance() ?: return base

        if (FOVTweaks.config.useFovModifiers) {
            base *= getFovModifier(player)
        }
        if (FOVTweaks.config.removeWaterFov) {
            base *= getWaterFovModifier(player)
        }
        return base
    }

    private fun getFovModifier(player: EntityPlayer): Float {
        var modifier = 1.0f

        val speed = player.getActivePotionEffect(Potion.moveSpeed)
        val slowness = player.getActivePotionEffect(Potion.moveSlowdown)

        if (player.capabilities.isFlying) modifier += 0.1f * FOVTweaks.config.flyingModifier

        if (player.isSprinting) modifier += 0.15f * FOVTweaks.config.sprintingModifier

        if (speed != null) modifier += 0.1f * (speed.amplifier + 1f) * FOVTweaks.config.speedModifier

        if (slowness != null) modifier += -0.075f * (slowness.amplifier + 1f) * FOVTweaks.config.slownessModifier

        if (player.capabilities.walkSpeed == 0f || modifier.isNaN() || modifier.isInfinite()) modifier = 1.0f

        if (player.isUsingItem && player.itemInUse.item === Items.bow) {
            val useDuration = player.itemInUseDuration
            val duration = useDuration / 20f
            val f1 = if (duration > 1f) 1f else duration * duration

            modifier *= 1f - f1 * 0.15f * FOVTweaks.config.bowModifier
        }

        return modifier
    }

    private fun getWaterFovModifier(player: EntityPlayer) = WATER_MULTIPLIER.takeIf { player.isInWater } ?: 1.0f
}