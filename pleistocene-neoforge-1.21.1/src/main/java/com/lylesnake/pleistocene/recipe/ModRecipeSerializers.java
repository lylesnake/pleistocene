package com.lylesnake.pleistocene.recipe;

import com.lylesnake.pleistocene.Pleistocene;
import com.lylesnake.pleistocene.recipe.choppingblock.ChoppingBlockRecipe;
import com.lylesnake.pleistocene.recipe.choppingblock.ChoppingBlockRecipeSerializer;
import com.lylesnake.pleistocene.recipe.dryingrack.DryingRackRecipe;
import com.lylesnake.pleistocene.recipe.dryingrack.DryingRackRecipeSerializer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;


import java.util.function.Supplier;

public class ModRecipeSerializers {
	private static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, Pleistocene.MODID);

	public static final Supplier<RecipeSerializer<ChoppingBlockRecipe>> CHOPPING_BLOCK = RECIPE_SERIALIZERS
            .register("chopping_block", ChoppingBlockRecipeSerializer::new);
	
	public static final Supplier<RecipeSerializer<DryingRackRecipe>> DRYING_RACK = RECIPE_SERIALIZERS
            .register("drying_rack", DryingRackRecipeSerializer::new);

	public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZERS.register(eventBus);
	}
}
