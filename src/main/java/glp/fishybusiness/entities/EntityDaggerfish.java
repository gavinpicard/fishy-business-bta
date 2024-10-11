package glp.fishybusiness.entities;

import glp.fishybusiness.items.ModItems;
import net.minecraft.core.HitResult;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.projectile.EntityProjectile;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.DamageType;
import net.minecraft.core.world.World;

public class EntityDaggerfish extends EntityProjectile {

	public EntityDaggerfish(World world) {
		super(world);
		this.modelItem = ModItems.daggerfish;
	}

	public EntityDaggerfish(World world, EntityLiving entityliving) {
		super(world, entityliving);
		this.modelItem = ModItems.daggerfish;
	}

	public EntityDaggerfish(World world, double d, double d1, double d2) {
		super(world, d, d1, d2);
		this.modelItem = ModItems.daggerfish;
	}

	@Override
	public void onHit(HitResult hitResult) {
		if (hitResult.entity != null) {
			hitResult.entity.hurt(this.owner, 8, DamageType.COMBAT);
		}

		super.onHit(hitResult);
	}
}
