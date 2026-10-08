package cicadas.mixtape.bettermacros.mixins;

import cicadas.mixtape.bettermacros.MacroManager;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci) {
        MacroManager.INSTANCE.onTick();
    }
}