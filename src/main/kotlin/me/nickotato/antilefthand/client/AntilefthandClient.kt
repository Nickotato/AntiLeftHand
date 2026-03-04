package me.nickotato.antilefthand.client

import me.nickotato.antilefthand.client.commands.AntiLeftHandCommands
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback

class AntilefthandClient : ClientModInitializer {

    lateinit var commands: AntiLeftHandCommands
        private set

    override fun onInitializeClient() {
        commands = AntiLeftHandCommands()

        ClientCommandRegistrationCallback.EVENT.register { dispatcher, _ ->
            commands.register(dispatcher)
        }
    }
}
