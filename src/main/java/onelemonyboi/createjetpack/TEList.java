package onelemonyboi.createjetpack;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;

public class TEList {
    public static final RegistryObject<TileEntityType<ChargerTile>> ChargerTile = ModRegistry.TE.register("charger", () -> TileEntityType.Builder.create(ChargerTile::new, BlockList.Charger.get()).build(null));

    public static void register() {}
}
