package onelemonyboi.createjetpack.packets;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import onelemonyboi.createjetpack.CreateJetpack;

public class Packets {
    private static final String CHANNEL_NAME = "main";
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(CreateJetpack.MOD_ID, CHANNEL_NAME),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void main() {
        INSTANCE.registerMessage(1,
                PlayerJetpackPacket.class,
                PlayerJetpackPacket::encode,
                PlayerJetpackPacket::decode,
                PlayerJetpackPacket::handle
        );

        INSTANCE.registerMessage(2, PlayerMovementPacket.class,
                PlayerMovementPacket::encode,
                PlayerMovementPacket::decode,
                PlayerMovementPacket::handle
        );
    }
}
