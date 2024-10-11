package glp.fishybusiness.entities;

import glp.fishybusiness.entities.EntityDaggerfish;
import glp.fishybusiness.items.ModItems;
import net.minecraft.client.render.entity.SnowballRenderer;
import turniplabs.halplibe.helper.EntityHelper;

public class ModEntities {
	public void initializeEntities() {
		EntityHelper.createEntity(EntityDaggerfish.class, 550, "Daggerfish", () -> new SnowballRenderer(ModItems.daggerfish));
	}
}
