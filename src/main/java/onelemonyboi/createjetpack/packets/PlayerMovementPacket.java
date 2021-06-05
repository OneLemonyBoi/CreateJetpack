package onelemonyboi.createjetpack.packets;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import onelemonyboi.createjetpack.content.JetpackAction;
import onelemonyboi.createjetpack.content.JetpackItem;
import onelemonyboi.createjetpack.content.MovementAction;

import java.util.function.Supplier;

public class PlayerMovementPacket {
    MovementAction action;
    boolean value;

    public PlayerMovementPacket(MovementAction action, boolean change) {
        this.action = action;
        this.value = change;
    }

    public static void encode(PlayerMovementPacket packet, PacketBuffer buf) {
        buf.writeEnumValue(packet.action);
        buf.writeBoolean(packet.value);
    }

    public static PlayerMovementPacket decode(PacketBuffer buf) {
        return new PlayerMovementPacket(buf.readEnumValue(MovementAction.class), buf.readBoolean());
    }

    public static void handle(PlayerMovementPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(()-> {
            if (ctx.get().getSender() == null) {
                return;
            }
            ItemStack itemStack = ctx.get().getSender().getItemStackFromSlot(EquipmentSlotType.CHEST);
            if (itemStack.getItem() instanceof JetpackItem) {
                JetpackItem item = (JetpackItem) itemStack.getItem();
                switch (msg.action) {
                    case JUMP:
                        item.playerJumping = msg.value;
                        break;
                    case SNEAK:
                        item.playerSneaking = msg.value;
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
