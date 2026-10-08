package cicadas.mixtape.bettermacros

import cicadas.mixtape.bettermacros.screens.*
import net.fabricmc.api.ModInitializer

object BetterMacros : ModInitializer {
    override fun onInitialize() {
        BetterMacrosBinding.init()
        MacroManager.init()
        Screens.init()
    }
}