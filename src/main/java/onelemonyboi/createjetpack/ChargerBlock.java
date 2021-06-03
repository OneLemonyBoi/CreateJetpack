package onelemonyboi.createjetpack;

import com.simibubi.create.content.contraptions.base.HorizontalKineticBlock;
import com.simibubi.create.content.contraptions.components.clock.CuckooClockBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class ChargerBlock extends HorizontalKineticBlock {
    public ChargerBlock(Properties properties) {
        super(properties);
    }

    @Override
    public TileEntity createTileEntity(BlockState blockState, IBlockReader iBlockReader) {
        return TEList.ChargerTile.get().create();
    }

    @Override
    public Direction.Axis getRotationAxis(BlockState blockState) {
        return blockState.get(HORIZONTAL_FACING).getAxis();
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        Direction preferred = this.getPreferredHorizontalFacing(context);
        return preferred != null ? this.getDefaultState().with(HORIZONTAL_FACING, preferred.getOpposite()) : this.getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    public boolean hasShaftTowards(IWorldReader world, BlockPos pos, BlockState state, Direction face) {
        return face == state.get(HORIZONTAL_FACING).getOpposite();
    }

    public boolean allowsMovement(BlockState state, IBlockReader reader, BlockPos pos, PathType type) {
        return false;
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (worldIn.isRemote) {
            return ActionResultType.SUCCESS;
        }

        if (!(worldIn.getTileEntity(pos) instanceof ChargerTile)) {
            return ActionResultType.PASS;
        }

        ChargerTile tile = (ChargerTile) worldIn.getTileEntity(pos);

        if (!tile.charging.isEmpty()) {
            InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), tile.charging);
            tile.charging = ItemStack.EMPTY;
            return ActionResultType.CONSUME;
        }

        if (player.getHeldItem(handIn).getItem() instanceof JetpackItem) {
            tile.charging = player.getHeldItem(handIn);
            player.setHeldItem(handIn, ItemStack.EMPTY);
            return ActionResultType.SUCCESS;
        }

        return ActionResultType.PASS;
    }
}
