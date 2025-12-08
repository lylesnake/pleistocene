package com.lylesnake.pleistocene.recipe.dryingrack;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.lylesnake.pleistocene.block.entity.dryingrack.DryingRackBlockEntity;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class DryingRackRecipeSerializer implements RecipeSerializer<DryingRackRecipe>
{
	public static final MapCodec<DryingRackRecipe> CODEC = RecordCodecBuilder.mapCodec(
            (inst) -> inst.group(Ingredient.CODEC_NONEMPTY.fieldOf("ingredient")
                    .forGetter(DryingRackRecipe::ingredient), ItemStack.CODEC.fieldOf("result")
                    .forGetter(DryingRackRecipe::result), Codec.INT.fieldOf("dryingtime")
                    .orElse(DryingRackBlockEntity.STANDARD_DRYING_TIME)
                    .forGetter(DryingRackRecipe::dryingTime))
                    .apply(inst, DryingRackRecipe::new));
	
	public static final StreamCodec<RegistryFriendlyByteBuf, DryingRackRecipe> STREAM_CODEC = StreamCodec.of(DryingRackRecipeSerializer::toNetwork, DryingRackRecipeSerializer::fromNetwork);
	
	@Override
	public MapCodec<DryingRackRecipe> codec()
	{
		return CODEC;
	}
	
	@Override
	public StreamCodec<RegistryFriendlyByteBuf, DryingRackRecipe> streamCodec()
	{
		return STREAM_CODEC;
	}
	
	private static DryingRackRecipe fromNetwork(RegistryFriendlyByteBuf buffer)
	{
		Ingredient ingredient = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
		ItemStack result = ItemStack.STREAM_CODEC.decode(buffer);
		int dryingTime = buffer.readVarInt();
		return new DryingRackRecipe(ingredient, result, dryingTime);
	}
	
	private static void toNetwork(RegistryFriendlyByteBuf buffer, DryingRackRecipe recipe)
	{
		Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.ingredient());
		ItemStack.STREAM_CODEC.encode(buffer, recipe.result());
		buffer.writeVarInt(recipe.dryingTime());
	}
}
