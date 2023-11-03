package mods.HerobrineMod.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

//////////////////////////////////////////////////////////////////////
//Herobrine V.1.0
//
//2011 By Burnner
//////////////////////////////////////////////////////////////////////

public class BlockHerobrine extends Block {

	private IIcon side1;
	private IIcon side2;
	private IIcon side3;

	public BlockHerobrine(Material rock) { super(Material.rock); }

	// Show Herobrine texture only from the side;
	@Override
	public IIcon getIcon(int i, int j) {
		if (i == 2 || i == 3 || i == 4 | i == 5) {
			if (j == 0) { return this.side1; }
			return this.side2;
		}
		return this.side3;
	}

	// Block updates
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void onBlockDestroyedByPlayer(World world, int i, int j, int k, int l) {
		HerobrineMod.canSpawn = false;
		if (world.isRemote) { return; }
		MinecraftServer.getServer().getConfigurationManager()
				.sendChatMsg(new ChatComponentText("You wll never be free...."));

	}

	public void onBlockDestroyedByExplosion(World world, int i, int j, int k) {
		this.onBlockDestroyedByPlayer(world, i, j, k, 0);
	}

	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister) {

		this.side1 = par1IconRegister.registerIcon("herobrinemod:blockskin1"); // bottom
		this.side2 = par1IconRegister.registerIcon("herobrinemod:blockskin2"); //
		this.side3 = par1IconRegister.registerIcon("herobrinemod:hellrock");
		this.blockIcon = par1IconRegister.registerIcon("herobrinemod:hellrock");
	}

	@Override
	public TileEntity createTileEntity(World world, int meta) {
		return new TileEntityTotem();

	}

	@Override
	public boolean hasTileEntity(int meta) { return true; }
}
