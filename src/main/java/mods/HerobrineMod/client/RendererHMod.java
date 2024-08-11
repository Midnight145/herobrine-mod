package mods.HerobrineMod.client;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class RendererHMod extends RenderBiped
{
	private ResourceLocation texture;

	public float scale;

	public RendererHMod(ModelBiped model, ResourceLocation texture, float scale)
	{
		super(model, 0.2f);
		this.scale = scale;
		this.texture = texture;
	}

	public RendererHMod(ModelBiped model, float scale)
	{
		this(model, null, 0.2f);
	}

	public void renderFamiliar(EntityLiving entity, double d, double d1, double d2, float f, float f1)
	{
		super.doRender(entity, d, d1, d2, f, f1);
	}

	@Override
	public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
	{
		renderFamiliar((EntityLiving)entity, d, d1, d2, f, f1);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f)
	{
		GL11.glScalef(scale, scale, scale);
	}

	protected void doRenderInGui(EntityLiving familiar, double d, double d1, double d2, float f, float f1)
	{
		doRender(familiar, d, d1, d2, f, f1);
	}
	

	
	protected ResourceLocation getEntityTexture(Entity entity) {
		// TODO Auto-generated method stub
		return texture;
	}

}
