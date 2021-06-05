package onelemonyboi.createjetpack.content;

import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import com.simibubi.create.foundation.utility.Lang;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import onelemonyboi.createjetpack.JetpackLang;
import onelemonyboi.createjetpack.TEList;

import javax.annotation.Nullable;
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
        if (charging.isEmpty() || world == null) {
            return;
        }
        charging.setDamage(MathHelper.clamp(charging.getDamage() - ((int) -getSpeed()), 0, 64000));
        this.markDirty();
        this.sendData();
    }

    @Override
    public boolean addToGoggleTooltip(List<ITextComponent> tooltip, boolean isPlayerSneaking) {
        super.addToGoggleTooltip(tooltip, isPlayerSneaking);
        if (charging.isEmpty()) {
            return false;
        }
        IFormattableTextComponent spacing = componentSpacing.copyRaw();
        tooltip.add(spacing.appendSibling(JetpackLang.translate("text.chargingitem").appendString(charging.getDisplayName().getString())));
        tooltip.add(spacing.appendSibling(JetpackLang.translate("text.charge").appendString((64000 - charging.getDamage()) + "/" + charging.getMaxDamage())));
        return true;
    }

    @Override
    protected void fromTag(BlockState state, CompoundNBT compound, boolean clientPacket) {
        this.charging = ItemStack.read(((CompoundNBT) compound.get("charging")));
        super.fromTag(state, compound, clientPacket);
    }

    @Override
    protected void write(CompoundNBT compound, boolean clientPacket) {
        compound.put("charging", charging.serializeNBT());
        super.write(compound, clientPacket);
    }
}
