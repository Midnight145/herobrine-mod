package mods.HerobrineMod.common;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

//////////////////////////////////////////////////////////////////////
//Herobrine V.1.0
//
//2011 By Burnner
//////////////////////////////////////////////////////////////////////

public class BlockHerobrine extends Block {
	
    private IIcon side1;
    private IIcon side2;
    private IIcon side3;
    
    public BlockHerobrine(Material rock)
    {
        super(Material.rock);
        
        texuse=0;//show white eyes
        super.setTickRandomly(getTickRandomly());
  
    }
    
    
   

	//Show Herobrinetexture only from the side;
    public IIcon getIcon(int i, int j)
    {
    	if(j==0)
    	{
    		if(i==2||i==3||i==4|i==5)
    			return side1;
    	}else{
    		if(i==2||i==3||i==4|i==5)
    			return side2;
    	}
    return side3;
    }
    
//Block updates
 //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void onBlockAdded(World world, int i, int j, int k)
    {
        world.scheduleBlockUpdate(i, j, k, this, tickRate());
    }

    public void onNeighborBlockChange(World world, int i, int j, int k, int l)
    {
        world.scheduleBlockUpdate(i, j, k, this, tickRate());
    }

    public void onBlockDestroyedByPlayer(World world, int i, int j, int k, int l)
    {
    	HerobrineMod.canSpawn=false;

        MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatComponentText("You wll never be free...."));

 
    }

    public void onBlockRemoval(World world, int i, int j, int k)
    {
    	 world.scheduleBlockUpdate(i, j, k, this, tickRate());
    	
    }
    public void onBlockDestroyedByExplosion(World world, int i, int j, int k)
    {
    	HerobrineMod.canSpawn=false;
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void randomDisplayTick(World world, int i, int j, int k, Random random)
    {
    	if(world.isRemote)
    	this.updateTick(world, i, j, k, random);
    }
    public void updateTick(World world, int i, int j, int k, Random random)
    {
    
    	//is a valid totem?
    	if(world.getBlock(i, j+2, k) == Blocks.fire)//Block is fire
    	{
    	if(world.getBlock(i, j+1, k) == Blocks.netherrack)//Block is neatherrack
    	{
    		if(world.getBlock(i, j-1, k)== Blocks.gold_block)//block is gold
    		{
    			if(world.getBlock(i, j-2, k)== Blocks.gold_block)//block is gold
        		{
    				
    		        if(!HerobrineMod.canSpawn || texuse==0)
    		        {
    		    		int me = world.getBlockMetadata(i, j, k);
    		        	texuse=1;//Switch texture
    		        	
    		        	
    		        	HerobrineMod.canSpawn=true;
    		        	//Switch Metadate
    		        	me=1;   
    		        	world.setBlockMetadataWithNotify(i, j, k, me,3);
    		        	world.notifyBlocksOfNeighborChange(i, j, k, this);
    		        	world.notifyBlocksOfNeighborChange(i, j - 1, k, this);
    		        	//world.markBlocksDirty(i, j, k, i, j, k);
    		            world.scheduleBlockUpdate(i, j, k, this, 10);
	     
    		            if(!world.isRemote)
        	            MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatComponentText("You don't know what you did...."));

        		        EntityLightningBolt entity2 = new EntityLightningBolt(world, i,j+2,k);
        		       
        		        
        		        world.spawnEntityInWorld(entity2);
        		        world.playSoundEffect(entity2.posX, entity2.posY, entity2.posZ, "ambient.weather.thunder", 10000.0F, 0.8F + 50 * 0.2F);
        	            world.playSoundEffect(entity2.posX, entity2.posY, entity2.posZ, "random.explode", 2.0F, 0.5F + 100 * 0.2F);
        		
        	            //world.playSound(entity.posX, entity.posY, entity.posZ, "random.explode", 2.0F, 0.5F + world.rand.nextFloat() * 0.2F, true);
        	            //world.playSound(entity.posX, entity.posY, entity.posZ, "ambient.weather.thunder", 10000.0F, 0.8F + world.rand.nextFloat() * 0.2F,true);
    		            			        
		
    		       
    		        }
    		     //False unset the state
        		}else{ this.unset(world, i, j, k);}
    		}else{ this.unset(world, i, j, k);}
    		
    	}else{ this.unset(world, i, j, k);}
    	}else{ this.unset(world, i, j, k);}
    	
    }
    
  
    
    //Deactivate Herobrine
    public void unset(World world, int i, int j, int k)
    {
    	if(HerobrineMod.canSpawn || texuse==1)
        {
        	int meta = world.getBlockMetadata(i, j, k);
        	texuse=0;//chance texture
        	meta=0;
        	HerobrineMod.canSpawn=false;//Can not spawn anymore
        	//Reset metainfo
        	world.setBlockMetadataWithNotify(i, j, k, meta,3);
        	world.notifyBlocksOfNeighborChange(i, j, k, this);
        	world.notifyBlocksOfNeighborChange(i, j - 1, k, this);
        	//world.markBlocksDirty(i, j, k, i, j, k);
         	world.scheduleBlockUpdate(i, j, k, this, 10);
        }
    }
    
    
    public int tickRate()
    {
        return 2;
    }
    
    
    //Load textures
    /*
    public void setTextures(){
    	tex = new int[2];
    
    	tex[0] = ModLoader. "/BlockSkin1.png";
        tex[1] = ModLoader.addOverride("/terrain.png", "/BlockSkin2.png");
    
    }*/
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
    	 
 
    	 side1= par1IconRegister.registerIcon("herobrinemod:blockskin1"); // bottom
         side2 = par1IconRegister.registerIcon("herobrinemod:blockskin2"); // 
         side3 = par1IconRegister.registerIcon("herobrinemod:hellrock");
         this.blockIcon = par1IconRegister.registerIcon("herobrinemod:hellrock");
    }
    

	private static int tex[];//texturearray
	private static int texuse;//Which texture is in use?
}
