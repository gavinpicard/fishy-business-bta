package glp.fishybusiness;

import glp.fishybusiness.blocks.ModBlocks;
import glp.fishybusiness.items.ModItems;
import glp.fishybusiness.entities.ModEntities;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;


public class FishyBusiness implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
    public static final String MOD_ID = "fishybusiness";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    @Override
    public void onInitialize() {
		LOGGER.info("FishyBusiness initialized.");
		new ModItems().initializeItems();
		new ModEntities().initializeEntities();
		new ModBlocks().initializeBlocks();
	}

	@Override
	public void beforeGameStart() {
	}

	@Override
	public void afterGameStart() {

	}

	@Override
	public void onRecipesReady() {

	}

	@Override
	public void initNamespaces() {

	}
}
