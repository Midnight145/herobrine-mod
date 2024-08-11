// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package mods.HerobrineMod.client;

import mods.HerobrineMod.common.EntityPig2;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.util.ResourceLocation;



public class RenderPig2 extends RenderLiving
{
    private static final ResourceLocation pigTextures9 = new ResourceLocation("textures/entity/pig/pig_saddle.png");
    private static final ResourceLocation pigTextures= new ResourceLocation("herobrinemod", "textures/mob/pigbrine.png");
    private static final ResourceLocation saddledPigTextures = new ResourceLocation("textures/entity/pig/pig_saddle.png");


    public RenderPig2(ModelBase par1ModelBase, ModelBase par2ModelBase, float par3)
    {
        super(par1ModelBase, par3);
        this.setRenderPassModel(par2ModelBase);
    }

    protected int renderSaddledPig(EntityPig2 par1EntityPig, int par2, float par3)
    {
        if (par2 == 0 && par1EntityPig.getSaddled())
        {
            this.bindTexture(saddledPigTextures);
            return 1;
        }
        else
        {
            return -1;
        }
    }

    protected ResourceLocation getPigTextures(EntityPig2 par1EntityPig)
    {
        return pigTextures;
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
    {
        return this.renderSaddledPig((EntityPig2)par1EntityLivingBase, par2, par3);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.getPigTextures((EntityPig2)par1Entity);
    }
}

