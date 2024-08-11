package mods.herobrinemod.client;

import net.minecraft.client.renderer.entity.RenderVillager;
import net.minecraft.util.ResourceLocation;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.herobrinemod.common.mobs.EntityEvilVillager;

@SideOnly(Side.CLIENT)
public class RenderEvilVillager extends RenderVillager {

    private static final ResourceLocation texture = new ResourceLocation("herobrinemod", "textures/mob/hvillager.png");

    public RenderEvilVillager() {}

    protected ResourceLocation func_110902_a(EntityEvilVillager par1EntityVillager) {
        return texture;
    }
}
