package cicadas.mixtape.bettermacros.screens

import cicadas.mixtape.bettermacros.MacroComponents
import cicadas.mixtape.bettermacros.list.MacroSelectionList
import cicadas.mixtape.bettermacros.mc
import net.minecraft.client.gui.components.Button
import net.minecraft.client.gui.layouts.FrameLayout
import net.minecraft.client.gui.layouts.GridLayout
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout
import net.minecraft.client.gui.screens.Screen
import net.minecraft.network.chat.Component

class BetterMacrosScreen : Screen(Component.literal("Better macros")) {
    private val mainLayout = HeaderAndFooterLayout(this)

    private var selectionList: MacroSelectionList? = null
    private var addButton: Button? = null
    private var editButton: Button? = null
    private var deleteButton: Button? = null

    override fun init() {
        val layout = GridLayout().spacing(5)
        val rows = layout.createRowHelper(3)

        mainLayout.addTitleHeader(title, mc.font)

        selectionList = mainLayout.addToContents(MacroSelectionList(minecraft, width, mainLayout.contentHeight, mainLayout.headerHeight, 36))

        addButton = rows.addChild(Button.builder(MacroComponents.ADD) { mc.setScreen(Screens.ADD_MACRO) }.size(100, 20).build())
        editButton = rows.addChild(Button.builder(MacroComponents.EDIT) { mc.setScreen(EditMacroScreen(selectionList!!.selected!!.macro)) }.size(100, 20).build())
        deleteButton = rows.addChild(Button.builder(MacroComponents.DELETE) { mc.setScreen(DeleteMacroScreen(selectionList!!.selected!!.macro)) }.size(100, 20).build())

        mainLayout.addToFooter(layout)
        mainLayout.visitWidgets { addRenderableWidget(it) }

        repositionElements()

        super.init()
    }

    override fun tick() {
        selectionList?.update()
        editButton!!.active = selectionList!!.selected != null
        deleteButton!!.active = selectionList!!.selected != null
        super.tick()
    }

    override fun repositionElements() {
        mainLayout.arrangeElements()
        selectionList?.updateSize(width, mainLayout)
        FrameLayout.centerInRectangle(mainLayout, 0, 0, width, height)
    }

    override fun isPauseScreen() = false
}