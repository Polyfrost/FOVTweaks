package org.polyfrost.fovtweaks.config

import org.polyfrost.fovtweaks.FOVTweaks
import org.polyfrost.oneconfig.api.config.v1.Config
import org.polyfrost.oneconfig.api.config.v1.annotations.Slider
import org.polyfrost.oneconfig.api.config.v1.annotations.Switch

class FOVTweaksConfig : Config("${FOVTweaks.ID}.json", FOVTweaks.NAME, Category.QOL) {
    init {
        listOf(
            "sprintingModifier", "flyingModifier",
            "bowModifier", "speedModifier",
            "slownessModifier"
        ).forEach { addDependency(it, "useFovModifiers") }
    }

    @Switch(
        title = "FOV Modifier",
        description = "Allow for modifying FOV change states.",
        category = "Miscellaneous", subcategory = "Field of View"
    )
    var useFovModifiers: Boolean = false

    @Slider(
        title = "Sprinting FOV",
        description = "Modify your FOV when sprinting.",
        category = "Miscellaneous", subcategory = "Field of View",
        min = -5f, max = 5f, step = 0.1f
    )
    var sprintingModifier: Float = 1f

    @Slider(
        title = "Flying FOV",
        description = "Modify your FOV when flying.",
        category = "Miscellaneous", subcategory = "Field of View",
        min = -5f, max = 5f, step = 0.1f
    )
    var flyingModifier: Float = 1f

    @Slider(
        title = "Bow FOV",
        description = "Modify your FOV when pulling back a bow.",
        category = "Miscellaneous", subcategory = "Field of View",
        min = -5f, max = 5f, step = 0.1f
    )
    var bowModifier: Float = 1f

    @Slider(
        title = "Speed FOV",
        description = "Modify your FOV when having the speed effect.",
        category = "Miscellaneous", subcategory = "Field of View",
        min = -5f, max = 5f, step = 0.1f
    )
    var speedModifier: Float = 1f

    @Slider(
        title = "Slowness FOV",
        description = "Modify your FOV when having the slowness effect.",
        category = "Miscellaneous", subcategory = "Field of View",
        min = -5f, max = 5f, step = 0.1f
    )
    var slownessModifier: Float = 1f

    @Switch(
        title = "Remove Water FOV",
        description = "Removes the FOV change when underwater.",
        category = "Miscellaneous", subcategory = "Field of View"
    )
    var removeWaterFov: Boolean = false
}