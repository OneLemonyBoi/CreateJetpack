package onelemonyboi.createjetpack.packets;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import onelemonyboi.createjetpack.content.JetpackAction;
import onelemonyboi.createjetpack.content.JetpackItem;

import java.util.function.Supplier;

public class PlayerJetpackPacket {
    JetpackAction action;
    boolean value;

    public PlayerJetpackPacket(JetpackAction action, boolean change) {
        this.action = action;
        this.value = change;
    }

    public static void encode(PlayerJetpackPacket packet, PacketBuffer buf) {
        buf.writeEnumValue(packet.action);
        buf.writeBoolean(packet.value);
    }

    public static PlayerJetpackPacket decode(PacketBuffer buf) {
        return new PlayerJetpackPacket(buf.readEnumValue(JetpackAction.class), buf.readBoolean());
    }

    public static void handle(PlayerJetpackPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(()-> {
            if (ctx.get().getSender() == null) {
                return;
            }
            ItemStack itemStack = ctx.get().getSender().getItemStackFromSlot(EquipmentSlotType.CHEST);
            if (itemStack.getItem() instanceof JetpackItem) {
                JetpackItem item = (JetpackItem) itemStack.getItem();
                switch (msg.action) {
                    case HOVERING:
                        item.isHovering = msg.value;
                        break;
                    case ACTIVATE:
                        item.isActive = msg.value;
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
