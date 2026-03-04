package me.nickotato.antilefthand.mixin.client;

import me.nickotato.antilefthand.client.AntiLeftHandConfig;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.PlayerLikeEntity;
import net.minecraft.util.Arm;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerLikeEntity.class)
public abstract class PlayerMixin {

    @Inject(method = "getMainArm", at=@At("HEAD"), cancellable = true)
    private void antilefthand$forceRightArm(CallbackInfoReturnable<Arm> cir) {
        if (!AntiLeftHandConfig.INSTANCE.getEnabled()) return;
        if (!AntiLeftHandConfig.INSTANCE.getApplyToSelf()) {
            PlayerLikeEntity self = (PlayerLikeEntity)(Object)this;

            if (self instanceof ClientPlayerEntity) {
                return;
            }
        }

        cir.setReturnValue(antilefthand$getArmValue());
    }

    @Unique
    private Arm antilefthand$getArmValue() {
        if (AntiLeftHandConfig.INSTANCE.getAntiRightHand()) {
            return Arm.LEFT;
        }

        return Arm.RIGHT;
    }
}
