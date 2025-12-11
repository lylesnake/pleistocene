package com.lylesnake.pleistocene.entity.mammoth;

import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;

import java.util.function.BiFunction;

public class MammothAnimations {
    public static final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.mammoth.idle_1_90");
    public static final RawAnimation ATTACK = RawAnimation.begin().thenPlay("animation.mammoth.attack");
    public static final RawAnimation EAT = RawAnimation.begin().thenLoop("animation.mammoth.eat");
    public static final RawAnimation FALL = RawAnimation.begin().thenLoop("animation.mammoth.jump/fall");
    public static final RawAnimation RUN = RawAnimation.begin().thenLoop("animation.mammoth.run");
    public static final RawAnimation SPEAK = RawAnimation.begin().thenLoop("animation.mammoth.speak");
    public static final RawAnimation SWIM = RawAnimation.begin().thenLoop("animation.mammoth.swim");
    public static final RawAnimation WALK = RawAnimation.begin().thenLoop("animation.mammoth.walk");

    public static <T extends LivingEntity & GeoAnimatable> AnimationController<T>  movementController(T animatable) {
        return new AnimationController<>(animatable, "Movement", 5, state -> {

            if (animatable instanceof MammothEntity mammoth) {
                // Decide between walk and run depending on speed
                double speed = mammoth.getDeltaMovement().horizontalDistanceSqr();

                if (speed > 0.0002) {
                    if (speed > 0.01) return state.setAndContinue(RUN);
                    return state.setAndContinue(WALK);
                }
            }
            return state.setAndContinue(IDLE);
        });
    }

    public static <T extends LivingEntity & GeoAnimatable> AnimationController<T> attackController(T animatable) {
        return new AnimationController<>(animatable, "Attack", 5, state -> {

            if (animatable instanceof MammothEntity mammoth && mammoth.isAttacking()) {
                return state.setAndContinue(ATTACK);
            }

            state.getController().forceAnimationReset();
            return PlayState.STOP;
        });
    }

    public static <T extends LivingEntity & GeoAnimatable> AnimationController<T> swimController(T animatable) {
        return new AnimationController<>(animatable, "Swim", 5, state -> {
            if (animatable instanceof MammothEntity mammoth && mammoth.isSwimming()) {
                return state.setAndContinue(SWIM);
            }
            state.getController().forceAnimationReset();
            return PlayState.STOP;
        });
    }


    public static <T extends LivingEntity & GeoAnimatable> AnimationController<T> eatController(T animatable) {
        return new AnimationController<>(animatable, "Eat", 5, state -> {
            if (animatable instanceof MammothEntity mammoth && mammoth.isEating()) {
                return state.setAndContinue(EAT);
            }
            state.getController().forceAnimationReset();
            return PlayState.STOP;
        });
    }


    public static <T extends LivingEntity & GeoAnimatable> AnimationController<T> fallController(T animatable) {
        return new AnimationController<>(animatable, "Fall", 5, state -> {
            if (animatable instanceof MammothEntity mammoth && mammoth.isFalling()) {
                return state.setAndContinue(FALL);
            }
            state.getController().forceAnimationReset();
            return PlayState.STOP;
        });
    }

    public static <T extends LivingEntity & GeoAnimatable> AnimationController<T> speakController(T animatable) {
        return new AnimationController<>(animatable, "Speak", 5, state -> {
            if (animatable instanceof MammothEntity mammoth && mammoth.isSpeaking()) {
                return state.setAndContinue(SPEAK);
            }
            state.getController().forceAnimationReset();
            return PlayState.STOP;
        });
    }

    /*
    Animation json credits: TeamFossilsArcheology
     */

}
