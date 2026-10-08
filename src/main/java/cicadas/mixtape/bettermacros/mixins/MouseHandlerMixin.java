package cicadas.mixtape.bettermacros.mixins;

import cicadas.mixtape.bettermacros.MacroManager;
import cicadas.mixtape.bettermacros.event.Action;
import cicadas.mixtape.bettermacros.event.Code;
import net.minecraft.client.MouseHandler;
import net.minecraft.client.input.MouseButtonInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MouseHandler.class)
public abstract class MouseHandlerMixin {
    @Inject(method = "onButton", at = @At("HEAD"))
    public void onButton(long window, MouseButtonInfo buttonInfo, int action, CallbackInfo ci) {
        MacroManager.INSTANCE.onCode(new Code(buttonInfo.button(), Action.Companion.get(action)));
    }
}