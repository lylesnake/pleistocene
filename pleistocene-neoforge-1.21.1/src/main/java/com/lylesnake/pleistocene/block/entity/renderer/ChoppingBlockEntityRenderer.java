package com.lylesnake.pleistocene.block.entity.renderer;

import com.lylesnake.pleistocene.block.entity.choppingblock.ChoppingBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.IItemHandler;

public class ChoppingBlockEntityRenderer implements BlockEntityRenderer<ChoppingBlockEntity>
{
	private final BlockEntityRendererProvider.Context context;
	
	public ChoppingBlockEntityRenderer(BlockEntityRendererProvider.Context context)
	{
		this.context = context;
	}
	
	@Override
	public void render(ChoppingBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay)
	{
		IItemHandler itemHandler = blockEntity.getLevel().getCapability(Capabilities.ItemHandler.BLOCK, blockEntity.getBlockPos(), null);
		if(itemHandler != null)
		{
			ItemStack stack = itemHandler.getStackInSlot(ChoppingBlockEntity.SLOT);
			if(!stack.isEmpty())
			{
				poseStack.pushPose();
				
				poseStack.scale(0.5f, 0.5f, 0.5f);
				poseStack.translate(1, 1.5, 1);
				
				ItemRenderer itemRenderer = context.getItemRenderer();
				itemRenderer.renderStatic(stack, ItemDisplayContext.NONE, packedLight, packedOverlay, poseStack, buffer, blockEntity.getLevel(), 0);
				
				poseStack.popPose();
			}
		}
	}
}
