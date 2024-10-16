package glp.fishybusiness;

import glp.fishybusiness.items.ModItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import turniplabs.halplibe.helper.RecipeBuilder;
import turniplabs.halplibe.helper.recipeBuilders.RecipeBuilderShaped;
import turniplabs.halplibe.helper.recipeBuilders.modifiers.WorkbenchModifier;
import turniplabs.halplibe.util.RecipeEntrypoint;

import static glp.fishybusiness.FishyBusiness.MOD_ID;

public class ModRecipes implements RecipeEntrypoint {
	public void initializeRecipes() {
		RecipeBuilder.ModifyWorkbench("minecraft").removeRecipe("fishing_rod");
		RecipeBuilder.Shaped(MOD_ID, "  W", " WS", "W I")
			.addInput('W', Item.stick)
			.addInput('S', Item.string)
			.addInput('I', Item.ingotIron)
			.create("fb_fishing_rod", new ItemStack(Item.toolFishingrod, 1));
	}

	@Override
	public void onRecipesReady() {
		initializeRecipes();
	}

	@Override
	public void initNamespaces() {
		RecipeBuilder.initNameSpace(MOD_ID);
		RecipeBuilder.getRecipeNamespace(MOD_ID);
	}
}
