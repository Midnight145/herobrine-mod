// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

package mods.HerobrineMod.common;

import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.Item;
import net.minecraft.world.World;

// Referenced classes of package net.minecraft.src:
//            EntityMob, World, MathHelper, Item

public class EntityZombie2 extends EntityZombie
{

    public EntityZombie2(World world, double x, double y, double z)
    {
        super(world);
        setPosition(x, y + (double)yOffset, z);
    
       	this.isImmuneToFire=true;

    }

    public EntityZombie2(World world)
    {
        super(world);
  
       	this.isImmuneToFire=true;
      
    }

    public void onLivingUpdate()
    {
    	super.onLivingUpdate();
   
 
    }
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.15000011920929D);
    }


    protected int func_40119_ar()
    {
        return 2;
    }

  

 

    public EnumCreatureAttribute func_40124_t()
    {
        return EnumCreatureAttribute.UNDEAD;
    }
}
