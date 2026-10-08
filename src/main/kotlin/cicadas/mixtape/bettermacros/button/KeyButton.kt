package cicadas.mixtape.bettermacros.button

import com.mojang.blaze3d.platform.InputConstants
import net.minecraft.ChatFormatting
import net.minecraft.client.gui.components.Button
import net.minecraft.client.input.InputWithModifiers
import net.minecraft.client.input.KeyEvent
import net.minecraft.client.input.MouseButtonEvent
import net.minecraft.network.chat.Component
import org.lwjgl.glfw.GLFW

class KeyButton(
    width: Int,
    height: Int,
    component: Component
) : Button.Plain(0, 0, width,height, component, {}, DEFAULT_NARRATION) {
    private var key = InputConstants.UNKNOWN
    private var listen = false

    private val text get() = if (listen) {
        Component.empty()
            .append(Component.literal("> ").withStyle(ChatFormatting.YELLOW))
            .append(key.displayName.copy().withStyle(ChatFormatting.UNDERLINE))
            .append(Component.literal(" <").withStyle(ChatFormatting.YELLOW))
    } else {
        key.displayName
    }

    override fun onPress(inputWithModifiers: InputWithModifiers) {
        listen = true
        message = text
    }

    override fun mouseClicked(mouseButtonEvent: MouseButtonEvent, bl: Boolean): Boolean {
        if (listen) {
            key = InputConstants.Type.MOUSE.getOrCreate(mouseButtonEvent.button())
            listen = false
            message = text
            return true
        }

        return super.mouseClicked(mouseButtonEvent, bl)
    }

    override fun keyPressed(keyEvent: KeyEvent): Boolean {
        if (listen) {
            key = if (keyEvent.key == GLFW.GLFW_KEY_ESCAPE) {
                InputConstants.UNKNOWN
            } else {
                InputConstants.getKey(keyEvent)
            }

            listen = false
            message = text
            return true
        }

        return false
    }

    fun reset() {
        key = InputConstants.UNKNOWN
        message = Component.literal("Key")
    }

    fun key() = key.value

    fun key(code: Int) {
        key = if (code in 0..7) {
            InputConstants.Type.MOUSE.getOrCreate(code)
        } else {
            InputConstants.Type.KEYSYM.getOrCreate(code)
        }

        message = text
    }
}