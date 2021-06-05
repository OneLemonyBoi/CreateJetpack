package onelemonyboi.createjetpack;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import onelemonyboi.createjetpack.content.BlockList;
import onelemonyboi.createjetpack.content.ChargerTile;

public class TEList {
    public static final RegistryObject<TileEntityType<onelemonyboi.createjetpack.content.ChargerTile>> ChargerTile = ModRegistry.TE.register("charger", () -> TileEntityType.Builder.create(ChargerTile::new, BlockList.Charger.get()).build(null));

    public static void register() {}
}
