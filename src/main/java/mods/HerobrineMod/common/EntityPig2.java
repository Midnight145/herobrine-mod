
package mods.HerobrineMod.common;


import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.AchievementList;
import net.minecraft.world.World;


//Referenced classes of package net.minecraft.src:
//         EntityAnimal, DataWatcher, NBTTagCompound, World,
//         EntityPlayer, Item, EntityPigZombie, AchievementList,
//         EntityLightningBolt

public class EntityPig2 extends EntityBaseHMod
{

    public void setDead()
    {
        isDead = true;

        if (worldObj.isRemote)
        {
            return;
        }

        if (pigbrine && HerobrineMod.placeTnt == 1)
        {
     
          EntityTNTPrimed tnt = new EntityTNTPrimed(worldObj);
          worldObj.spawnEntityInWorld(tnt);
          tnt.setPosition(this.posX, this.posY, this.posZ);
        }
    }

    private boolean pigbrine = false;

    public EntityPig2(World world, double x, double y, double z)
    {
        super(world);
        pigbrine = true;

      
        if(HerobrineMod.mini_mode==1)	
            setSize(0.45F, 0.45F);
        else
        	setSize(0.9F, 0.9F);

       
        setPosition(x, y + (double) yOffset, z);
    }


    public EntityPig2(World world)
    {
        super(world);
        pigbrine = true;

        
        if(HerobrineMod.mini_mode==1)	
            setSize(0.45F, 0.45F);
        else
        	setSize(0.9F, 0.9F);
      
    }



    public void onLivingUpdate()
    {

    				super.onLivingUpdate();
    }




    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.900000011920929D);
    }


    protected void entityInit()
    {
        super.entityInit();

    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setBoolean("Saddle", getSaddled());
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        setSaddled(nbttagcompound.getBoolean("Saddle"));
    }

    protected String getLivingSound()
    {
        return "mob.pig";
    }

    protected String getHurtSound()
    {
        return "mob.pig";
    }

    protected String getDeathSound()
    {
        return "mob.pigdeath";
    }

    public boolean interact(EntityPlayer entityplayer)
    {
        if (!super.interact(entityplayer))
        {
            if (getSaddled() && !worldObj.isRemote && (riddenByEntity == null || riddenByEntity == entityplayer))
            {
                entityplayer.mountEntity(this);
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return true;
        }
    }

   

    public boolean getSaddled()
    {
        return (dataWatcher.getWatchableObjectByte(16) & 1) != 0;
    }

    public void setSaddled(boolean flag)
    {
        if (flag)
        {
            dataWatcher.updateObject(16, Byte.valueOf((byte)1));
        }
        else
        {
            dataWatcher.updateObject(16, Byte.valueOf((byte)0));
        }
    }

    public void onStruckByLightning(EntityLightningBolt entitylightningbolt)
    {
        if (worldObj.isRemote)
        {
            return;
        }
        else
        {
            EntityPigZombie entitypigzombie = new EntityPigZombie(worldObj);
            entitypigzombie.setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
            worldObj.spawnEntityInWorld(entitypigzombie);
            setDead();
            return;
        }
    }

    protected void fall(float f)
    {
        super.fall(f);

        if (f > 5F && (riddenByEntity instanceof EntityPlayer))
        {
            ((EntityPlayer)riddenByEntity).triggerAchievement(AchievementList.flyPig);
        }
    }

    public boolean field_753_a;
    public float field_752_b;
    public float destPos;
    public float field_757_d;
    public float field_756_e;
    public float field_755_h;



}
