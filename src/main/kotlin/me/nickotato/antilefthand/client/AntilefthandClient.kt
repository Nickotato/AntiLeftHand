package me.nickotato.antilefthand.client

import me.nickotato.antilefthand.client.commands.AntiLeftHandCommands
import me.nickotato.antilefthand.client.handlers.KeyBindHandler
import me.nickotato.antilefthand.client.screens.AntiLeftHandScreen
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents

class AntilefthandClient : ClientModInitializer {

    lateinit var commands: AntiLeftHandCommands
        private set

    lateinit var keybinds: KeyBindHandler
        private set

    override fun onInitializeClient() {
        AntiLeftHandConfig.load()

        commands = AntiLeftHandCommands()
        keybinds = KeyBindHandler()


        ClientCommandRegistrationCallback.EVENT.register { dispatcher, _ ->
            commands.register(dispatcher)
        }

        keybinds.register()

        ClientTickEvents.END_CLIENT_TICK.register {client ->
            while (keybinds.openGuiKey.wasPressed()) {
                client.setScreen(AntiLeftHandScreen())
            }
        }
    }
}
