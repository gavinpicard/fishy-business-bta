package glp.fishybusiness.entities;

import glp.fishybusiness.items.ModItems;
import com.mojang.nbt.CompoundTag;
import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.monster.EntityMonster;
import net.minecraft.core.entity.monster.EntityPigZombie;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.enums.LightLayer;
import net.minecraft.core.util.collection.NamespaceID;
import net.minecraft.core.util.helper.DamageType;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.world.World;

import java.util.Random;

public class EntityFlameSpider extends EntityMonster {
	private int lastFireParticle;

	public EntityFlameSpider(World world) {
		super(world);
		this.textureIdentifier = new NamespaceID("fishybusiness", "flame_spider");
		this.setSize(1.4F, 0.9F);
		this.moveSpeed = 0.8F;
		this.fireImmune = true;
		this.attackStrength = 3;
		this.scoreValue = 200;
		this.mobDrops.add(new WeightedRandomLootObject(ModItems.lavaproofString.getDefaultStack(), 0, 2));
	}

	private void flameSpiderParticle() {
		double f1 = this.random.nextFloat() * 1 - 0.5;
		double f2 = this.random.nextFloat();
		double f3 = this.random.nextFloat() * 1 - 0.5;
		this.world.spawnParticle("flame", this.x + f1, this.y + f2, this.z + f3, 0, 0, 0, 2);
	}

	protected void init() {
		super.init();
		this.entityData.define(16, (byte)1);
	}

	public void tick() {
		super.tick();
		if (this.random.nextFloat() > 0.92) {
			flameSpiderParticle();
		}
	}

	public boolean hurt(Entity attacker, int i, DamageType type) {
		super.hurt(attacker, i, type);
		if (type == DamageType.COMBAT) {
			for (int j = 0; j < 5; j++) flameSpiderParticle();
		}
		return false;
	}

	public void spawnInit() {
		super.init();
		if (this.world.difficultySetting != 0 && this.random.nextInt(100 / this.world.difficultySetting) == 0) {
			EntityPigZombie entityPigZombie = new EntityPigZombie(this.world);
			entityPigZombie.moveTo(this.x, this.y, this.z, this.yRot, 0.0F);
			this.world.entityJoinedWorld(entityPigZombie);
			entityPigZombie.startRiding(this);
		}

	}

	public double getRideHeight() {
		return (double)this.bbHeight * 0.75 - 0.5;
	}

	protected boolean makeStepSound() {
		return false;
	}

	protected Entity findPlayerToAttack() {
		float f = this.getBrightness(1.0F);
		if (f < 0.5F) {
			double d = 16.0;
			EntityPlayer p = this.world.getClosestPlayerToEntity(this, d);
			return p != null && !p.gamemode.areMobsHostile() ? null : p;
		} else {
			return null;
		}
	}

	public String getLivingSound() {
		return "mob.spider";
	}

	protected String getHurtSound() {
		return "mob.spider";
	}

	protected String getDeathSound() {
		return "mob.spiderdeath";
	}

	public boolean getCanSpawnHere() {
		int x = MathHelper.floor_double(this.x);
		int y = MathHelper.floor_double(this.y);
		int z = MathHelper.floor_double(this.z);
		if (this.world.getBlockId(x, y, z) != 0) {
			return false;
		} else {
			int blockLight = this.world.getSavedLightValue(LightLayer.Block, x, y, z);
			if (blockLight > 7) {
				return false;
			} else {
				return this.world.difficultySetting > 0 && this.world.checkIfAABBIsClear(this.bb) && this.world.getCubes(this, this.bb).size() == 0 && !this.world.getIsAnyLiquid(this.bb);
			}
		}
	}

	protected void attackEntity(Entity entity, float distance) {
		float brightness = this.getBrightness(1.0F);
		double fire;
		if (brightness > 0.5F && this.random.nextInt(100) == 0) {
			this.entityToAttack = null;
		} else {
			if (distance > 2.0F && distance < 6.0F && this.random.nextInt(10) == 0) {
				if (this.onGround) {
					double d = entity.x - this.x;
					double d1 = entity.z - this.z;
					float f2 = MathHelper.sqrt_double(d * d + d1 * d1);
					this.xd = d / (double)f2 * 0.5 * 0.800000011920929 + this.xd * 0.20000000298023224;
					this.zd = d1 / (double)f2 * 0.5 * 0.800000011920929 + this.zd * 0.20000000298023224;
					this.yd = 0.4000000059604645;
				}
			} else {
				if (this.attackTime <= 0 && distance < 2.0F && entity.bb.maxY > this.bb.minY && entity.bb.minY < this.bb.maxY) {
					this.attackTime = 20;
					entity.hurt(this, this.attackStrength, DamageType.COMBAT);
					fire = this.random.nextDouble();
					if (fire > 0.8) {
						entity.remainingFireTicks = 60;
					}
				}
			}

		}
	}

	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
	}

	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
	}

	public boolean canClimb() {
		return this.horizontalCollision;
	}
}
