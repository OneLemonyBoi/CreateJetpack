package onelemonyboi.createjetpack;

import net.minecraft.client.Minecraft;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.LogicalSide;
import onelemonyboi.createjetpack.content.JetpackAction;
import onelemonyboi.createjetpack.content.JetpackItem;
import onelemonyboi.createjetpack.content.MovementAction;
import onelemonyboi.createjetpack.packets.Packets;
import onelemonyboi.createjetpack.packets.PlayerJetpackPacket;
import onelemonyboi.createjetpack.packets.PlayerMovementPacket;

public class ClientEvents {
    public static void onKeyInput(TickEvent.PlayerTickEvent event) {
        if (event.side == LogicalSide.SERVER) {
            return;
        }

        if (!(event.player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() instanceof JetpackItem)) {
            return;
        }

        Packets.INSTANCE.sendToServer(new PlayerJetpackPacket(JetpackAction.ACTIVATE, KeyBindings.activateJetpack.isKeyDown()));
        Packets.INSTANCE.sendToServer(new PlayerJetpackPacket(JetpackAction.HOVERING, KeyBindings.toggleHover.isActive()));
        Packets.INSTANCE.sendToServer(new PlayerMovementPacket(MovementAction.JUMP, Minecraft.getInstance().gameSettings.keyBindJump.isKeyDown()));
        Packets.INSTANCE.sendToServer(new PlayerMovementPacket(MovementAction.SNEAK, Minecraft.getInstance().gameSettings.keyBindSneak.isKeyDown()));
    }

    public static void clientStuff() {
        MinecraftForge.EVENT_BUS.addListener(ClientEvents::onKeyInput);
        MinecraftForge.EVENT_BUS.addListener(JetpackOverlayRenderer::jetpackOverlayRenderer);
        KeyBindings.register();
    }
}