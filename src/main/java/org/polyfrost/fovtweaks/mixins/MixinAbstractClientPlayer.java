package org.polyfrost.fovtweaks.mixins;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.entity.AbstractClientPlayer;
import org.polyfrost.fovtweaks.FOVTweaks;
import org.polyfrost.fovtweaks.hooks.FOVHook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractClientPlayer.class)
public class MixinAbstractClientPlayer {
    @ModifyReturnValue(method = "getFovModifier", at = @At("RETURN"))
    private float idk(float original) {
        return FOVTweaks.getConfig().getUseFovModifiers() || FOVTweaks.getConfig().getRemoveWaterFov() ?
                FOVHook.getFov() : original;
    }
}
