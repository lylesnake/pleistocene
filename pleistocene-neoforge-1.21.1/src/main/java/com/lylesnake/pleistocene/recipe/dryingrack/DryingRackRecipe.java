package com.lylesnake.pleistocene.recipe.dryingrack;

import com.lylesnake.pleistocene.block.ModBlocks;
import com.lylesnake.pleistocene.recipe.ModRecipeSerializers;
import com.lylesnake.pleistocene.recipe.ModRecipeTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public record DryingRackRecipe(Ingredient ingredient, ItemStack result, int dryingTime) implements Recipe<SingleRecipeInput>
{
	@Override
	public ItemStack getToastSymbol()
	{
        return new ItemStack(ModBlocks.DRYING_RACK.get());
	}
	
	@Override
	public boolean matches(SingleRecipeInput input, Level level)
	{
        return ingredient.test(input.getItem(0));
	}
	
	@Override
	public ItemStack assemble(SingleRecipeInput input, HolderLookup.Provider registries)
	{
		return getResultItem(registries).copy();
	}
	
	@Override
	public boolean canCraftInDimensions(int width, int height)
	{
		return false;
	}
	
	@Override
	public NonNullList<Ingredient> getIngredients()
	{
		NonNullList<Ingredient> ingredients = NonNullList.create();
		ingredients.add(ingredient);
		return ingredients;
	}
	
	@Override
	public ItemStack getResultItem(HolderLookup.Provider registries)
	{
		return result;
	}
	
	@Override
	public RecipeSerializer<?> getSerializer()
	{
		return ModRecipeSerializers.DRYING_RACK.get();
	}
	
	@Override
	public RecipeType<?> getType()
	{
		return ModRecipeTypes.DRYING_RACK.get();
	}
	
	public int getDryingTime()
	{
		return dryingTime;
	}
}
