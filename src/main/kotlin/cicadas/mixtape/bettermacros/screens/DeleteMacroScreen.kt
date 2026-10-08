package cicadas.mixtape.bettermacros.screens

import cicadas.mixtape.bettermacros.Macro
import cicadas.mixtape.bettermacros.MacroComponents
import cicadas.mixtape.bettermacros.MacroManager
import cicadas.mixtape.bettermacros.mc
import net.minecraft.client.gui.components.Button
import net.minecraft.client.gui.components.MultiLineTextWidget
import net.minecraft.client.gui.layouts.FrameLayout
import net.minecraft.client.gui.layouts.GridLayout
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout
import net.minecraft.client.gui.screens.Screen
import net.minecraft.client.input.KeyEvent
import net.minecraft.network.chat.CommonComponents
import net.minecraft.network.chat.Component
import org.lwjgl.glfw.GLFW

class DeleteMacroScreen(val macro: Macro) : Screen(Component.literal("Delete macro")) {
    private val mainLayout = HeaderAndFooterLayout(this)
    private val gridLayout = GridLayout().spacing(5)

    private var areYouSureWidget: MultiLineTextWidget? = null
    private var yesButton: Button? = null
    private var noButton: Button? = null

    override fun init() {
        val rows = gridLayout.createRowHelper(2)

        mainLayout.addTitleHeader(title, mc.font)

        areYouSureWidget = rows.addChild(MultiLineTextWidget(MacroComponents.ARE_YOU_SURE, mc.font), 2, gridLayout.newCellSettings().alignHorizontallyCenter().paddingBottom(50))
        yesButton = rows.addChild(Button.builder(CommonComponents.GUI_YES) { onClick(true) }.build())
        noButton = rows.addChild(Button.builder(CommonComponents.GUI_NO) { onClick(false) }.build())

        mainLayout.addToContents(gridLayout)
        mainLayout.visitWidgets { addRenderableWidget(it) }

        repositionElements()

        super.init()
    }

    override fun repositionElements() {
        mainLayout.arrangeElements()
        FrameLayout.centerInRectangle(mainLayout, 0, 0, width, height)
        FrameLayout.centerInRectangle(gridLayout, 0, mainLayout.headerHeight - 75, width, mainLayout.contentHeight)
    }

    override fun keyPressed(event: KeyEvent): Boolean {
        if (event.key == GLFW.GLFW_KEY_ESCAPE) {
            mc.setScreen(Screens.BETTER_MACROS)
            return true
        }

        return super.keyPressed(event)
    }

    override fun isPauseScreen() = false

    private fun onClick(delete: Boolean) {
        if (delete) {
            MacroManager.macroList.removeMacro(macro)
        }

        mc.setScreen(Screens.BETTER_MACROS)
    }
}