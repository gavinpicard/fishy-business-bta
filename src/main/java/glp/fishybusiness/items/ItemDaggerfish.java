package glp.fishybusiness.items;

import glp.fishybusiness.entities.EntityDaggerfish;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.IDispensable;

import java.util.Random;

public class ItemDaggerfish extends Item implements IDispensable {
	public ItemDaggerfish(String name, int id) {
		super(name, id);
		this.maxStackSize = 16;
	}

	public ItemStack onUseItem(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		itemstack.consumeItem(entityplayer);
		world.playSoundAtEntity(entityplayer, entityplayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		if (!world.isClientSide) {
			world.entityJoinedWorld(new EntityDaggerfish(world, entityplayer));
		}
		return itemstack;
	}

	public void onDispensed(ItemStack itemStack, World world, double x, double y, double z, int xOffset, int zOffset, Random random) {
		EntityDaggerfish daggerfish = new EntityDaggerfish(world, x, y, z);
		daggerfish.setHeading((double)xOffset, 0.1, (double)zOffset, 1.1F, 6.0F);
		world.entityJoinedWorld(daggerfish);
	}
}
