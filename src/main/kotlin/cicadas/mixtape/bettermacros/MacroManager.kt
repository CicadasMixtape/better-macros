package cicadas.mixtape.bettermacros

import cicadas.mixtape.bettermacros.event.Action
import cicadas.mixtape.bettermacros.event.Code
import cicadas.mixtape.bettermacros.list.MacroList
import cicadas.mixtape.bettermacros.screens.BetterMacrosScreen
import cicadas.mixtape.bettermacros.screens.Screens

object MacroManager {
    val macroList = MacroList()

    fun onTick() {
        if (BetterMacrosBinding.BINDING.isDown && mc.screen !is BetterMacrosScreen) {
            mc.setScreen(Screens.BETTER_MACROS)
        }
    }

    fun onCode(code: Code) {
        if (code.action != Action.PRESS || mc.screen != null) {
            return
        }

        macroList.forEach {
            if (code.code != it.code) return@forEach
            it.onKey()
        }
    }
}