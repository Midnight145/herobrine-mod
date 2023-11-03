// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

package mods.HerobrineMod.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

// Referenced classes of package net.minecraft.src:
//            RenderLiving, EntityCow, ModelBase, EntityLiving,
//            Entity

public class RenderCow2 extends RenderLiving {
	private static final ResourceLocation texture = new ResourceLocation("herobrinemod", "textures/mob/cowbrine.png");

	public RenderCow2(ModelBase par1ModelBase, float par2) { super(par1ModelBase, par2); }

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless
	 * you call Render.bindEntityTexture.
	 */
	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) { return texture; }
}
