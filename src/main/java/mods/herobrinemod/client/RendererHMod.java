package mods.herobrinemod.client;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RendererHMod extends RenderBiped {
	private final ResourceLocation texture;

	public float scale;

	public RendererHMod(ModelBiped model, ResourceLocation texture, float scale) {
		super(model, 0.2f);
		this.scale = scale;
		this.texture = texture;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		// TODO Auto-generated method stub
		return this.texture;
	}

}
