package mods.HerobrineMod.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockJukebox;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;


public class ItemHDisc extends ItemRecord
{
	  public String rname;
	  public String screename;
	  protected ItemHDisc(String name, String screenname)
	  {
	    super(name);

	    this.screename = screenname;
	    this.rname = name;


	  }
	  public void SetScreenName(String screename_)
	  {
		  this.screename = screename_;
	  }
	  
	  @SideOnly(Side.CLIENT)
	  public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	  {
	    par3List.add(func_150927_i());
	  }
	  
	  @SideOnly(Side.CLIENT)
	  public String func_150927_i()
	  {
		
	    return this.screename;
	  }
	  
	  public ResourceLocation getRecordResource(String name)
	  {
	    return new ResourceLocation("herobrinemod:" + rname);
	  }
	
}