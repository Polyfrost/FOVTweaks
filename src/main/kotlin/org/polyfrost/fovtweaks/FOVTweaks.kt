package org.polyfrost.fovtweaks

//#if FABRIC
//$$ import net.fabricmc.api.ModInitializer;
//#elseif FORGE
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
//#endif
import org.polyfrost.fovtweaks.config.FOVTweaksConfig

//#if FORGE-LIKE
@Mod(modid = FOVTweaks.ID, name = FOVTweaks.NAME, version = FOVTweaks.VERSION)
//#endif
class FOVTweaks
//#if FABRIC
//$$ : ModInitializer
//#endif
{

    //#if FABRIC
    //$$ override
    //#elseif FORGE
    @Mod.EventHandler
    //#endif
    fun onInitialize(
        //#if FORGE
        event: FMLInitializationEvent
        //#endif
    ) {
        // Forge requires the config to be initialized during boot, fabric doesn't
        // since im alr doing this, a lateinit var might be better..?
        //config
    }

    companion object {
        const val ID = "@MOD_ID@"
        const val NAME = "@MOD_NAME@"
        const val VERSION = "@MOD_VERSION@"

        @JvmStatic
        val config: FOVTweaksConfig by lazy { FOVTweaksConfig() }
    }
}