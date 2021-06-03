package onelemonyboi.createjetpack;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PlayerHoveringPacket {
    boolean isHovering;

    public PlayerHoveringPacket(boolean isHovering) {
        this.isHovering = isHovering;
    }

    public static void encode(PlayerHoveringPacket packet, PacketBuffer buf) {
        buf.writeBoolean(packet.isHovering);
    }

    public static PlayerHoveringPacket decode(PacketBuffer buf) {
        return new PlayerHoveringPacket(buf.readBoolean());
    }

    public static void handle(PlayerHoveringPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(()-> {
            if (ctx.get().getSender() == null) {
                return;
            }
            ItemStack itemStack = ctx.get().getSender().getItemStackFromSlot(EquipmentSlotType.CHEST);
            if (itemStack.getItem() instanceof JetpackItem) {
                JetpackItem item = (JetpackItem) itemStack.getItem();
                item.isHovering = msg.isHovering;
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
