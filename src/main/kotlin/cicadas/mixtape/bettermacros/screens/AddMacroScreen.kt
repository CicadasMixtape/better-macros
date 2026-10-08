package cicadas.mixtape.bettermacros.screens

import cicadas.mixtape.bettermacros.button.KeyButton
import cicadas.mixtape.bettermacros.Macro
import cicadas.mixtape.bettermacros.MacroComponents
import cicadas.mixtape.bettermacros.MacroManager
import cicadas.mixtape.bettermacros.drawTitle
import cicadas.mixtape.bettermacros.mc
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.components.Button
import net.minecraft.client.gui.components.EditBox
import net.minecraft.client.gui.layouts.FrameLayout
import net.minecraft.client.gui.layouts.GridLayout
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout
import net.minecraft.client.gui.screens.Screen
import net.minecraft.client.input.KeyEvent
import net.minecraft.network.chat.Component
import org.lwjgl.glfw.GLFW
import kotlin.text.isNotBlank

class AddMacroScreen : Screen(Component.literal("Add macro")) {
    private val mainLayout = HeaderAndFooterLayout(this)

    private var nameBox: EditBox? = null
    private var codeButton: KeyButton? = null
    private var commandBox: EditBox? = null
    private var addButton: Button? = null

    override fun init() {
        val layout = GridLayout().spacing(5)
        val rows = layout.createRowHelper(1)

        mainLayout.addTitleHeader(title, mc.font)

        nameBox = rows.addChild(EditBox(mc.font, 150, 20, MacroComponents.NAME), layout.newCellSettings().paddingBottom(18))
        codeButton = rows.addChild(KeyButton(150, 20, MacroComponents.CODE), layout.newCellSettings().paddingBottom(18))
        commandBox = rows.addChild(EditBox(mc.font, 150, 20, MacroComponents.COMMAND))
        addButton = rows.addChild(Button.builder(title) { onClick() }.build())

        mainLayout.addToContents(layout)
        mainLayout.visitWidgets { addRenderableWidget(it) }

        repositionElements()

        super.init()
    }

    override fun render(guiGraphics: GuiGraphics, mouseX: Int, mouseY: Int, partialTick: Float) {
        updateVisible()
        drawTitle(guiGraphics, MacroComponents.NAME, nameBox)
        drawTitle(guiGraphics, MacroComponents.CODE, codeButton)
        drawTitle(guiGraphics, MacroComponents.COMMAND, commandBox)
        super.render(guiGraphics, mouseX, mouseY, partialTick)
    }

    override fun keyPressed(event: KeyEvent): Boolean {
        if (event.key == GLFW.GLFW_KEY_ESCAPE) {
            mc.setScreen(Screens.BETTER_MACROS)
            return true
        }

        return codeButton!!.keyPressed(event) || super.keyPressed(event)
    }

    override fun repositionElements() {
        mainLayout.arrangeElements()
        FrameLayout.centerInRectangle(mainLayout, 0, 0, width, height)
    }

    override fun isPauseScreen() = false

    private fun onClick() {
        MacroManager.macroList.addMacro(Macro(nameBox!!.value, codeButton!!.key(), commandBox!!.value))
        mc.setScreen(Screens.BETTER_MACROS)
        reset()
    }

    private fun reset() {
        nameBox?.value = ""
        codeButton?.reset()
        commandBox?.value = ""
    }

    private fun updateVisible() {
        addButton?.active = nameBox!!.value.isNotBlank() && codeButton!!.key() != GLFW.GLFW_KEY_UNKNOWN && commandBox!!.value.isNotBlank()
    }
}