package cicadas.mixtape.bettermacros

import com.mojang.blaze3d.platform.InputConstants
import kotlinx.serialization.Serializable

@Serializable
class Macro(
    var name: String,
    var code: Int,
    var command: String
) {
    fun onKey() {
        if (command.startsWith("/")) {
            player.connection.sendCommand(command.removePrefix("/"))
        } else {
            player.connection.sendChat(command)
        }
    }

    fun getKey() = if (code in 0..7) {
        InputConstants.Type.MOUSE.getOrCreate(code)
    } else {
        InputConstants.Type.KEYSYM.getOrCreate(code)
    }

    fun fromOther(other: Macro) {
        this.name = other.name
        this.code = other.code
        this.command = other.command
    }

    override fun toString() = "name=$name; code=$code; command=$command"
}