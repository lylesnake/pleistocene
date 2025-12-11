package com.lylesnake.pleistocene.entity.mammoth;

import com.lylesnake.pleistocene.Pleistocene;
import com.lylesnake.pleistocene.entity.ModEntities;
import com.lylesnake.pleistocene.sound.ModSounds;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootTable;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public class MammothEntity extends Animal implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private int eatAnimationTick;
    private MammothEatBlockGoal eatBlockGoal;
    private static final int EAT_ANIMATION_TICKS = 40;


    public MammothEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0));

        this.goalSelector.addGoal(2, new TemptGoal(this, 1.25,
                stack -> stack.is(Items.SUGAR_CANE), false));
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this).setAlertOthers());

        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.5, false) {
            @Override
            protected boolean canPerformAttack(LivingEntity entity) {
                return this.isTimeToAttack() && this.mob.distanceToSqr(entity) <
                        (this.mob.getBbWidth() * this.mob.getBbWidth() + entity.getBbWidth())
                        && this.mob.getSensing().hasLineOfSight(entity);}});

        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.25));
        this.goalSelector.addGoal(6, new AvoidEntityGoal<>(this, Wolf.class, (float) 6, 2, 1.5));

        this.eatBlockGoal = new MammothEatBlockGoal(this);
        this.goalSelector.addGoal(7, this.eatBlockGoal);

        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 30)
                .add(Attributes.MOVEMENT_SPEED, 0.15)
                .add(Attributes.ARMOR, 5)
                .add(Attributes.FOLLOW_RANGE, 12)
                .add(Attributes.ATTACK_DAMAGE, 15)
                .add(Attributes.STEP_HEIGHT, 2);
    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return itemStack.is(Items.SUGAR_CANE);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return ModEntities.MAMMOTH.get().create(serverLevel);
    }

    public void ate() {
        super.ate();
        if (this.isBaby()) {
            this.ageUp(60);
        }

    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(MammothAnimations.attackController(this));
        controllers.add(MammothAnimations.fallController(this));
        controllers.add(MammothAnimations.swimController(this));
        controllers.add(MammothAnimations.eatController(this));
        controllers.add(MammothAnimations.speakController(this));
        controllers.add(MammothAnimations.movementController(this));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    protected void customServerAiStep() {
        this.eatAnimationTick = this.eatBlockGoal.getEatAnimationTick();
        super.customServerAiStep();
    }

    public void aiStep() {
        if (this.level().isClientSide) {
            this.eatAnimationTick = Math.max(0, this.eatAnimationTick - 1);
        }

        super.aiStep();
    }

    public void handleEntityEvent(byte id) {
        if (id == 10) {
            this.eatAnimationTick = 40;
        } else {
            super.handleEntityEvent(id);
        }
    }

    public boolean isEating() {
        return this.eatAnimationTick > 0;
    }

    public boolean isAttacking() {
        return this.swinging;
    }

    public boolean isFalling() {
        return !this.onGround() && this.getDeltaMovement().y < -0.1;
    }


    private int speakCooldown = 0;
    private int speakTicks = 0;

    public boolean isSpeaking() {
        return this.speakTicks > 0;
    }

    @Override
    public void tick() {
        super.tick();

        if (speakCooldown > 0) speakCooldown--;

        // random chance to speak
        if (speakCooldown == 0 && this.random.nextInt(1000) == 0) {
            this.speakTicks = 20;
            this.speakCooldown = 200;
        }

        if (speakTicks > 0) speakTicks--;
    }


    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        if (this.isSpeaking()) {
        return ModSounds.MAMMOTH_AMBIENT.get();
        } return null;
    }

    @Override
    protected @Nullable SoundEvent getHurtSound(DamageSource damageSource) {
        return ModSounds.MAMMOTH_HURT.get();
    }

    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return ModSounds.MAMMOTH_DEATH.get();
    }

    @Override
    protected ResourceKey<LootTable> getDefaultLootTable() {
        return ResourceKey.create(
                Registries.LOOT_TABLE,
                ResourceLocation.fromNamespaceAndPath(Pleistocene.MODID, "entities/mammoth")
        );
    }
}
