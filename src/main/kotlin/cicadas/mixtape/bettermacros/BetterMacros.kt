package cicadas.mixtape.bettermacros

import cicadas.mixtape.bettermacros.screens.*
import com.mojang.blaze3d.platform.InputConstants
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.KeyMapping
import net.minecraft.resources.Identifier
import org.lwjgl.glfw.GLFW

object BetterMacros : ModInitializer {
    @JvmField
    val CATEGORY = KeyMapping.Category.register(Identifier.fromNamespaceAndPath("bettermacros", "keys"))

    @JvmField
    val BINDING = KeyBindingHelper.registerKeyBinding(KeyMapping(
        "key.bettermacros.openmenu",
        InputConstants.Type.KEYSYM,
        GLFW.GLFW_KEY_DELETE,
        CATEGORY
    ))

    override fun onInitialize() {
        MacroManager.init()
        Screens.init()
    }
}