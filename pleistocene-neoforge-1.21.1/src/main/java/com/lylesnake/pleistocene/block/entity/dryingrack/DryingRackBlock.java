package com.lylesnake.pleistocene.block.entity.dryingrack;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.MapCodec;
import com.lylesnake.pleistocene.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.IItemHandler;
import org.jetbrains.annotations.Nullable;

public class DryingRackBlock extends BaseEntityBlock
{
	public static final MapCodec<DryingRackBlock> CODEC = simpleCodec(DryingRackBlock::new);
	
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	
	public DryingRackBlock(Properties properties) {
		super(properties);
		registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(BlockStateProperties.WATERLOGGED, Boolean.FALSE));
	}
	
	@Override
	protected MapCodec<DryingRackBlock> codec()
	{
		return CODEC;
	}
	
	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
		return defaultBlockState().setValue(FACING, context.getHorizontalDirection()).setValue(BlockStateProperties.WATERLOGGED, fluidstate.getType() == Fluids.WATER);
	}
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, BlockStateProperties.WATERLOGGED);
	}
	
	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}


	@Override
	public boolean canSurvive(BlockState state, LevelReader levelReader, BlockPos pos) {
        return levelReader.getFluidState(pos).isEmpty();
	}


	private static final ImmutableMap<Direction, VoxelShape> BOUNDS;
	
	static {
		ImmutableMap.Builder<Direction, VoxelShape> builder = ImmutableMap.builder();
		builder.put(Direction.NORTH, Block.box(0, 0, 2, 15, 13, 13));
		builder.put(Direction.SOUTH, Block.box(2, 0, 0, 13, 13, 15));
		builder.put(Direction.EAST, Block.box(0, 2, 0, 13, 15, 13));
		builder.put(Direction.WEST, Block.box(0, 2, 0, 13, 15, 13));
		builder.put(Direction.UP, Block.box(0, 0, 2, 15, 13, 13));
		BOUNDS = builder.build();
	}
	
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return (VoxelShape)BOUNDS.get(state.getValue(FACING));
	}
	
	@Override
	public RenderShape getRenderShape(BlockState state)
	{
		return RenderShape.MODEL;
	}
	
	@Override
	public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
		if(state.getBlock() != newState.getBlock()) {
			BlockEntity blockEntity = level.getBlockEntity(pos);
			if(blockEntity instanceof DryingRackBlockEntity dryingRackBlockEntity)
				dryingRackBlockEntity.dropContents();
		}
		
		super.onRemove(state, level, pos, newState, isMoving);
	}

	@Override
	public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
		if(!level.isClientSide() && level.getBlockEntity(pos) instanceof DryingRackBlockEntity) {
			IItemHandler itemHandler = level.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
			if(itemHandler != null) {
				ItemStack extract = itemHandler.extractItem(DryingRackBlockEntity.SLOT, 1, true);
				if(extract.isEmpty()) {
					if(!player.getItemInHand(hand).isEmpty()) {
						if(!(player.getItemInHand(hand).getItem() instanceof BlockItem)) {
                            player.setItemInHand(hand, itemHandler.insertItem(DryingRackBlockEntity.SLOT,
                                                 player.getItemInHand(hand), false));
                            level.playSound(player, pos, SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.BLOCKS, 1f, 2f);
                        }
                    }
				} else if(player.getInventory().add(extract)) {
                    itemHandler.extractItem(DryingRackBlockEntity.SLOT, 1, false);
                    level.playSound(player, pos, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 1f, 2f);
                }

            }
		}
		return ItemInteractionResult.sidedSuccess(!level.isClientSide());
	}
	
	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new DryingRackBlockEntity(pos, state);
	}
	
	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> entityType) {
		return createTickerHelper(entityType, ModBlockEntities.DRYING_RACK_BE.get(), DryingRackBlockEntity::tick);
	}
}
