package mods.HerobrineMod.common;


import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

// Referenced classes of package net.minecraft.src:
//            EntityMob, World, MathHelper, Item

public class EntityFighter extends EntityBaseHMod
{

    public EntityFighter(World world, double x, double y, double z)
    {
        super(world);
       
        ingame = 0;
        wait = 0;
        start = false;
    }

    public EntityFighter(World world)
    {
        super(world);
       
        ingame = 0;
        wait = 0;
        start = false;
    }

    public int func_82193_c(Entity par1Entity) {
    	return 8;
    	}

    public EntityFighter(World worldObj, float f, float g, float h, boolean q)
    {
        super(worldObj);



        setPosition(f, g + (double)yOffset, h);

      
        ingame = 0;
        wait = 0;
        start = false;
        worldObj.playSoundEffect((double)this.posX + 0.5D, (double)this.posY + 0.5D, (double)this.posZ + 0.5D, "ambient.cave.cave", 35F, 0.8F);
    }

    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        
        if (worldObj.isRemote)
        {
            return;
        }

        if ((int)(Math.random() * 90 + 1) == 1 )
        {
            EntityPlayer stalk2 = worldObj.getClosestPlayerToEntity(this, 30D);
            if(stalk2 != null)
            	this.setPosition((double)stalk2.posX, (double)stalk2.posY, (double) stalk2.posZ+3);
        }
        wait++;

        if (wait < 45)
        {

        }

        if (!start)
        {
            EntityPlayer stalk2 = worldObj.getClosestPlayerToEntity(this, 30D);

            if (stalk2 == null)
            {
                return;
            }

            this.faceEntity(stalk2, 30F , 30F);
        }

        if (wait == 45)
        {
            start = true;
            EntityPlayer stalk = worldObj.getClosestPlayerToEntity(this, 30D);

            if (stalk == null)
            {
                return;
            }

      
         

            if ((int)(Math.random() * 8 + 1) == 1 )
            {
                worldObj.spawnEntityInWorld(new EntityFighter2(worldObj, (float)stalk.posX + 0.5F, (float)stalk.posY + 0.0F, (float)stalk.posZ + 0.5F));
             
                MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatComponentText("Herobrine: Hi brother!"));

                MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatComponentText("Notch: I don't want you in my game!"));

            }
        }

        if (start)
        {
            ingame++;
        }

        if (ingame == 800)
        {
            setDead();
        }
    }

    public void setDead()
    {
      
        EntityPlayer stalk2 = worldObj.getClosestPlayerToEntity(this, 30D);

        if (stalk2 == null)
        {
            return;
        }

      
        super.setDead();
    }
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }

    protected Entity findPlayerToAttack()
    {
        if (!start)
        {
            return null;
        }

        EntityPlayer entityplayer = worldObj.getClosestPlayerToEntity(this, 16D);

        if (entityplayer == null)
        {
            return null;
        }

        if (entityplayer != null && canEntityBeSeen(entityplayer))
        {
            return entityplayer;
        }
        else
        {
            return null;
        }
    }

    protected boolean isMovementCeased()
    {
        if (!start)
        {
            return true;
        }

        return false;
    }

    protected void dropFewItems()
    {
        Item i = getDropItemId();
        dropItem(i, 1);
    }
    
    protected Item getDropItemId()
    {
        int i = (int)(Math.random() * 6 + 1);

        if (i == 1)
        {
            return Items.diamond_sword;
        }

        if (i == 2)
        {
        //cd    return HerobrineMod.seeHerobrine.itemID;
        }

        if (i == 3)
        {
            return Items.golden_apple;
        }

        if (i == 4)
        {
        //cd    return HerobrineMod.itsHerobrine.itemID;
        }

        if (i == 5)
        {
            return Items.cake;
        }

        if (i == 6)
        {
            return Items.cookie;
        }

        return null;
    }

    private int ingame;
    private boolean start;
    public int wait;
    
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.900000011920929D);
    }


}