package me.nickotato.antilefthand.client.screens

import me.nickotato.antilefthand.client.AntiLeftHandConfig
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.text.Text

class AntiLeftHandScreen: Screen(Text.literal("Anti-Left-Hand")) {
    private lateinit var toggle: ButtonWidget
    private lateinit var applyToSelfButton: ButtonWidget
    private lateinit var antiRightHandButton: ButtonWidget

    override fun init() {
        val centerX = width / 2
        var y = height / 4

        toggle = addDrawableChild(
            ButtonWidget.builder(Text.literal("Toggled: ${AntiLeftHandConfig.enabled}")) {
                AntiLeftHandConfig.toggle()
                toggle.message = Text.literal("Toggled: ${AntiLeftHandConfig.enabled}")
            }.dimensions(centerX-100, y, 200, 20).build()
        )

        y+=24

        applyToSelfButton = addDrawableChild(
            ButtonWidget.builder(Text.literal("Should Apply to Self: ${AntiLeftHandConfig.applyToSelf}")) {
                AntiLeftHandConfig.toggleApplyToSelf()
                applyToSelfButton.message = Text.literal("Should Apply to Self: ${AntiLeftHandConfig.applyToSelf}")
            }.dimensions(centerX-100, y, 200, 20).build()
        )

        y+=24

        antiRightHandButton = addDrawableChild(
            ButtonWidget.builder(Text.literal("Anti-Right-Hand: ${AntiLeftHandConfig.antiRightHand}")) {
                AntiLeftHandConfig.toggleAntiRightHand()
                antiRightHandButton.message = Text.literal("Anti-Right-Hand: ${AntiLeftHandConfig.antiRightHand}")
            }.dimensions(centerX-100, y, 200, 20).build()
        )
    }
}