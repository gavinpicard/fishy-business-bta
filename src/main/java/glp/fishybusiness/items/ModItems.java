package glp.fishybusiness.items;

import glp.fishybusiness.items.ItemDaggerfish;
import net.minecraft.core.item.*;
import net.minecraft.core.item.material.ArmorMaterial;
import turniplabs.halplibe.helper.ArmorHelper;
import turniplabs.halplibe.helper.CreativeHelper;
import turniplabs.halplibe.helper.ItemBuilder;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.client.render.stitcher.TextureRegistry;

import static glp.fishybusiness.FishyBusiness.MOD_ID;

public class ModItems {
	public static int itemId = 1000;

	public static Item lavaproofString;
	public static ItemFood salmon;
	public static ItemFood seaweed;
	public static ItemDaggerfish daggerfish;

	public void initializeItems() {

		lavaproofString = new ItemBuilder(MOD_ID)
			.setIcon("fishybusiness:item/lavaproof_string")
			.build(new Item("lavaproofString", itemId++));

		salmon = new ItemBuilder(MOD_ID)
			.setIcon("fishybusiness:item/salmon")
			.build(new ItemFood("salmon", itemId++, 2, 10, true, 8));

		daggerfish = new ItemBuilder(MOD_ID)
			.setIcon("fishybusiness:item/daggerfish")
			.build(new ItemDaggerfish("daggerfish", itemId++));

		seaweed = new ItemBuilder(MOD_ID)
			.setIcon("fishybusiness:item/seaweed")
			.build(new ItemFood("seaweed", itemId++, 1, 10, false, 8));
	}

}
