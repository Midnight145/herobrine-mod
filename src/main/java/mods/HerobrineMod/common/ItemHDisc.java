package mods.HerobrineMod.common;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ItemHDisc extends ItemRecord {
	public String rname;
	public String screename;
	
	protected ItemHDisc(String name, String screenname) {
		super(name);
		
		this.screename = screenname;
		this.rname = name;
		
	}
	
	public void SetScreenName(String screename_) { this.screename = screename_; }
	
	@SideOnly(Side.CLIENT)
	public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		par3List.add(func_150927_i());
	}
	
	@SideOnly(Side.CLIENT)
	public String func_150927_i() {
		
		return this.screename;
	}
	
	public ResourceLocation getRecordResource(String name) { return new ResourceLocation("herobrinemod:" + rname); }
	
}
