package mods.HerobrineMod.common;



import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

// Referenced classes of package net.minecraft.src:
//            EntityMob, World, MathHelper, Item

public class EntityFighter2 extends EntityBaseHMod
{

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.0f);
    }

    public EntityFighter2(World world)
    {
        super(world);
     
        wait = 0;
    }
    public int func_82193_c(Entity par1Entity) {
    	return 10;
    	}

    public EntityFighter2(World worldObj, double x, double y, double z)
    {
        super(worldObj);
        setPosition(x, y + (double)yOffset, z);

        wait = 0;
        st = false;
     
    }

    int tick = 400;
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (worldObj.isRemote)
        {
            return;
        }

        if (!st)
        {
       

            st = true;
        }

        int range = 20;

        if (tick > 400)
        {
            tick = 0;
            List list_ = worldObj.getEntitiesWithinAABB(EntityFighter.class, AxisAlignedBB.getBoundingBox(posX - range, posY - range, posZ - range, posX + range, posY + range, posZ + range));

            if (!list_.isEmpty())
            {
                super.setAttackTarget(((EntityLiving)list_.get(worldObj.rand.nextInt(list_.size()))));
            }
            else
            {
                wait++;

                if (400 < wait)
                {
                    setDead();
                    part();
                }
            }
        }
        else
        {
            tick++;
        }
    }

    protected void entityInit()
    {
        super.entityInit();
    }
    protected Entity findPlayerToAttack()
    {
        int range = 100;
        List list_ = worldObj.getEntitiesWithinAABB(EntityFighter.class, AxisAlignedBB.getBoundingBox(posX - range, posY - range, posZ - range, posX + range, posY + range, posZ + range));

        if (!list_.isEmpty())
        {
            return (EntityLiving)list_.get(worldObj.rand.nextInt(list_.size()));
        }
        else
        {
            wait++;
        }

        if (400 < wait)
        {
            setDead();
            part();
        }

        return null;
    }

    protected boolean isMovementCeased()
    {
        return false;
    }

    protected void part()
    {
        for (int i = 0; i < 20; i++)
        {
            double d = rand.nextGaussian() * 0.02D;
            double d1 = rand.nextGaussian() * 0.02D;
            double d2 = rand.nextGaussian() * 0.02D;
            double d3 = 10D;
            worldObj.spawnParticle("largesmoke", (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width - d * d3, (posY + (double)(rand.nextFloat() * height)) - d1 * d3, (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)width - d2 * d3, d, d1, d2);
        }
    }



    public int wait;
    private boolean st;

}