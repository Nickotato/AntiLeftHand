package me.nickotato.antilefthand.client.commands

import com.mojang.brigadier.CommandDispatcher
import me.nickotato.antilefthand.client.AntiLeftHandConfig
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource

class AntiLeftHandCommands {
    fun register(dispatcher: CommandDispatcher<FabricClientCommandSource>) {
        dispatcher.register(
            literal("antilefthand")
                .then(literal("toggle")
                    .executes { toggleLeftHand() }
                )
                .then(literal("toggle_apply_to_self")
                    .executes { toggleAffectingUser() }
                )
                .then(literal("toggle_anti_right_hand")
                    .executes { toggleAntiRightHand() }
                )
        )
    }

    fun toggleLeftHand(): Int {
        AntiLeftHandConfig.toggle()
        return 1
    }

    fun toggleAffectingUser(): Int {
        AntiLeftHandConfig.toggleApplyToSelf()
        return 1
    }

    fun toggleAntiRightHand(): Int{
        AntiLeftHandConfig.toggleAntiRightHand()
        return 1
    }
}