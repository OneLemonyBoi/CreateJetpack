package onelemonyboi.createjetpack;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class CreativeTab extends ItemGroup {
    private static final CreativeTab INSTANCE = new CreativeTab();

    public CreativeTab() { super(CreateJetpack.MOD_ID); }

    public static CreativeTab getInstance()
    {
        return INSTANCE;
    }

    public ItemStack createIcon()
    {
        return new ItemStack(ItemList.Jetpack.get());
    }
}
