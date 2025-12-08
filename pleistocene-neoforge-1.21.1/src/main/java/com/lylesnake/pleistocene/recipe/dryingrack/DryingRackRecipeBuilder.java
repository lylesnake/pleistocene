package com.lylesnake.pleistocene.recipe.dryingrack;

import com.lylesnake.pleistocene.Pleistocene;
import com.lylesnake.pleistocene.block.entity.dryingrack.DryingRackBlockEntity;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.LinkedHashMap;
import java.util.Map;


public class DryingRackRecipeBuilder implements RecipeBuilder
{
	private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
	
	private final Ingredient ingredient;
	private final ItemStack result;
	private final int dryingTime;
	
	private String group;
	
	private DryingRackRecipeBuilder(Ingredient ingredient, ItemStack result, int dryingTime)
	{
		this.ingredient = ingredient;
		this.result = result;
		this.dryingTime = dryingTime;
	}
	
	public static DryingRackRecipeBuilder dryingRack(Ingredient ingredient, ItemStack result, int dryingTime)
	{
		return new DryingRackRecipeBuilder(ingredient, result, dryingTime);
	}
	
	public static DryingRackRecipeBuilder dryingRack(Ingredient ingredient, ItemStack result)
	{
		return dryingRack(ingredient, result, DryingRackBlockEntity.STANDARD_DRYING_TIME);
	}
	
	@Override
	public RecipeBuilder unlockedBy(String name, Criterion<?> criterion)
	{
		criteria.put(name, criterion);
		return this;
	}
	
	@Override
	public RecipeBuilder group(String group)
	{
		this.group = group;
		return this;
	}
	
	@Override
	public Item getResult()
	{
		return result.getItem();
	}
	
	@Override
	public void save(RecipeOutput recipeOutput, ResourceLocation id)
	{
		ResourceLocation name = ResourceLocation.fromNamespaceAndPath(Pleistocene.MODID, "drying_rack/" + id.getPath());
		ensureValid(name);
		
		Advancement.Builder advancementBuilder = recipeOutput.advancement()
				.addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(name))
				.rewards(AdvancementRewards.Builder.recipe(name))
				.requirements(AdvancementRequirements.Strategy.OR);
		criteria.forEach(advancementBuilder::addCriterion);
		
		recipeOutput.accept(name, new DryingRackRecipe(ingredient, result, dryingTime),
                advancementBuilder.build(ResourceLocation.fromNamespaceAndPath(Pleistocene.MODID,
                        "recipes/drying_rack/" + id.getPath())));
	}
	
	private void ensureValid(ResourceLocation id)
	{
		if(criteria.isEmpty())
			throw new IllegalStateException("No way of obtaining recipe " + id);
	}
}
