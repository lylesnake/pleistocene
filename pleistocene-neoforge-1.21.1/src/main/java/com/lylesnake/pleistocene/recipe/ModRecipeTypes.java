package com.lylesnake.pleistocene.recipe;


import com.lylesnake.pleistocene.Pleistocene;
import com.lylesnake.pleistocene.recipe.choppingblock.ChoppingBlockRecipe;
import com.lylesnake.pleistocene.recipe.dryingrack.DryingRackRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModRecipeTypes
{
	private static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, Pleistocene.MODID);
	
	public static final Supplier<RecipeType<ChoppingBlockRecipe>> CHOPPING_BLOCK = RECIPE_TYPES.register("chopping_block",
            () -> RecipeType.simple(ResourceLocation.fromNamespaceAndPath(Pleistocene.MODID, "chopping_block")));
	
	public static final Supplier<RecipeType<DryingRackRecipe>> DRYING_RACK = RECIPE_TYPES.register("drying_rack",
            () -> RecipeType.simple(ResourceLocation.fromNamespaceAndPath(Pleistocene.MODID, "drying_rack")));
	

	public static void register(IEventBus eventBus)
	{
		RECIPE_TYPES.register(eventBus);
	}
}
