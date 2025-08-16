package org.polyfrost.fovtweaks;

//#if FABRIC
//$$ import net.fabricmc.api.ModInitializer;
//#elseif FORGE
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
//#endif

import org.polyfrost.fovtweaks.config.FOVTweaksConfig;

//#if FORGE-LIKE
@Mod(modid = FOVTweaks.ID, name = FOVTweaks.NAME, version = FOVTweaks.VERSION)
//#endif
public class FOVTweaks
        //#if FABRIC
        //$$ implements ModInitializer
        //#endif
{
    public static final String ID = "@MOD_ID@";
    public static final String NAME = "@MOD_NAME@";
    public static final String VERSION = "@MOD_VERSION@";

    public static FOVTweaksConfig config;

    //#if FABRIC
    //$$ @Override
    //#elseif FORGE
    @Mod.EventHandler
    //#endif
    public void onInitialize(
            //#if FORGE
            FMLInitializationEvent event
            //#endif
    ) {
        config = new FOVTweaksConfig();
    }
}