package onelemonyboi.createjetpack;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.simibubi.create.foundation.tileEntity.behaviour.ValueBox;
import com.simibubi.create.foundation.utility.outliner.Outline;
import com.simibubi.create.foundation.utility.outliner.Outliner;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.glfw.GLFW;

import java.util.Iterator;

public class ClientEvents {
    public static void onKeyInput(InputEvent.KeyInputEvent event) {
        PlayerEntity player = Minecraft.getInstance().player;
        if (player == null) {
            return;
        }
        if (event.getKey() != GLFW.GLFW_KEY_SPACE) {
            return;
        }
//        if (Minecraft.getInstance().currentScreen != null) {
//            Packets.INSTANCE.sendToServer(new PlayerHoveringPacket(false));
//        }

            ItemStack chestItemStack = player.getItemStackFromSlot(EquipmentSlotType.CHEST);

        if (chestItemStack.getItem() instanceof JetpackItem && event.getAction() == GLFW.GLFW_PRESS) {
            CreateJetpack.LOGGER.info("Space Pressed");
            Packets.INSTANCE.sendToServer(new PlayerHoveringPacket(true));
        }
        else if (event.getAction() == GLFW.GLFW_RELEASE) {
            CreateJetpack.LOGGER.info("Space Released");
            Packets.INSTANCE.sendToServer(new PlayerHoveringPacket(false));
        }
    }
}
