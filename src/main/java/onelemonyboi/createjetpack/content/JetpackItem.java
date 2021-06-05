package onelemonyboi.createjetpack.content;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.world.World;

public class JetpackItem extends ArmorItem {
    public boolean isActive, isDisabled, isHovering, playerJumping, playerSneaking;

    public JetpackItem(Properties builderIn) {
        super(JetpackMaterial.JETPACK, EquipmentSlotType.CHEST, builderIn);
        isActive = false;
        isHovering = false;
        isDisabled = false;
        playerJumping = false;
        playerSneaking = false;
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        if (!isActive || !(stack.getItem() instanceof JetpackItem) || stack.getDamage() >= stack.getMaxDamage() || player == null) {
            return;
        }
        JetpackItem item = (JetpackItem) stack.getItem();
        item.isDisabled = stack.attemptDamageItem(1, random, player instanceof ServerPlayerEntity ? (ServerPlayerEntity) player : null);
        if (isDisabled) {
            return;
        }
        double mx = 0.25;
        if (item.isHovering) {
            mx = 0;
            mx += item.playerJumping ? 0.25 : 0;
            mx += item.playerSneaking ? -0.25: 0;
        }
        player.setMotion(player.getMotion().mul(1, 0, 1).add(0, mx, 0));
    }

    @Override
    public boolean isRepairable(ItemStack stack) {
        return false;
    }
}
