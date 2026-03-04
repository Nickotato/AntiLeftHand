package me.nickotato.antilefthand.client

import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.text.Text
import net.minecraft.util.Formatting

object AntiLeftHandConfig {
    private val mc: MinecraftClient
    get() = MinecraftClient.getInstance()

    var enabled = true
    var applyToSelf = true

    fun toggle() {
        enabled = !enabled
        player()?.sendMessage(Text.literal("Enabled: $enabled").formatted(Formatting.AQUA), false)
    }

    fun toggleApplyToSelf() {
        applyToSelf = !applyToSelf
        player()?.sendMessage(Text.literal("Applying to Self: $applyToSelf").formatted(Formatting.AQUA), false)
    }

    fun player(): ClientPlayerEntity? {
        return mc.player
    }
}