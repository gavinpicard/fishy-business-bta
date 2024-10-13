package glp.fishybusiness.blocks;

import net.minecraft.client.render.block.model.*;
import net.minecraft.client.render.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockFluid;
import net.minecraft.core.block.BlockFluidFlowing;
import net.minecraft.core.block.BlockFluidStill;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.item.block.ItemBlockLeaves;
import net.minecraft.core.sound.BlockSound;
import net.minecraft.core.sound.BlockSounds;
import turniplabs.halplibe.helper.BlockBuilder;

import static glp.fishybusiness.FishyBusiness.MOD_ID;

public class ModBlocks {
	public static Block riceBottom;
	public static Block debug;

	public void initializeBlocks() {
		riceBottom = new BlockBuilder(MOD_ID)
			.setHardness(100.0f)
			.setResistance(0.0f)
			.setTags(BlockTags.IS_WATER, BlockTags.PLACE_OVERWRITES)
			.build(new BlockFluid("riceBottom", 2000, Material.water) {
			});
	}

}
