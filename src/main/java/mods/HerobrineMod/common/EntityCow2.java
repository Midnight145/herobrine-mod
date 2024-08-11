

package mods.HerobrineMod.common;


import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

// Referenced classes of package net.minecraft.src:
//            EntityAnimal, Item, EntityPlayer, InventoryPlayer, 
//            ItemStack, World, NBTTagCompound

public class EntityCow2 extends EntityAnimal
{

    public EntityCow2(World world, double x, double y, double z)
    {
        super(world);
        
        if(HerobrineMod.mini_mode==1)	
            setSize(0.45F, 0.65F);
        else
        	setSize(0.9F, 1.3F);
        
        cowbrine=false;
        if(HerobrineMod.cowBrine==1 && world.difficultySetting != world.difficultySetting.EASY)
        {
        	
        		cowbrine=true;
        
        }else{
        	setDead();
        }
		setPosition(x, y+ (double) yOffset, z);

		this.heal(5.0f);
    }
    
 
    public EntityCow2(World world)
    {
        super(world);
        setSize(0.9F, 1.3F);
        if(HerobrineMod.mini_mode==1)	
            setSize(0.45F, 0.65F);
        else
        	setSize(0.9F, 1.3F);
        
        cowbrine=false;

        if(HerobrineMod.cowBrine==1 &&  world.difficultySetting != world.difficultySetting.EASY)
        {
        		cowbrine=true;
        
        }else{
        	setDead();
        }
    }
    
    public void onLivingUpdate()
    {
    	super.onLivingUpdate();
    	EntityPlayer player = worldObj.getClosestPlayerToEntity(this, 15D);
    	if(player != null)
    		this.faceEntity(player, 30f, 30f);
    }
    public void setDead()
    {
    	isDead=true;
    	if (!worldObj.isRemote)
			return;
		
    	if(cowbrine && HerobrineMod.placeLava==1)
    	{
  
    		worldObj.setBlock((int)this.posX, (int)this.posY,(int) this.posZ, Blocks.lava,0,0);
    		worldObj.setBlock((int)this.posX, (int)this.posY+1,(int) this.posZ, Blocks.lava,0,0);

    	}
    		
        isDead = true;
    }

  

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
    }

    protected String getLivingSound()
    {
        return "mob.cow";
    }

    protected String getHurtSound()
    {
        return "mob.cowhurt";
    }

    protected String getDeathSound()
    {
        return "mob.cowhurt";
    }

    protected float getSoundVolume()
    {
        return 0.4F;
    }

  
    protected void dropFewItems(boolean flag, int i)
    {
       
    }

 


    public boolean cowbrine=false;


	  public EntityCow2 spawnBabyAnimal(EntityAgeable par1EntityAgeable)
	    {
	        return null;
	    }

	    /**
	     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
	     * the animal type)
	     */
	    public boolean isBreedingItem(ItemStack par1ItemStack)
	    {
	        return false;
	    }

	    public EntityAgeable createChild(EntityAgeable par1EntityAgeable)
	    {
	        return null;
	    }
	


}
