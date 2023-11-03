package mods.HerobrineMod.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.HerobrineMod.common.mobs.EntityHVillager;
import net.minecraft.client.renderer.entity.RenderVillager;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderHVillager extends RenderVillager {

	private static final ResourceLocation texture = new ResourceLocation("herobrinemod", "textures/mob/hvillager.png");

	public RenderHVillager() {}

	protected ResourceLocation func_110902_a(EntityHVillager par1EntityVillager) { return texture; }
}
