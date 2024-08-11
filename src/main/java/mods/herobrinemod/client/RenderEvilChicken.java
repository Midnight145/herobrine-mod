package mods.herobrinemod.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderEvilChicken extends RenderLiving {
	private static final ResourceLocation texture = new ResourceLocation("herobrinemod",
			"textures/mob/angrychicken.png");

	public RenderEvilChicken(ModelBase par1ModelBase, float par2) { super(par1ModelBase, par2); }

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) { return texture; }

}
