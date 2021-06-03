package onelemonyboi.createjetpack;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class ItemList {
    public static final RegistryObject<Item> Jetpack = ModRegistry.ITEMS.register("jetpack", () -> new JetpackItem(new Item.Properties().group(CreativeTab.getInstance())));

    public static void register() {}
}
