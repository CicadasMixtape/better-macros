package cicadas.mixtape.bettermacros

import kotlinx.serialization.json.Json
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.components.AbstractWidget
import net.minecraft.network.chat.Component
import java.io.File

fun Any.init() = this

fun drawTitle(graphics: GuiGraphics, component: Component, widget: AbstractWidget?) {
    val widget = widget ?: return
    graphics.drawString(mc.font, component, widget.x, widget.y - 12, -6250336)
}

val json = Json { prettyPrint = true }
val config = File(FabricLoader.getInstance().configDir.toString(), "better-macros.json")

inline val mc get() = Minecraft.getInstance()
inline val player get() = mc.player!!