package com.lylesnake.pleistocene.block.entity.choppingblock;

import com.mojang.serialization.MapCodec;
import com.lylesnake.pleistocene.recipe.choppingblock.ChoppingBlockRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.items.IItemHandler;
import org.jetbrains.annotations.Nullable;

public class ChoppingBlock extends BaseEntityBlock {
	public static final MapCodec<ChoppingBlock> CODEC = simpleCodec(ChoppingBlock::new);
	
	public ChoppingBlock(Properties properties) {
		super(properties);
		registerDefaultState(stateDefinition.any().setValue(BlockStateProperties.WATERLOGGED, Boolean.FALSE));
	}
	
	@Override
	protected MapCodec<ChoppingBlock> codec()
	{
		return CODEC;
	}
	
	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
		return defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, fluidstate.getType() == Fluids.WATER);
	}
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.WATERLOGGED);
	}
	
	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}
	
	protected static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 8, 16);
	
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}
	
	@Override
	public RenderShape getRenderShape(BlockState state)
	{
		return RenderShape.MODEL;
	}
	
	@Override
	public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
		if(state.getBlock() != newState.getBlock())
		{
			BlockEntity blockEntity = level.getBlockEntity(pos);
			if(blockEntity instanceof ChoppingBlockEntity choppingBlockEntity)
				choppingBlockEntity.dropContents();
		}
		
		super.onRemove(state, level, pos, newState, isMoving);
	}
	
	public boolean handleWoodWorking(Player player, BlockPos pos) {
		Level level = player.level();
		
		if(level.isClientSide())
			return false;
		
		if(player.isCreative() || player.isShiftKeyDown())
			return false;
		
		if(!player.getMainHandItem().canPerformAction(ItemAbilities.AXE_DIG))
			return false;
		
		if(!(level.getBlockEntity(pos) instanceof ChoppingBlockEntity blockEntityChoppingBlock))
			return false;
		
		IItemHandler itemHandler = level.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
		if(itemHandler != null)
		{
			if(blockEntityChoppingBlock.canDoWork())
			{
				ChoppingBlockRecipe choppingBlockRecipe = blockEntityChoppingBlock.getRecipe();
				ItemStack result = choppingBlockRecipe.assemble(null, level.registryAccess());
				
				player.getMainHandItem().hurtAndBreak(2, player, EquipmentSlot.MAINHAND);
				itemHandler.extractItem(ChoppingBlockEntity.SLOT, 1, false);
				
				level.addFreshEntity(new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.75, pos.getZ() + 0.5, result));
				
				// player.awardStat(ModStats.CHOPPED_WOOD.get());
				
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
		if(level.getBlockEntity(pos) instanceof ChoppingBlockEntity) {
			IItemHandler itemHandler = level.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
			if(itemHandler != null) {
				ItemStack extract = itemHandler.extractItem(ChoppingBlockEntity.SLOT, 1, true);
				if(extract.isEmpty()) {
					if(!player.getItemInHand(hand).isEmpty()) {
						ChoppingBlockRecipe choppingBlockRecipe = ChoppingBlockEntity.getRecipe(level, player.getItemInHand(hand));
						if(choppingBlockRecipe != null) {
							if(!level.isClientSide())
								player.setItemInHand(hand, itemHandler.insertItem(ChoppingBlockEntity.SLOT, player.getItemInHand(hand), false));
							
							return ItemInteractionResult.sidedSuccess(!level.isClientSide());
						}
					}
				} else if(player.getInventory().add(extract)) {
					if(!level.isClientSide())
						itemHandler.extractItem(ChoppingBlockEntity.SLOT, 1, false);
					
					return ItemInteractionResult.sidedSuccess(!level.isClientSide());
				}
			}
		}
		
		return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
	}
	
	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
	{
		return new ChoppingBlockEntity(pos, state);
	}
}

