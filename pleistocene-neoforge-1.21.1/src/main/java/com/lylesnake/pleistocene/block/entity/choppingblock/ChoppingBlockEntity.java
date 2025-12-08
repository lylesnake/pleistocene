package com.lylesnake.pleistocene.block.entity.choppingblock;

import com.lylesnake.pleistocene.block.entity.ModBlockEntities;
import com.lylesnake.pleistocene.recipe.ModRecipeTypes;
import com.lylesnake.pleistocene.recipe.choppingblock.ChoppingBlockRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class ChoppingBlockEntity extends BlockEntity
{
	public static final int SLOT = 0;

	private final ItemStackHandler inventory = new ItemStackHandler(1)
	{
		@Override
		protected void onContentsChanged(int slot)
		{
			setChanged();
			if(level != null)
				level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 2);
		}
		
		@Override
		protected int getStackLimit(int slot, @NotNull ItemStack stack)
		{
			return 1;
		}
	};
	
	public ChoppingBlockEntity(BlockPos pos, BlockState state)
	{
		super(ModBlockEntities.CHOPPING_BLOCK_BE.get(), pos, state);
	}
	
	@Override
	protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries)
	{
		tag.put("inventory", inventory.serializeNBT(registries));
		super.saveAdditional(tag, registries);
	}
	
	@Override
	public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries)
	{
		super.loadAdditional(tag, registries);
		inventory.deserializeNBT(registries, tag.getCompound("inventory"));
	}
	
	@Override
	public @Nullable Packet<ClientGamePacketListener> getUpdatePacket()
	{
		return ClientboundBlockEntityDataPacket.create(this);
	}
	
	@Override
	public CompoundTag getUpdateTag(HolderLookup.Provider registries)
	{
		return saveWithoutMetadata(registries);
	}
	
	public void dropContents()
	{
		SimpleContainer container = new SimpleContainer(inventory.getSlots());
		
		for(int i = 0; i < inventory.getSlots(); i++)
			container.setItem(i, inventory.getStackInSlot(i));
		
		Containers.dropContents(level, worldPosition, container);
	}
	
	public boolean canDoWork()
	{
		ItemStack input = inventory.getStackInSlot(SLOT);
		if(input.isEmpty())
			return false;
		
		ChoppingBlockRecipe choppingBlockRecipe = getRecipe();
		if(choppingBlockRecipe == null)
			return false;
		
		ItemStack result = choppingBlockRecipe.assemble(null, level.registryAccess());
		return !result.isEmpty();
	}
	
	public ChoppingBlockRecipe getRecipe()
	{
		return getRecipe(level, inventory.getStackInSlot(SLOT));
	}
	
	public static ChoppingBlockRecipe getRecipe(Level level, ItemStack input)
	{
		Optional<RecipeHolder<ChoppingBlockRecipe>> recipe = level.getRecipeManager().getRecipeFor(ModRecipeTypes.CHOPPING_BLOCK.get(), new SingleRecipeInput(input), level);
		if(recipe.isPresent())
			return recipe.get().value();
		
		return null;
	}
	
	public static void registerCapabilities(RegisterCapabilitiesEvent event)
	{
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.CHOPPING_BLOCK_BE.get(), (blockEntity, side) -> blockEntity.inventory);
	}
}
