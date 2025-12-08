package com.lylesnake.pleistocene.block.entity.renderer;

import com.lylesnake.pleistocene.block.entity.dryingrack.DryingRackBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.IItemHandler;

public class DryingRackBlockEntityRenderer implements BlockEntityRenderer<DryingRackBlockEntity>
{
	private final BlockEntityRendererProvider.Context context;
	
	public DryingRackBlockEntityRenderer(BlockEntityRendererProvider.Context context)
	{
		this.context = context;
	}
	
	@Override
	public void render(DryingRackBlockEntity blockEntity, float partialTick, PoseStack poseStack,
                       MultiBufferSource buffer, int packedLight, int packedOverlay)
	{
		IItemHandler itemHandler = blockEntity.getLevel().getCapability(Capabilities.ItemHandler.BLOCK, blockEntity.getBlockPos(), null);
		if(itemHandler != null)
		{
			ItemStack stack = itemHandler.getStackInSlot(DryingRackBlockEntity.SLOT);
			if(!stack.isEmpty())
			{
				poseStack.pushPose();
				
				switch(blockEntity.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING))
				{
					case NORTH ->
					{
						poseStack.translate(0.5f, 0.5f, 0.5f);
						poseStack.mulPose(Axis.YP.rotationDegrees(180f));
					}
					case EAST ->
					{
						poseStack.translate(0.5f, 0.5f, 0.5f);
						poseStack.mulPose(Axis.YP.rotationDegrees(90f));
					}
					case SOUTH ->
					{
						poseStack.translate(0.5f, 0.5f, 0.5f);
					}
					case WEST ->
					{
						poseStack.translate(0.5f, 0.5f, 0.5f);
						poseStack.mulPose(Axis.YP.rotationDegrees(-90f));
					}
				}

                poseStack.scale(0.6f, 0.6f, 0.6f);

				ItemRenderer itemRenderer = context.getItemRenderer();
				itemRenderer.renderStatic(stack, ItemDisplayContext.NONE, packedLight, packedOverlay,
                        poseStack, buffer, blockEntity.getLevel(), 0);
				
				poseStack.popPose();
			}
		}
	}
}
