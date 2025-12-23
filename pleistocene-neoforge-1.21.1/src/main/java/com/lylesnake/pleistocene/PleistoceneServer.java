package com.lylesnake.pleistocene;

import com.lylesnake.pleistocene.block.entity.choppingblock.ChoppingBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.LogicalSide;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = Pleistocene.MODID)
public class PleistoceneServer {

    @SubscribeEvent
    public static void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event)
    {
        if(event.getSide() == LogicalSide.CLIENT || event.getHand() == InteractionHand.OFF_HAND)
            return;

        BlockPos pos = event.getPos();
        BlockState state = event.getLevel().getBlockState(pos);

        if(state.getBlock() instanceof ChoppingBlock choppingBlock)
        {
            if(choppingBlock.handleWoodWorking(event.getEntity(), pos))
                event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onHarvestCheck(PlayerEvent.HarvestCheck event)
    {
        BlockState targetBlock = event.getTargetBlock();
        if(targetBlock.is(BlockTags.LOGS))
        {
            boolean canHarvest = false;

            ItemStack mainHand = event.getEntity().getMainHandItem();
            canHarvest = mainHand.getItem().isCorrectToolForDrops(mainHand, targetBlock);

            event.setCanHarvest(canHarvest && mainHand.canPerformAction(ItemAbilities.AXE_DIG));
        }
    }
}
