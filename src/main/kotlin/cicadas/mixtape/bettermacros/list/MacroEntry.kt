package cicadas.mixtape.bettermacros.list

import cicadas.mixtape.bettermacros.Macro
import cicadas.mixtape.bettermacros.MacroManager
import cicadas.mixtape.bettermacros.mc
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.components.ObjectSelectionList
import net.minecraft.client.input.MouseButtonEvent
import net.minecraft.network.chat.Component

class MacroEntry(val macro: Macro) : ObjectSelectionList.Entry<MacroEntry>() {
    override fun getNarration() = Component.empty()

    override fun renderContent(
        guiGraphics: GuiGraphics,
        mouseX: Int,
        mouseY: Int,
        isHovering: Boolean,
        partialTick: Float
    ) {
        val name = Component.literal(macro.name)
        guiGraphics.drawString(mc.font, name, x + 10, y + height / 2 - mc.font.lineHeight / 2, -1)

        val key = Component.literal("Key: ").append(macro.getKey().displayName)
        val keyWidth = mc.font.width(key) + 10
        guiGraphics.drawString(mc.font, key, x + width - keyWidth, y + height / 2 - mc.font.lineHeight - 2, -1)

        val command = Component.literal("Command: ").append(macro.command)
        val commandWidth = mc.font.width(command) + 10
        guiGraphics.drawString(mc.font, command, x + width - commandWidth, y + height / 2 + 2, -1)
    }
}