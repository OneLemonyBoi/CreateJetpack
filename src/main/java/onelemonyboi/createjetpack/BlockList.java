package onelemonyboi.createjetpack;

import com.simibubi.create.AllBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class BlockList {
    public static final RegistryObject<Block> Charger = register("charger", () -> new ChargerBlock(AbstractBlock.Properties.from(Blocks.IRON_BARS)));
    public static void register() {}

    private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> block) {
        return ModRegistry.BLOCKS.register(name, block);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block) {
        RegistryObject<T> ret = registerNoItem(name, block);
        ModRegistry.ITEMS.register(name, () -> new BlockItem(ret.get(), new Item.Properties().group(CreativeTab.getInstance())));
        return ret;
    }

    private static <T extends Block> RegistryObject<T> registerNoTab(String name, Supplier<T> block) {
        RegistryObject<T> ret = registerNoItem(name, block);
        ModRegistry.ITEMS.register(name, () -> new BlockItem(ret.get(), new Item.Properties()));
        return ret;
    }
}
