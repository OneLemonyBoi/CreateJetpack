package onelemonyboi.createjetpack;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.world.World;

public class JetpackItem extends ArmorItem {
    boolean isHovering, isDisabled;

    public JetpackItem(Properties builderIn) {
        super(JetpackMaterial.JETPACK, EquipmentSlotType.CHEST, builderIn);
        isHovering = false;
        isDisabled = false;
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        if (!isHovering || !(stack.getItem() instanceof JetpackItem) || stack.getDamage() >= stack.getMaxDamage()) {
            return;
        }
        JetpackItem item = (JetpackItem) stack.getItem();
        item.isDisabled = stack.attemptDamageItem(1, random, player instanceof ServerPlayerEntity ? (ServerPlayerEntity) player : null);
        if (isDisabled) {
            return;
        }
        player.setMotion(player.getMotion().mul(1, 0, 1).add(0, 0.25, 0));
    }

    @Override
    public boolean isRepairable(ItemStack stack) {
        return false;
    }
}
