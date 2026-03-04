package me.nickotato.antilefthand.client

import com.google.gson.GsonBuilder
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import java.nio.file.Files
import java.nio.file.Path

object AntiLeftHandConfig {
    private val mc: MinecraftClient
        get() = MinecraftClient.getInstance()

    private val gson = GsonBuilder().setPrettyPrinting().create()

    private val configPath: Path =
        FabricLoader.getInstance().configDir.resolve("AntiLeftHand.json")

    var enabled = true
    var applyToSelf = true
    var antiRightHand = false

    fun load() {
        if (Files.exists(configPath)) {
            val json = Files.readString(configPath)
            try {
                val data = gson.fromJson(json, ConfigData::class.java)
                enabled = data.enabled
                applyToSelf = data.applyToSelf
                antiRightHand = data.antiRightHand

                save()
            } catch (_: Exception) {
                save()
            }
        } else {
            save()
        }
    }

    fun save() {
        val data = ConfigData(enabled, applyToSelf, antiRightHand)
        Files.writeString(configPath, gson.toJson(data))
    }

    fun toggle() {
        enabled = !enabled
        player()?.sendMessage(Text.literal("Enabled: $enabled").formatted(Formatting.GRAY), false)

        save()
    }

    fun toggleApplyToSelf() {
        applyToSelf = !applyToSelf
        player()?.sendMessage(Text.literal("Applying to Self: $applyToSelf").formatted(Formatting.GRAY), false)

        save()
    }

    fun toggleAntiRightHand() {
        antiRightHand = !antiRightHand
        player()?.sendMessage(Text.literal("Anti Right Hand: $antiRightHand").formatted(Formatting.GRAY), false)

        save()
    }

    fun player(): ClientPlayerEntity? {
        return mc.player
    }

    private data class ConfigData(
        val enabled: Boolean = true,
        val applyToSelf: Boolean = true,
        val antiRightHand: Boolean = false,
    )
}