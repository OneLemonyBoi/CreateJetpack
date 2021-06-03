package onelemonyboi.createjetpack;

import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.List;

public class ChargerTile extends KineticTileEntity {
    ItemStack charging;


    public ChargerTile() {
        super(TEList.ChargerTile.get());
        this.charging = ItemStack.EMPTY;
    }

    @Override
    public void tick() {
        super.tick();
        if (charging.isEmpty()) {
            return;
        }
        charging.setDamage(MathHelper.clamp(charging.getDamage() - ((int) getSpeed()), 0, 64000));
        CreateJetpack.LOGGER.info(charging.getDamage());
    }

    @Override
    public boolean addToGoggleTooltip(List<ITextComponent> tooltip, boolean isPlayerSneaking) {
        tooltip.add(new StringTextComponent("Item Currently Charging: " + charging.getDisplayName().getString()));
        tooltip.add(new StringTextComponent("Charge: " + charging.getDamage() + "/" + charging.getMaxDamage()));
        return true;
    }
}
