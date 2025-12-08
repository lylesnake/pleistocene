package com.lylesnake.pleistocene.recipe.choppingblock;

import com.lylesnake.pleistocene.Pleistocene;
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

public class ChoppingBlockRecipeBuilder implements RecipeBuilder
{
	private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
	
	private final Ingredient ingredient;
	private final Item result;
	private final int count;
	
	private String group;
	
	private ChoppingBlockRecipeBuilder(Ingredient ingredient, Item result, int count)
	{
		this.ingredient = ingredient;
		this.result = result;
		this.count = count;
	}
	
	public static ChoppingBlockRecipeBuilder choppingBlock(Ingredient ingredient, ItemStack result)
	{
		return new ChoppingBlockRecipeBuilder(ingredient, result.getItem(), result.getCount());
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
		return result;
	}
	
	@Override
	public void save(RecipeOutput recipeOutput, ResourceLocation id)
	{
		ResourceLocation name = ResourceLocation.fromNamespaceAndPath(Pleistocene.MODID, "chopping_block/" + id.getPath());
		ensureValid(name);
		
		Advancement.Builder advancementBuilder = recipeOutput.advancement()
				.addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(name))
				.rewards(AdvancementRewards.Builder.recipe(name))
				.requirements(AdvancementRequirements.Strategy.OR);
		criteria.forEach(advancementBuilder::addCriterion);
		
		recipeOutput.accept(name, new ChoppingBlockRecipe(ingredient, new ItemStack(result, count)),
                advancementBuilder.build(ResourceLocation.fromNamespaceAndPath(Pleistocene.MODID,
                        "recipes/chopping_block/" + id.getPath())));
	}
	
	private void ensureValid(ResourceLocation id)
	{
		if(criteria.isEmpty())
			throw new IllegalStateException("No way of obtaining recipe " + id);
	}
}
