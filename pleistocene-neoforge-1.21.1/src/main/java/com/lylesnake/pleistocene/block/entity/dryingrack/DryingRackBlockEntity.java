package com.lylesnake.pleistocene.block.entity.dryingrack;

import com.lylesnake.pleistocene.block.entity.ModBlockEntities;
import com.lylesnake.pleistocene.recipe.ModRecipeTypes;
import com.lylesnake.pleistocene.recipe.dryingrack.DryingRackRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.ContainerData;
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

public class DryingRackBlockEntity extends BlockEntity
{
	public static final int STANDARD_DRYING_TIME = 2000;
	public static final int SLOT = 0;
	protected final ContainerData data;
	protected int dryingTicks;
	protected int neededDryingTicks = STANDARD_DRYING_TIME;
	
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
	
	public DryingRackBlockEntity(BlockPos pos, BlockState state)
	{
		super(ModBlockEntities.DRYING_RACK_BE.get(), pos, state);
		
		data = new ContainerData()
		{
			@Override
			public int get(int index)
			{
				return switch(index)
				{
					case 0 -> DryingRackBlockEntity.this.dryingTicks;
					case 1 -> DryingRackBlockEntity.this.neededDryingTicks;
					default -> 0;
				};
			}
			
			@Override
			public void set(int index, int value)
			{
				switch(index)
				{
					case 0 -> DryingRackBlockEntity.this.dryingTicks = value;
					case 1 -> DryingRackBlockEntity.this.neededDryingTicks = value;
				}
			}
			
			@Override
			public int getCount()
			{
				return 2;
			}
		};
	}
	
	@Override
	protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries)
	{
		tag.put("inventory", inventory.serializeNBT(registries));
		
		tag.putInt("dryingTicks", dryingTicks);
		tag.putInt("neededDryingTicks", neededDryingTicks);
		
		super.saveAdditional(tag, registries);
	}
	
	@Override
	public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries)
	{
		super.loadAdditional(tag, registries);
		
		inventory.deserializeNBT(registries, tag.getCompound("inventory"));
		
		dryingTicks = tag.getInt("dryingTicks");
		neededDryingTicks = tag.getInt("neededDryingTicks");
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
		
		DryingRackRecipe dryingRackRecipe = getRecipe();
		if(dryingRackRecipe == null)
			return false;
		
		ItemStack result = dryingRackRecipe.assemble(null, level.registryAccess());
		return !result.isEmpty();
	}
	
	public DryingRackRecipe getRecipe()
	{
		Optional<RecipeHolder<DryingRackRecipe>> recipe = level.getRecipeManager().getRecipeFor(ModRecipeTypes.DRYING_RACK.get(), new SingleRecipeInput(inventory.getStackInSlot(SLOT)), level);
		if(recipe.isPresent())
			return recipe.get().value();
		
		return null;
	}
	
	public static void tick(Level level, BlockPos pos, BlockState state, DryingRackBlockEntity dryingRackBlockEntity)
	{
		if(level.isClientSide())
			return;
		
		boolean hasChanged = false;
		
		if(dryingRackBlockEntity.canDoWork())
		{
			hasChanged = true;
			
			dryingRackBlockEntity.dryingTicks++;
			
			DryingRackRecipe dryingRackRecipe = dryingRackBlockEntity.getRecipe();
			dryingRackBlockEntity.neededDryingTicks = dryingRackRecipe.getDryingTime();
			if(dryingRackBlockEntity.dryingTicks >= dryingRackBlockEntity.neededDryingTicks)
			{
				dryingRackBlockEntity.dryingTicks = 0;
				
				ItemStack result = dryingRackRecipe.assemble(null, dryingRackBlockEntity.level.registryAccess());
				dryingRackBlockEntity.inventory.setStackInSlot(SLOT, result);
			}
		} else
			dryingRackBlockEntity.dryingTicks = 0;
		
		if(hasChanged)
			setChanged(level, pos, state);
	}
	
	public static void registerCapabilities(RegisterCapabilitiesEvent event)
	{
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.DRYING_RACK_BE.get(), (blockEntity, side) -> blockEntity.inventory);
	}
}
