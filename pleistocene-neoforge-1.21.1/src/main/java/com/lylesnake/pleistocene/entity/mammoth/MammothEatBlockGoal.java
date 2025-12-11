package com.lylesnake.pleistocene.entity.mammoth;

import net.minecraft.world.entity.ai.goal.EatBlockGoal;

public class MammothEatBlockGoal extends EatBlockGoal {
    private final MammothEntity mammoth;

    public MammothEatBlockGoal(MammothEntity mammoth) {
        super(mammoth);
        this.mammoth = mammoth;
    }

    @Override
    public void start() {
        super.start();
        mammoth.level().broadcastEntityEvent(mammoth, (byte)10);
    }
}
