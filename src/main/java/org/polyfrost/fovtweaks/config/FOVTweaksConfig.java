package org.polyfrost.fovtweaks.config;

import org.polyfrost.fovtweaks.FOVTweaks;
import org.polyfrost.oneconfig.api.config.v1.Config;
import org.polyfrost.oneconfig.api.config.v1.annotations.Slider;
import org.polyfrost.oneconfig.api.config.v1.annotations.Switch;

import java.util.Arrays;

public class FOVTweaksConfig extends Config {
    public FOVTweaksConfig() {
        super(FOVTweaks.ID + ".json", FOVTweaks.NAME, Category.QOL);

        Arrays.asList(
                "sprintingModifier", "flyingModifier",
                "bowModifier", "speedModifier",
                "slownessModifier"
        ).forEach(property -> addDependency(property, "useFovModifiers"));
    }

    @Switch(
            title = "FOV Modifier",
            description = "Allow for modifying FOV change states.",
            category = "Miscellaneous", subcategory = "Field of View"
    )
    public static boolean useFovModifiers;

    @Slider(
            title = "Sprinting FOV",
            description = "Modify your FOV when sprinting.",
            category = "Miscellaneous", subcategory = "Field of View",
            min = -5F, max = 5F
    )
    public static float sprintingModifier = 1;

    @Slider(
            title = "Flying FOV",
            description = "Modify your FOV when flying.",
            category = "Miscellaneous", subcategory = "Field of View",
            min = -5F, max = 5F
    )
    public static float flyingModifier = 1;

    @Slider(
            title = "Bow FOV",
            description = "Modify your FOV when pulling back a bow.",
            category = "Miscellaneous", subcategory = "Field of View",
            min = -5, max = 5
    )
    public static float bowModifier = 1;

    @Slider(
            title = "Speed FOV",
            description = "Modify your FOV when having the speed effect.",
            category = "Miscellaneous", subcategory = "Field of View",
            min = -5, max = 5
    )
    public static float speedModifier = 1;

    @Slider(
            title = "Slowness FOV",
            description = "Modify your FOV when having the slowness effect.",
            category = "Miscellaneous", subcategory = "Field of View",
            min = -5, max = 5
    )
    public static float slownessModifier = 1;
}
