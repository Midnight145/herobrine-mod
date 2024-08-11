package mods.HerobrineMod.common;

import java.util.Calendar;
import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;




public class EntityHVillager extends EntityBaseHMod
{
	
	private int ticker = 0;
	private int lifespawn=0;
	
	  public void onUpdate()
	    {
	        super.onUpdate();
	        ticker ++;
	        if(lifespawn < ticker)
	        	this.setDead();
	    }

	 public EntityHVillager(World world, double x, double y, double z)
	    {
	        super(world);
     
	        lifespawn = 700+((int) (Math.random() * 500 + 1));
	        setPosition(x, y + (double) yOffset, z);
	    }
	 
	 public EntityHVillager(World world)
	    {
	        super(world);
	        lifespawn =700+((int) (Math.random() * 500 + 1));
	    }
	 
	   protected Entity findPlayerToAttack()
	    {
	

	        EntityPlayer entityplayer = worldObj.getClosestPlayerToEntity(this, 30D);

	        if (entityplayer == null)
	        {
	            return null;
	        }

	        if (entityplayer != null)
	        {
	            return entityplayer;
	        }
	        else
	        {
	            return null;
	        }
	    }

}