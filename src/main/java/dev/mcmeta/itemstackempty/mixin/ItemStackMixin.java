package dev.mcmeta.itemstackempty.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class ItemStackMixin {
	@Inject(
			method = "getOrCreateTag()Lnet/minecraft/nbt/CompoundTag;",
			at = @At("HEAD"),
			cancellable = true
	)
	private void itemstackempty$getOrCreateTag(CallbackInfoReturnable<CompoundTag> cir) {
		if (self() == ItemStack.EMPTY) {
			cir.setReturnValue(new CompoundTag());
		}
	}

	@Inject(
			method = "getOrCreateTagElement(Ljava/lang/String;)Lnet/minecraft/nbt/CompoundTag;",
			at = @At("HEAD"),
			cancellable = true
	)
	private void itemstackempty$getOrCreateTagElement(String element, CallbackInfoReturnable<CompoundTag> cir) {
		if (self() == ItemStack.EMPTY) {
			cir.setReturnValue(new CompoundTag());
		}
	}

	@Inject(
			method = "setTag(Lnet/minecraft/nbt/CompoundTag;)V",
			at = @At("HEAD"),
			cancellable = true
	)
	private void itemstackempty$setTag(CompoundTag compoundTag, CallbackInfo ci) {
		if (self() == ItemStack.EMPTY) {
			ci.cancel();
		}
	}

	private ItemStack self() {
		return (ItemStack) (Object) this;
	}
}
