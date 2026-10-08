package cicadas.mixtape.bettermacros.screens

import cicadas.mixtape.bettermacros.Macro
import cicadas.mixtape.bettermacros.MacroComponents
import cicadas.mixtape.bettermacros.MacroManager
import cicadas.mixtape.bettermacros.button.KeyButton
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
import net.minecraft.network.chat.CommonComponents
import net.minecraft.network.chat.Component
import org.lwjgl.glfw.GLFW

class EditMacroScreen(val macro: Macro) : Screen(Component.literal("Edit macro")) {
    private var nameBox: EditBox? = null
    private var codeButton: KeyButton? = null
    private var commandBox: EditBox? = null

    private var saveButton: Button? = null
    private var cancelButton: Button? = null

    private val mainLayout = HeaderAndFooterLayout(this)
    private val gridLayout = GridLayout().spacing(5)

    override fun init() {
        val rows = gridLayout.createRowHelper(2)

        mainLayout.addTitleHeader(title, mc.font)

        nameBox = rows.addChild(EditBox(mc.font, 150, 20, MacroComponents.NAME), 2, gridLayout.newCellSettings().paddingBottom(18).alignHorizontallyCenter())
        nameBox?.value = macro.name

        codeButton = rows.addChild(KeyButton(150, 20, MacroComponents.CODE), 2, gridLayout.newCellSettings().paddingBottom(18).alignHorizontallyCenter())
        codeButton?.key(macro.code)

        commandBox = rows.addChild(EditBox(mc.font, 150, 20, MacroComponents.COMMAND), 2, gridLayout.newCellSettings().paddingBottom(38).alignHorizontallyCenter())
        commandBox?.value = macro.command

        saveButton = rows.addChild(Button.builder(MacroComponents.SAVE) { onClick(true) }.build())
        cancelButton = rows.addChild(Button.builder(CommonComponents.GUI_CANCEL) { onClick(false) }.build())

        mainLayout.addToContents(gridLayout)
        mainLayout.visitWidgets { addRenderableWidget(it) }

        repositionElements()

        super.init()
    }

    override fun render(guiGraphics: GuiGraphics, mouseX: Int, mouseY: Int, partialTick: Float) {
        drawTitle(guiGraphics, MacroComponents.NAME, nameBox)
        drawTitle(guiGraphics, MacroComponents.CODE, codeButton)
        drawTitle(guiGraphics, MacroComponents.COMMAND, commandBox)
        super.render(guiGraphics, mouseX, mouseY, partialTick)
    }

    override fun repositionElements() {
        mainLayout.arrangeElements()
        FrameLayout.centerInRectangle(mainLayout, 0, 0, width, height)
        FrameLayout.centerInRectangle(gridLayout, 0, mainLayout.headerHeight, width, mainLayout.contentHeight)
    }

    override fun keyPressed(event: KeyEvent): Boolean {
        if (event.key == GLFW.GLFW_KEY_ESCAPE) {
            mc.setScreen(Screens.BETTER_MACROS)
            return true
        }

        return super.keyPressed(event)
    }

    private fun onClick(edit: Boolean) {
        if (edit) {
            MacroManager.macroList.editMacro(macro, Macro(nameBox!!.value, codeButton!!.key(), commandBox!!.value))
        }

        mc.setScreen(Screens.BETTER_MACROS)
    }
}