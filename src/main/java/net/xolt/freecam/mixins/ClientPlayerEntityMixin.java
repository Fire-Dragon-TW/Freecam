package net.xolt.freecam.mixins;

import net.minecraft.client.network.ClientPlayerEntity;
import net.xolt.freecam.Freecam;
import net.xolt.freecam.config.ModConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.xolt.freecam.Freecam.MC;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {

    @Inject(method = "isCamera", at = @At("HEAD"), cancellable = true)
    private void onIsCamera(CallbackInfoReturnable<Boolean> cir) {
        if (Freecam.isEnabled() && this.equals(MC.player)) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "damage", at = @At("HEAD"))
    private void onDamage(CallbackInfoReturnable<Boolean> cir) {
        if (Freecam.isEnabled() && ModConfig.INSTANCE.disableOnDamage && this.equals(MC.player)) {
            Freecam.toggle();
        }
    }
}
