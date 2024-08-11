package mods.herobrinemod.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import mods.herobrinemod.common.CommonProxy;
import mods.herobrinemod.common.Config;
import mods.herobrinemod.common.mobs.EntityEvilChicken;
import mods.herobrinemod.common.mobs.EntityEvilCow;
import mods.herobrinemod.common.mobs.EntityEvilPig;
import mods.herobrinemod.common.mobs.EntityEvilVillager;
import mods.herobrinemod.common.mobs.EntityEvilZombie;
import mods.herobrinemod.common.mobs.herobrine.EntityBuilder;
import mods.herobrinemod.common.mobs.herobrine.EntityDummySpawner;
import mods.herobrinemod.common.mobs.herobrine.EntityFighter;
import mods.herobrinemod.common.mobs.herobrine.EntityFighterNotch;
import mods.herobrinemod.common.mobs.herobrine.EntityHerobrine;
import mods.herobrinemod.common.mobs.herobrine.EntityHerobrineBoss;
import mods.herobrinemod.common.mobs.herobrine.EntityStalker;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.util.ResourceLocation;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderers() {
		if (Config.mini_mode) {

			RenderingRegistry.registerEntityRenderingHandler(EntityEvilVillager.class, new RenderEvilVillager());
			RenderingRegistry.registerEntityRenderingHandler(EntityEvilChicken.class,
					new RenderEvilChicken(new ModelChicken(), 0.3F));
			RenderingRegistry.registerEntityRenderingHandler(EntityEvilCow.class,
					new RenderEvilCow(new ModelCow(), 0.5F));
			RenderingRegistry.registerEntityRenderingHandler(EntityEvilPig.class,
					new RenderEvilPig(new ModelPig(), 0.5F));

			RenderingRegistry.registerEntityRenderingHandler(EntityDummySpawner.class, new RendererHMod(
					new ModelBiped(), new ResourceLocation("herobrinemod", "textures/mob/sucker.png"), 1.0F));
			RenderingRegistry.registerEntityRenderingHandler(EntityFighter.class, new RendererHMod(new ModelBiped(),
					new ResourceLocation("herobrinemod", "textures/mob/sucker.png"), 1.0F));
			RenderingRegistry.registerEntityRenderingHandler(EntityFighterNotch.class, new RendererHMod(new ModelBiped(),
					new ResourceLocation("herobrinemod", "textures/mob/notch.png"), 1.0F));
			RenderingRegistry.registerEntityRenderingHandler(EntityHerobrine.class, new RendererHMod(new ModelBiped(),
					new ResourceLocation("herobrinemod", "textures/mob/sucker.png"), 1.0F));
			RenderingRegistry.registerEntityRenderingHandler(EntityStalker.class, new RendererHMod(new ModelBiped(),
					new ResourceLocation("herobrinemod", "textures/mob/sucker.png"), 1.0F));
			RenderingRegistry.registerEntityRenderingHandler(EntityBuilder.class, new RendererHMod(new ModelBiped(),
					new ResourceLocation("herobrinemod", "textures/mob/sucker.png"), 1.0F));
			RenderingRegistry.registerEntityRenderingHandler(EntityEvilZombie.class, new RendererHMod(new ModelZombie(),
					new ResourceLocation("herobrinemod", "textures/mob/zombie.png"), 1.0F));
			RenderingRegistry.registerEntityRenderingHandler(EntityHerobrineBoss.class, new RendererHMod(
					new ModelBiped(), new ResourceLocation("herobrinemod", "textures/mob/sucker.png"), 1.0F));

		}
		else {

			RenderingRegistry.registerEntityRenderingHandler(EntityEvilChicken.class,
					new RenderEvilChicken(new ModelChicken(), 0.15F));
			RenderingRegistry.registerEntityRenderingHandler(EntityEvilCow.class,
					new RenderEvilCow(new ModelCow(), 0.25F));
			RenderingRegistry.registerEntityRenderingHandler(EntityEvilPig.class,
					new RenderEvilPig(new ModelPig(), 0.25F));
			RenderingRegistry.registerEntityRenderingHandler(EntityHerobrineBoss.class, new RendererHMod(
					new ModelBiped(), new ResourceLocation("herobrinemod", "textures/mob/sucker.png"), 0.5F));
			RenderingRegistry.registerEntityRenderingHandler(EntityDummySpawner.class, new RendererHMod(
					new ModelBiped(), new ResourceLocation("herobrinemod", "textures/mob/sucker.png"), 0.5F));
			RenderingRegistry.registerEntityRenderingHandler(EntityFighter.class, new RendererHMod(new ModelBiped(),
					new ResourceLocation("herobrinemod", "textures/mob/sucker.png"), 0.5F));
			RenderingRegistry.registerEntityRenderingHandler(EntityFighterNotch.class, new RendererHMod(new ModelBiped(),
					new ResourceLocation("herobrinemod", "textures/mob/notch.png"), 0.5F));
			RenderingRegistry.registerEntityRenderingHandler(EntityHerobrine.class, new RendererHMod(new ModelBiped(),
					new ResourceLocation("herobrinemod", "textures/mob/sucker.png"), 0.5F));
			RenderingRegistry.registerEntityRenderingHandler(EntityStalker.class, new RendererHMod(new ModelBiped(),
					new ResourceLocation("herobrinemod", "textures/mob/sucker.png"), 0.5F));
			RenderingRegistry.registerEntityRenderingHandler(EntityBuilder.class, new RendererHMod(new ModelBiped(),
					new ResourceLocation("herobrinemod", "textures/mob/sucker.png"), 0.5F));
			RenderingRegistry.registerEntityRenderingHandler(EntityEvilZombie.class, new RendererHMod(new ModelZombie(),
					new ResourceLocation("herobrinemod", "textures/mob/zombie.png"), 0.5F));
		}


	}
}
