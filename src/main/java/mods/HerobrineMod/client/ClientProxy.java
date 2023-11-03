package mods.HerobrineMod.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import mods.HerobrineMod.common.CommonProxy;
import mods.HerobrineMod.common.HerobrineMod;
import mods.HerobrineMod.common.mobs.EntityChicken2;
import mods.HerobrineMod.common.mobs.EntityCow2;
import mods.HerobrineMod.common.mobs.EntityHVillager;
import mods.HerobrineMod.common.mobs.EntityJetra;
import mods.HerobrineMod.common.mobs.EntityPig2;
import mods.HerobrineMod.common.mobs.EntityZombie2;
import mods.HerobrineMod.common.mobs.herobrine.EntityBuilder;
import mods.HerobrineMod.common.mobs.herobrine.EntityDummySpawner;
import mods.HerobrineMod.common.mobs.herobrine.EntityFighter;
import mods.HerobrineMod.common.mobs.herobrine.EntityFighter2;
import mods.HerobrineMod.common.mobs.herobrine.EntityHerobrine;
import mods.HerobrineMod.common.mobs.herobrine.EntityHerobrineBoss;
import mods.HerobrineMod.common.mobs.herobrine.EntityStalker;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerRenderers() {
		
		if ((int) (Math.random() * 60 + 1) == 10) {
			HerobrineMod.mini_mode = 1;
		}
		
		if ((int) (Math.random() * 80 + 1) == 10) {
			HerobrineMod.canSpawn = true;
		}
		
		if ((int) (Math.random() * 110 + 1) == 10) {
			System.out.println("Rick roll time!");
			
		}
		if (HerobrineMod.mini_mode == 0) {
			
			RenderingRegistry.registerEntityRenderingHandler(EntityHVillager.class, new RenderHVillager());
			RenderingRegistry.registerEntityRenderingHandler(EntityChicken2.class,
					new RenderChicken2(new ModelChicken(), 0.3F));
			RenderingRegistry.registerEntityRenderingHandler(EntityCow2.class, new RenderCow2(new ModelCow(), 0.5F));
			RenderingRegistry.registerEntityRenderingHandler(EntityPig2.class,
					new RenderPig2(new ModelPig(), new ModelPig(), 0.5F));
			
			RenderingRegistry.registerEntityRenderingHandler(EntityDummySpawner.class, new RendererHMod(
					new ModelBiped(), new ResourceLocation("herobrinemod", "textures/mob/sucker.png"), 1.0F));
			RenderingRegistry.registerEntityRenderingHandler(EntityFighter.class, new RendererHMod(new ModelBiped(),
					new ResourceLocation("herobrinemod", "textures/mob/sucker.png"), 1.0F));
			RenderingRegistry.registerEntityRenderingHandler(EntityFighter2.class, new RendererHMod(new ModelBiped(),
					new ResourceLocation("herobrinemod", "textures/mob/notch.png"), 1.0F));
			RenderingRegistry.registerEntityRenderingHandler(EntityHerobrine.class, new RendererHMod(new ModelBiped(),
					new ResourceLocation("herobrinemod", "textures/mob/sucker.png"), 1.0F));
			RenderingRegistry.registerEntityRenderingHandler(EntityStalker.class, new RendererHMod(new ModelBiped(),
					new ResourceLocation("herobrinemod", "textures/mob/sucker.png"), 1.0F));
			RenderingRegistry.registerEntityRenderingHandler(EntityBuilder.class, new RendererHMod(new ModelBiped(),
					new ResourceLocation("herobrinemod", "textures/mob/sucker.png"), 1.0F));
			RenderingRegistry.registerEntityRenderingHandler(EntityZombie2.class, new RendererHMod(new ModelZombie(),
					new ResourceLocation("herobrinemod", "textures/mob/zombie.png"), 1.0F));
			RenderingRegistry.registerEntityRenderingHandler(EntityHerobrineBoss.class, new RendererHMod(
					new ModelBiped(), new ResourceLocation("herobrinemod", "textures/mob/sucker.png"), 1.0F));
			
		}
		else {
			
			RenderingRegistry.registerEntityRenderingHandler(EntityChicken2.class,
					new RenderChicken2(new ModelChicken(), 0.15F));
			RenderingRegistry.registerEntityRenderingHandler(EntityCow2.class, new RenderCow2(new ModelCow(), 0.25F));
			RenderingRegistry.registerEntityRenderingHandler(EntityPig2.class,
					new RenderPig2(new ModelPig(), new ModelPig(), 0.25F));
			RenderingRegistry.registerEntityRenderingHandler(EntityHerobrineBoss.class, new RendererHMod(
					new ModelBiped(), new ResourceLocation("herobrinemod", "textures/mob/sucker.png"), 0.5F));
			RenderingRegistry.registerEntityRenderingHandler(EntityDummySpawner.class, new RendererHMod(
					new ModelBiped(), new ResourceLocation("herobrinemod", "textures/mob/sucker.png"), 0.5F));
			RenderingRegistry.registerEntityRenderingHandler(EntityFighter.class, new RendererHMod(new ModelBiped(),
					new ResourceLocation("herobrinemod", "textures/mob/sucker.png"), 0.5F));
			RenderingRegistry.registerEntityRenderingHandler(EntityFighter2.class, new RendererHMod(new ModelBiped(),
					new ResourceLocation("herobrinemod", "textures/mob/notch.png"), 0.5F));
			RenderingRegistry.registerEntityRenderingHandler(EntityHerobrine.class, new RendererHMod(new ModelBiped(),
					new ResourceLocation("herobrinemod", "textures/mob/sucker.png"), 0.5F));
			RenderingRegistry.registerEntityRenderingHandler(EntityStalker.class, new RendererHMod(new ModelBiped(),
					new ResourceLocation("herobrinemod", "textures/mob/sucker.png"), 0.5F));
			RenderingRegistry.registerEntityRenderingHandler(EntityBuilder.class, new RendererHMod(new ModelBiped(),
					new ResourceLocation("herobrinemod", "textures/mob/sucker.png"), 0.5F));
			RenderingRegistry.registerEntityRenderingHandler(EntityZombie2.class, new RendererHMod(new ModelZombie(),
					new ResourceLocation("herobrinemod", "textures/mob/zombie.png"), 0.5F));
		}
		
		RenderingRegistry.registerEntityRenderingHandler(EntityJetra.class, new RendererHMod(new ModelBiped(),
				new ResourceLocation("herobrinemod", "textures/mob/jetra.png"), 1.0F));
		
	}
	
	@Override
	public void load() { FMLCommonHandler.instance().bus().register(new ClientTickHandler()); }
	
	@Override
	public void preInit() {
		// MinecraftForge.EVENT_BUS.register(new SoundLoader());
		MinecraftForge.EVENT_BUS.register(this);
	}
	
}
