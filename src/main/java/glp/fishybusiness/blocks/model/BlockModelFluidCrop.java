package glp.fishybusiness.blocks.model;

import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.LightmapHelper;
import net.minecraft.client.render.block.color.BlockColor;
import net.minecraft.client.render.block.color.BlockColorDispatcher;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockFluid;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.WorldSource;

public class BlockModelFluidCrop<T extends Block> extends BlockModelStandard<T> {
	public BlockModelFluidCrop(Block block) {
		super(block);
	}

	public boolean render(Tessellator tessellator, int x, int y, int z) {
		this.block.setBlockBoundsBasedOnState(renderBlocks.blockAccess, x, y, z);
		int color = ((BlockColor)BlockColorDispatcher.getInstance().getDispatch(this.block)).getWorldColor(renderBlocks.blockAccess, x, y, z);
		float r = (float)(color >> 16 & 255) / 255.0F;
		float g = (float)(color >> 8 & 255) / 255.0F;
		float b = (float)(color & 255) / 255.0F;
		boolean renderTop = this.shouldSideBeRendered(renderBlocks.blockAccess, x, y + 1, z, 1);
		boolean flag1 = this.shouldSideBeRendered(renderBlocks.blockAccess, x, y - 1, z, 0);
		boolean[] aflag = new boolean[]{this.shouldSideBeRendered(renderBlocks.blockAccess, x, y, z - 1, 2), this.shouldSideBeRendered(renderBlocks.blockAccess, x, y, z + 1, 3), this.shouldSideBeRendered(renderBlocks.blockAccess, x - 1, y, z, 4), this.shouldSideBeRendered(renderBlocks.blockAccess, x + 1, y, z, 5)};
		if (!renderTop && !flag1 && !aflag[0] && !aflag[1] && !aflag[2] && !aflag[3]) {
			return false;
		} else {
			boolean didRender = false;
			float sideBottom = 0.5F;
			float sideTop = 1.0F;
			float sideNorthSouth = 0.8F;
			float sideEastWest = 0.6F;
			double d = 0.0;
			double d1 = 1.0;
			float yOff = 0.001F;
			Material material = this.block.blockMaterial;
			int meta = renderBlocks.blockAccess.getBlockMetadata(x, y, z);
			float f7 = BlockFluid.getFluidHeight(renderBlocks.blockAccess, x, y, z, material);
			float f8 = BlockFluid.getFluidHeight(renderBlocks.blockAccess, x, y, z + 1, material);
			float f9 = BlockFluid.getFluidHeight(renderBlocks.blockAccess, x + 1, y, z + 1, material);
			float f10 = BlockFluid.getFluidHeight(renderBlocks.blockAccess, x + 1, y, z, material);
			int zB;
			float f15;
			float f17;
			double d4;
			double d5;
			if (renderBlocks.renderAllFaces || renderTop) {
				didRender = true;
				IconCoordinate texture = this.getBlockTextureFromSideAndMetadata(Side.TOP, meta);
				float rotation = (float)BlockFluid.getSlopeAngle(renderBlocks.blockAccess, x, y, z, material);
				if (rotation > -999.0F) {
					texture = this.getBlockTextureFromSideAndMetadata(Side.NORTH, meta);
				} else {
					rotation = 0.0F;
				}

				float blockBrightness = 1.0F;
				if (LightmapHelper.isLightmapEnabled()) {
					zB = this.block.getLightmapCoord(renderBlocks.blockAccess, x, y, z);
					int lightUp = this.block.getLightmapCoord(renderBlocks.blockAccess, x, y + 1, z);
					tessellator.setLightmapCoord(LightmapHelper.max(zB, lightUp));
				} else {
					blockBrightness = this.getBlockBrightness(renderBlocks.blockAccess, x, y, z);
				}

				if (texture.namespaceId.toString().contains("_flowing")) {
					double centerU = texture.getSubIconU(0.5);
					double centerV = texture.getSubIconV(0.5);
					f15 = MathHelper.cos(rotation) / 2.0F;
					f17 = MathHelper.sin(rotation) / 2.0F;
					double minU = texture.getIconUMin();
					double maxU = texture.getIconUMax();
					d4 = texture.getIconVMin();
					d5 = texture.getIconVMax();
					tessellator.setColorOpaque_F(1.0F * blockBrightness * r, 1.0F * blockBrightness * g, 1.0F * blockBrightness * b);
					tessellator.addVertexWithUV((double)x, (double)((float)y + f7), (double)z, (minU - centerU) * (double)f17 - (d5 - centerV) * (double)f15 + centerU, (d5 - centerV) * (double)f17 + (minU - centerU) * (double)f15 + centerV);
					tessellator.addVertexWithUV((double)x, (double)((float)y + f8), (double)(z + 1), (maxU - centerU) * (double)f17 - (d5 - centerV) * (double)f15 + centerU, (d5 - centerV) * (double)f17 + (maxU - centerU) * (double)f15 + centerV);
					tessellator.addVertexWithUV((double)(x + 1), (double)((float)y + f9), (double)(z + 1), (maxU - centerU) * (double)f17 - (d4 - centerV) * (double)f15 + centerU, (d4 - centerV) * (double)f17 + (maxU - centerU) * (double)f15 + centerV);
					tessellator.addVertexWithUV((double)(x + 1), (double)((float)y + f10), (double)z, (minU - centerU) * (double)f17 - (d4 - centerV) * (double)f15 + centerU, (d4 - centerV) * (double)f17 + (minU - centerU) * (double)f15 + centerV);
				} else {
					tessellator.setColorOpaque_F(1.0F * blockBrightness * r, 1.0F * blockBrightness * g, 1.0F * blockBrightness * b);
					tessellator.addVertexWithUV((double)x, (double)((float)y + f7), (double)z, texture.getIconUMin(), texture.getIconVMax());
					tessellator.addVertexWithUV((double)x, (double)((float)y + f8), (double)(z + 1), texture.getIconUMax(), texture.getIconVMax());
					tessellator.addVertexWithUV((double)(x + 1), (double)((float)y + f9), (double)(z + 1), texture.getIconUMax(), texture.getIconVMin());
					tessellator.addVertexWithUV((double)(x + 1), (double)((float)y + f10), (double)z, texture.getIconUMin(), texture.getIconVMin());
				}
			}

			if (renderBlocks.renderAllFaces || flag1) {
				float brightness = 1.0F;
				if (LightmapHelper.isLightmapEnabled()) {
					tessellator.setLightmapCoord(this.block.getLightmapCoord(renderBlocks.blockAccess, x, y - 1, z));
				} else {
					brightness = this.getBlockBrightness(renderBlocks.blockAccess, x, y - 1, z);
				}

				tessellator.setColorOpaque_F(0.5F * brightness * r, 0.5F * brightness * g, 0.5F * brightness * b);
				renderBlocks.renderBottomFace(tessellator, this.block, (double)x, (double)((float)y + 0.001F), (double)z, this.getBlockTextureFromSideAndMetadata(Side.TOP, 0));
				didRender = true;
			}

			for(int side = 0; side < 4; ++side) {
				int xB = x;
				zB = z;
				if (side == 0) {
					--zB;
				}

				if (side == 1) {
					++zB;
				}

				if (side == 2) {
					--xB;
				}

				if (side == 3) {
					++xB;
				}

				float uOff;
				if (side != 0 && side != 1) {
					uOff = (z & 1) == 0 ? 0.0F : 0.5F;
				} else {
					uOff = (x & 1) == 0 ? 0.0F : 0.5F;
				}

				IconCoordinate texture = this.getBlockTextureFromSideAndMetadata(Side.getSideById(side + 2), meta);
				if (renderBlocks.renderAllFaces || aflag[side]) {
					float f13;
					float f20;
					float f19;
					float f21;
					if (side == 0) {
						f13 = f7;
						f15 = f10;
						f17 = (float)x + 1.0E-4F;
						f20 = (float)(x + 1) - 1.0E-4F;
						f19 = (float)z + 1.0E-4F;
						f21 = (float)z + 1.0E-4F;
					} else if (side == 1) {
						f13 = f9;
						f15 = f8;
						f17 = (float)(x + 1) - 1.0E-4F;
						f20 = (float)x + 1.0E-4F;
						f19 = (float)(z + 1) - 1.0E-4F;
						f21 = (float)(z + 1) - 0.001F;
					} else if (side == 2) {
						f13 = f8;
						f15 = f7;
						f17 = (float)x + 1.0E-4F;
						f20 = (float)x + 1.0E-4F;
						f19 = (float)(z + 1) - 1.0E-4F;
						f21 = (float)z + 1.0E-4F;
					} else {
						f13 = f10;
						f15 = f9;
						f17 = (float)(x + 1) - 1.0E-4F;
						f20 = (float)(x + 1) - 1.0E-4F;
						f19 = (float)z + 1.0E-4F;
						f21 = (float)(z + 1) - 1.0E-4F;
					}

					didRender = true;
					float vOff = (y & 1) == 0 ? 0.0F : 0.5F;
					d4 = texture.getSubIconU((double)(0.0F + uOff));
					d5 = texture.getSubIconU((double)(0.5F + uOff));
					double d6 = texture.getSubIconV((double)(0.5F - f13 / 2.0F + vOff));
					double d7 = texture.getSubIconV((double)(0.5F - f15 / 2.0F + vOff));
					double d8 = texture.getSubIconV((double)(0.5F + vOff));
					float brightness = 1.0F;
					if (LightmapHelper.isLightmapEnabled()) {
						tessellator.setLightmapCoord(this.block.getLightmapCoord(renderBlocks.blockAccess, xB, y, zB));
					} else {
						brightness = this.getBlockBrightness(renderBlocks.blockAccess, xB, y, zB);
					}

					if (side < 2) {
						brightness *= 0.8F;
					} else {
						brightness *= 0.6F;
					}

					tessellator.setColorOpaque_F(1.0F * brightness * r, 1.0F * brightness * g, 1.0F * brightness * b);
					tessellator.addVertexWithUV((double)f17, (double)((float)y + f13), (double)f19, d4, d6);
					tessellator.addVertexWithUV((double)f20, (double)((float)y + f15), (double)f21, d5, d7);
					tessellator.addVertexWithUV((double)f20, (double)y, (double)f21, d5, d8);
					tessellator.addVertexWithUV((double)f17, (double)y, (double)f19, d4, d8);
				}
			}

			this.block.minY = d;
			this.block.maxY = d1;
			return didRender;
		}
	}

	public boolean shouldItemRender3d() {
		return false;
	}

	public boolean shouldSideBeRendered(WorldSource blockAccess, int x, int y, int z, int side) {
		Material material = blockAccess.getBlockMaterial(x, y, z);
		if (material == this.block.blockMaterial) {
			return false;
		} else if (material == Material.ice) {
			return true;
		} else {
			return side == Side.TOP.getId() ? true : super.shouldSideBeRendered(blockAccess, x, y, z, side);
		}
	}
}
