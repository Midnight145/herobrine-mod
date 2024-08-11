package mods.HerobrineMod.common;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode



import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

// Referenced classes of package net.minecraft.src:
//            EntityAnimal, World, Item, NBTTagCompound
public class EntityChicken2 extends EntitySpider
{

	
    public float field_70886_e;
    public float destPos1;
    public float field_70884_g;
    public float field_70888_h;
    public float field_70889_i = 1.0F;
    
    protected void func_110147_ax()
    {
    	 super.applyEntityAttributes();
         this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(0.5D);
         this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.800000011920929D);
       
    }
    
    
    protected Item getDropItemId()
    {
    	return Items.rotten_flesh;
    }
    
    public EntityChicken2(World world)
    {
        super(world);
        field_753_a = false;
        field_752_b = 0.0F;
        destPos1 = 0.0F;
        field_755_h = 1.0F;
  
       
        if(HerobrineMod.mini_mode==1)	
        setSize(0.15F, 0.35F);
        else
        	 setSize(0.3F, 0.7F);
      
    }


    public EntityChicken2(World worldObj, double d, double e, double f)
    {
        super(worldObj);
        field_753_a = false;
        field_752_b = 0.0F;
        destPos1 = 0.0F;
        field_755_h = 1.0F;
       
        if(HerobrineMod.mini_mode==1)	
        	setSize(0.15F, 0.35F);
        else
        	 setSize(0.3F, 0.7F);
        	
        timeUntilNextEgg = rand.nextInt(6000) + 6000;
        setPosition(d, e + (double)yOffset, f);
    
    }
    
    protected Entity findPlayerToAttack()
    {

        EntityPlayer entityplayer = worldObj.getClosestPlayerToEntity(this, 16D);

    
        if (entityplayer == null)
        {
            return null;
        }

        if (entityplayer != null)
        {
           if(entityplayer.getHealth() < 5.0f)
        	   if((int)(Math.random() * 10 + 1)==2)
        		   this.setDead();
        	
            return entityplayer;
        }
        return null;
    }

    
    protected void attackEntity(Entity par1Entity, float par2)
    {


        if (this.rand.nextInt(100) == 0)
        {
            this.entityToAttack = null;
        }
        else
        {
            if (par2 > 2.0F && par2 < 6.0F && this.rand.nextInt(10) == 0)
            {
                if (this.onGround)
                {
                    double d0 = par1Entity.posX - this.posX;
                    double d1 = par1Entity.posZ - this.posZ;
                    float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
                    this.motionX = d0 / (double)f2 * 0.5D * 0.800000011920929D + this.motionX * 0.20000000298023224D;
                    this.motionZ = d1 / (double)f2 * 0.5D * 0.800000011920929D + this.motionZ * 0.20000000298023224D;
                    this.motionY = 0.4000000059604645D;
                }
            }
            else
            {
                super.attackEntity(par1Entity, par2);
              
            }
        }
    }

    public int func_82193_c(Entity par1Entity) {
    	return 2;
    	}

    @Override
    public void onLivingUpdate()
    {
    
         this.field_70888_h = this.field_70886_e;
         this.field_70884_g = this.destPos;
         this.destPos = (float)((double)this.destPos + (double)(this.onGround ? -1 : 4) * 0.3D);

         if (this.destPos < 0.0F)
         {
             this.destPos = 0.0F;
         }

         if (this.destPos > 1.0F)
         {
             this.destPos = 1.0F;
         }

         if (!this.onGround && this.field_70889_i < 1.0F)
         {
             this.field_70889_i = 1.0F;
         }

         this.field_70889_i = (float)((double)this.field_70889_i * 0.9D);

         if (!this.onGround && this.motionY < 0.0D)
         {
             this.motionY *= 0.6D;
         }

         this.field_70886_e += this.field_70889_i * 2.0F;
         
      

         super.onLivingUpdate();
      
    }
    
    


    protected void fall(float f)
    {
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
        return "mob.chicken.say";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return "mob.chicken.hurt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "mob.chicken.hurt";
    }

    /**
     * Plays step sound at given x, y, z for the entity
     */
    protected void playStepSound(int par1, int par2, int par3, int par4)
    {
        this.playSound("mob.chicken.step", 0.15F, 1.0F);
    }

    

	  public EntityChicken spawnBabyAnimal(EntityAgeable par1EntityAgeable)
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
	
	    
    public boolean field_753_a;
    public float field_752_b;
    public float destPos;
    public float field_757_d;
    public float field_756_e;
    public float field_755_h;
    public int timeUntilNextEgg;
}