package mods.herobrinemod.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderEvilPig extends RenderLiving {

    private static final ResourceLocation texture = new ResourceLocation("herobrinemod", "textures/mob/pigbrine.png");

    public RenderEvilPig(ModelBase par1ModelBase, float par3) {
        super(par1ModelBase, par3);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity) {
        return texture;
    }
}
