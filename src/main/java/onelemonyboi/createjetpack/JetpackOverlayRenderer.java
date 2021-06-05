package onelemonyboi.createjetpack;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.CreateClient;
import com.simibubi.create.content.contraptions.components.structureMovement.IDisplayAssemblyExceptions;
import com.simibubi.create.content.contraptions.components.structureMovement.piston.MechanicalPistonBlock;
import com.simibubi.create.content.contraptions.components.structureMovement.piston.PistonExtensionPoleBlock;
import com.simibubi.create.content.contraptions.goggles.GoggleOverlayRenderer;
import com.simibubi.create.content.contraptions.goggles.IHaveGoggleInformation;
import com.simibubi.create.content.contraptions.goggles.IHaveHoveringInformation;
import com.simibubi.create.foundation.config.AllConfigs;
import com.simibubi.create.foundation.gui.GuiGameElement;
import com.simibubi.create.foundation.tileEntity.behaviour.ValueBox;
import com.simibubi.create.foundation.utility.Iterate;
import com.simibubi.create.foundation.utility.Lang;
import com.simibubi.create.foundation.utility.outliner.Outline;
import com.simibubi.create.foundation.utility.outliner.Outliner;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.*;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import onelemonyboi.createjetpack.content.JetpackItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.simibubi.create.content.contraptions.goggles.IHaveGoggleInformation.componentSpacing;

public class JetpackOverlayRenderer {
    public static void jetpackOverlayRenderer(RenderGameOverlayEvent.Post event) {
        MatrixStack ms = event.getMatrixStack();
        if (event.getType() != RenderGameOverlayEvent.ElementType.HOTBAR) {
            return;
        }

        Minecraft mc = Minecraft.getInstance();
        PlayerEntity player = mc.player;

        if (!(player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() instanceof JetpackItem)) {
            return;
        }

        JetpackItem item = (JetpackItem) player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem();
        ItemStack jetpack = player.getItemStackFromSlot(EquipmentSlotType.CHEST);


        IFormattableTextComponent spacing = componentSpacing.copyRaw();
        int posX, posY;
        List<ITextComponent> tooltip = new ArrayList();

        tooltip.add(spacing.appendSibling(JetpackLang.translate("text.charge")
                .appendString(jetpack.getDamage() + "/" + jetpack.getMaxDamage())));

        tooltip.add(spacing.appendSibling(JetpackLang.translate("text.estimatedusage")
                .appendString(String.valueOf(jetpack.getDamage() / 1200))
                .appendSibling(JetpackLang.translate("text.minutes"))));

        ms.push();
        Screen tooltipScreen = new TooltipScreen(null);
        tooltipScreen.init(mc, mc.getMainWindow().getScaledWidth(), mc.getMainWindow().getScaledHeight());
        int titleLinesCount = 1;
        int tooltipTextWidth = 0;

        for (ITextComponent iTextComponent : tooltip) {
            ITextProperties textLine = iTextComponent;
            posY = mc.fontRenderer.getStringPropertyWidth(textLine);
            if (posY > tooltipTextWidth) {
                tooltipTextWidth = posY;
            }
        }

        int tooltipHeight = 8;
        if (tooltip.size() > 1) {
            tooltipHeight += (tooltip.size() - 1) * 10;
            if (tooltip.size() > titleLinesCount) {
                tooltipHeight += 2;
            }
        }

        posX = tooltipScreen.width + 20;
        posY = tooltipScreen.height;
        posX = Math.min(posX, tooltipScreen.width - tooltipTextWidth - 20);
        posY = Math.min(posY, tooltipScreen.height - tooltipHeight - 20);
        tooltipScreen.func_243308_b(ms, tooltip, posX, posY);
        GuiGameElement.of(item).at(posX + 10, posY - 16, 450.0).render(ms);
        ms.pop();
    }

    private static final class TooltipScreen extends Screen {
        private TooltipScreen(ITextComponent p_i51108_1_) {
            super(p_i51108_1_);
        }

        public void init(Minecraft mc, int width, int height) {
            this.minecraft = mc;
            this.itemRenderer = mc.getItemRenderer();
            this.font = mc.fontRenderer;
            this.width = width;
            this.height = height;
        }
    }
}
