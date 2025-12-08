package com.lylesnake.pleistocene.recipe.choppingblock;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class ChoppingBlockRecipeSerializer implements RecipeSerializer<ChoppingBlockRecipe>
{
	public static final MapCodec<ChoppingBlockRecipe> CODEC = RecordCodecBuilder.mapCodec((inst) -> inst.group(Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(ChoppingBlockRecipe::ingredient), ItemStack.CODEC.fieldOf("result").forGetter(ChoppingBlockRecipe::result)).apply(inst, ChoppingBlockRecipe::new));
	
	public static final StreamCodec<RegistryFriendlyByteBuf, ChoppingBlockRecipe> STREAM_CODEC = StreamCodec.of(ChoppingBlockRecipeSerializer::toNetwork, ChoppingBlockRecipeSerializer::fromNetwork);
	
	@Override
	public MapCodec<ChoppingBlockRecipe> codec()
	{
		return CODEC;
	}
	
	@Override
	public StreamCodec<RegistryFriendlyByteBuf, ChoppingBlockRecipe> streamCodec()
	{
		return STREAM_CODEC;
	}
	
	private static ChoppingBlockRecipe fromNetwork(RegistryFriendlyByteBuf buffer)
	{
		Ingredient ingredient = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
		ItemStack result = ItemStack.STREAM_CODEC.decode(buffer);
		return new ChoppingBlockRecipe(ingredient, result);
	}
	
	private static void toNetwork(RegistryFriendlyByteBuf buffer, ChoppingBlockRecipe recipe)
	{
		Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.ingredient());
		ItemStack.STREAM_CODEC.encode(buffer, recipe.result());
	}
}
