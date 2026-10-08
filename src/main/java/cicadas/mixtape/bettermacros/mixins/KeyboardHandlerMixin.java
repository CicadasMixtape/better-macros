package cicadas.mixtape.bettermacros.mixins;

import cicadas.mixtape.bettermacros.event.Action;
import cicadas.mixtape.bettermacros.event.Code;
import cicadas.mixtape.bettermacros.MacroManager;
import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.input.KeyEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyboardHandler.class)
public abstract class KeyboardHandlerMixin {
    @Inject(method = "keyPress", at = @At("HEAD"))
    public void keyPress(long window, int action, KeyEvent event, CallbackInfo ci) {
        MacroManager.INSTANCE.onCode(new Code(event.key(), Action.Companion.get(action)));
    }
}