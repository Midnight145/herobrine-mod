package mods.HerobrineMod.client;

import mods.HerobrineMod.common.mobs.EntityPig2;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderPig;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderPig2 extends RenderPig {
	private static final ResourceLocation texture = new ResourceLocation("herobrinemod", "textures/mob/pigbrine.png");
	
	public RenderPig2(ModelBase par1ModelBase, ModelBase par2ModelBase, float par3) {
		super(par1ModelBase, par2ModelBase, par3);
		this.setRenderPassModel(par2ModelBase);
	}

	protected int renderSaddledPig(EntityPig2 par1EntityPig, int par2, float par3) { return -1; }
	
	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) { return texture; }
}
