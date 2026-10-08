package cicadas.mixtape.bettermacros.list

import cicadas.mixtape.bettermacros.MacroManager
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.components.ObjectSelectionList

class MacroSelectionList(
    minecraft: Minecraft,
    width: Int,
    height: Int,
    y: Int,
    itemHeight: Int
) : ObjectSelectionList<MacroEntry>(minecraft, width, height, y, itemHeight) {
    val macroEntries = arrayListOf<MacroEntry>()

    fun update() {
        val dirty = MacroManager.macroList.getDirty()

        if (dirty != null) {
            macroEntries.clear()

            dirty.forEach {
                macroEntries.add(MacroEntry(it))
            }

            replaceEntries(macroEntries)
        }
    }
}