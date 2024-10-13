package glp.fishybusiness.mixin;

import glp.fishybusiness.entities.EntityFlameSpider;
import net.minecraft.core.entity.SpawnListEntry;
import net.minecraft.core.entity.monster.EntityGhast;
import net.minecraft.core.entity.monster.EntityPigZombie;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.biome.BiomeNether;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = BiomeNether.class, remap = false)
public abstract class BiomeNetherMixin extends Biome {

	public BiomeNetherMixin(String key) {
		super(key);
	}

	@Inject(method = "<init>", at = @At("TAIL"))
	private void addMobs(CallbackInfo ci) {
		this.spawnableMonsterList.add(new SpawnListEntry(EntityFlameSpider.class, 10));
	}
}
