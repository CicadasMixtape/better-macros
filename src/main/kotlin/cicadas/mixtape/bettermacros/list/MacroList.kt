package cicadas.mixtape.bettermacros.list

import cicadas.mixtape.bettermacros.Macro
import cicadas.mixtape.bettermacros.config
import cicadas.mixtape.bettermacros.json

class MacroList {
    private val macros = arrayListOf<Macro>()
    private var dirty = false

    init {
        load()
    }

    fun getDirty(): ArrayList<Macro>? {
        if (dirty) {
            dirty = false
            return macros
        }

        return null
    }

    fun addMacro(macro: Macro) {
        macros.add(macro)
        save()
    }

    fun removeMacro(macro: Macro) {
        macros.remove(macro)
        save()
    }

    fun editMacro(macro: Macro, other: Macro) {
        macro.fromOther(other)
        save()
    }

    fun forEach(unit: (Macro) -> Unit) {
        macros.forEach(unit)
    }

    fun load() {
        if (!config.exists()) {
            return
        }

        macros.clear()
        macros.addAll(json.decodeFromString(config.readText()))
        dirty = true
    }

    fun save() {
        config.writeText(json.encodeToString(macros))
        dirty = true
    }
}