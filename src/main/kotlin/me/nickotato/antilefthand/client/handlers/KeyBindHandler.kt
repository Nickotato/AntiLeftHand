package me.nickotato.antilefthand.client.handlers

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import org.lwjgl.glfw.GLFW

class KeyBindHandler {
    lateinit var openGuiKey: KeyBinding
        private set

    fun register() {
        openGuiKey = KeyBindingHelper.registerKeyBinding(KeyBinding(
            "Open Anti-Left-Hand GUI",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_K,
            KeyBinding.Category.MISC
        ))
    }
}