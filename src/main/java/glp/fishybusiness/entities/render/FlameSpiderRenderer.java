package glp.fishybusiness.entities.render;

import glp.fishybusiness.entities.EntityFlameSpider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.LightmapHelper;
import net.minecraft.client.render.entity.LivingRenderer;
import net.minecraft.client.render.model.ModelSpider;
import net.minecraft.core.entity.monster.EntitySpider;
import org.lwjgl.opengl.GL11;

public class FlameSpiderRenderer extends LivingRenderer<EntityFlameSpider> {
	public FlameSpiderRenderer() {
		super(new ModelSpider(0.0F), 1.0F);
		this.setRenderPassModel(new ModelSpider(0.01F));
	}

	protected float setSpiderDeathMaxRotation(EntityFlameSpider spider) {
		return 180.0F;
	}

	protected boolean setSpiderEyeBrightness(EntityFlameSpider spider, int renderPass, float partialTick) {
		if (renderPass == 0) {
			boolean useVariants = (Boolean) Minecraft.getMinecraft(this).gameSettings.mobVariants.value;
			this.loadTexture("/assets/fishybusiness/textures/entity/flame_spider/eyes/" + 0 + ".png");
			float brightness = spider.getBrightness(1.0F);
			if (LightmapHelper.isLightmapEnabled()) {
				LightmapHelper.setLightmapCoord(LightmapHelper.getLightmapCoord(15, 15));
			}

			float f1 = (1.0F - brightness) * 0.5F;
			GL11.glEnable(3042);
			GL11.glDisable(3008);
			GL11.glBlendFunc(770, 771);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, f1);
			return true;
		} else {
			return false;
		}
	}

	protected float getDeathMaxRotation(EntityFlameSpider entity) {
		return this.setSpiderDeathMaxRotation(entity);
	}

	protected boolean shouldRenderPass(EntityFlameSpider entity, int renderPass, float partialTick) {
		return this.setSpiderEyeBrightness(entity, renderPass, partialTick);
	}
}
