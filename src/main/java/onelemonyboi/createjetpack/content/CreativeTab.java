package onelemonyboi.createjetpack.content;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import onelemonyboi.createjetpack.CreateJetpack;

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
