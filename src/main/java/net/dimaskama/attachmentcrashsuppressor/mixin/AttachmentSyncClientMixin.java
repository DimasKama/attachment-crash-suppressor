package net.dimaskama.attachmentcrashsuppressor.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;

@Pseudo
@Mixin(targets = "net.fabricmc.fabric.impl.attachment.client.AttachmentSyncClient", remap = false)
abstract class AttachmentSyncClientMixin {

    @WrapWithCondition(
            method = "lambda$onInitializeClient$1",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/fabricmc/fabric/api/networking/v1/PacketSender;disconnect(Lnet/minecraft/network/chat/Component;)V",
                    remap = false
            ),
            require = 0,
            remap = false
    )
    private static boolean wrapDisconnect(PacketSender instance, Component component) {
        return false;
    }

}
