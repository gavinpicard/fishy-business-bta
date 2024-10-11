package glp.fishybusiness.items;

import glp.fishybusiness.items.ItemDaggerfish;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemArmor;
import net.minecraft.core.item.ItemFood;
import net.minecraft.core.item.ItemPebble;
import net.minecraft.core.item.ItemEgg;
import net.minecraft.core.item.material.ArmorMaterial;
import turniplabs.halplibe.helper.ArmorHelper;
import turniplabs.halplibe.helper.CreativeHelper;
import turniplabs.halplibe.helper.ItemBuilder;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.client.render.stitcher.TextureRegistry;

import static glp.fishybusiness.FishyBusiness.MOD_ID;

public class ModItems {
	public static int itemId = 1000;

	public static ItemFood rawSalmon;
	public static ItemDaggerfish daggerfish;

	public void initializeItems() {

		rawSalmon = new ItemBuilder(MOD_ID)
			.setIcon("fishybusiness:item/salmon")
			.build(new ItemFood("salmon", itemId++, 2, 20, false, 4));

		daggerfish = new ItemBuilder(MOD_ID)
			.setIcon("fishybusiness:item/daggerfish")
			.build(new ItemDaggerfish("daggerfish", itemId++));
	}

}
